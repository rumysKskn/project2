package com.mycompany.mavenproject1;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Future;
import org.json.JSONObject;
public class Server  {
    
     
   
    static final int    PORT = 4060;
    static final String HOST = "127.0.0.1";
    public static void runInstance()  throws IOException,InterruptedException {
         int resultOp =0;    
       try{
         AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();
        InetSocketAddress hostAddress = new InetSocketAddress(HOST, PORT);
        serverChannel.bind(hostAddress);
        
        Future acceptResult = serverChannel.accept();
        AsynchronousSocketChannel clientChannel = (AsynchronousSocketChannel) acceptResult.get();
          
       
           if ((clientChannel != null) && (clientChannel.isOpen())) {
 
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                Future result = clientChannel.read(buffer);
 
                 while (! result.isDone()) {
                  
                 }
                
                buffer.flip();
                String Strjson = new String(buffer.array()).trim();
                JSONObject json = new JSONObject(Strjson); 
                
                System.out.println(json.get("id"));
                System.out.println(json.get("operation"));
                System.out.println(json.get("num1"));
                System.out.println(json.get("num2"));
                
                String s = (String) json.get("operation");
                int n1 =(int) json.get("num1");
                int n2 =(int) json.get("num2");
                 resultOp =  operation(s,n1,n2);
              System.out.println("Result of operation in server "+resultOp);
              
                JSONObject jsonResult = new JSONObject(); 
                 jsonResult.put("id",json.get("id")) ;
                 jsonResult.put("result",resultOp );
                 
            byte [] messageResult = jsonResult.toString().getBytes();
            ByteBuffer bufferResult = ByteBuffer.wrap(messageResult);
            Future Sendingresult = clientChannel.write(bufferResult);
            
               
        }
                
                 
           
       }
           catch (Exception e) {
        }
       
      
        
       
       }
    
      public static int  add(int num1,int num2){
       
        return  (num1+num2);
       
    }
      
      public static int  substract(int num1,int num2){
       
        return  (num1-num2);
       
    }
      
      public static int  multiply(int num1,int num2){
       
        return  (num1*num2);
       
    } 
       public static int  divide(int num1,int num2){
       
        return  (num1/num2);
       
    } 
       
        public static int operation(String str,int num1,int num2){
          int result=0;
       
       switch (str) {
          case "add":
        result = add( num1,num2);
           break;
           
          case "substract":
            result = substract( num1,num2);
           break;
           
          case "multiply":  
             result = multiply( num1,num2);   
           break;
           
          case "divide":
           result =  divide( num1,num2);    
           break;
    }
  
       return result;
    

}

}