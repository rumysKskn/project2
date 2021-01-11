package com.mycompany.mavenproject1;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.concurrent.ExecutionException;

import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class Server  {
    
    static final int    PORT = 4060;
    static final String HOST = "127.0.0.1";
    static     int resultOp =0;    
    public static void runInstance()  throws IOException,InterruptedException {
    
       try{
        final AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();
        InetSocketAddress hostAddress = new InetSocketAddress(HOST, PORT);
         serverChannel.bind(hostAddress);
        
     
                Future acceptResult = serverChannel.accept();
                AsynchronousSocketChannel clientChannel = (AsynchronousSocketChannel) acceptResult.get();
                
                 if ((clientChannel != null) && (clientChannel.isOpen())) {
                   ByteBuffer buffer = ByteBuffer.allocate(1024);
                Future result = clientChannel.read(buffer);
                System.out.println("server is open...");
                while (! result.isDone()) {
                    
                }
                buffer.flip();
                String Strjson = new String(buffer.array()).trim();
                System.out.println("Request:"+Strjson); // reguest json from client
                 buffer.clear();
                 
                JSONObject json = new JSONObject(Strjson);
               
                String s = (String) json.get("operation");
                int n1 =(int) json.get("num1");
                int n2 =(int) json.get("num2");
                resultOp =  operation(s,n1,n2);  // result of operation
                
                
                JSONObject jsonResult = new JSONObject();
                jsonResult.put("id",json.get("id")) ;
                jsonResult.put("equals",resultOp );
                
                byte [] messageResult = jsonResult.toString().getBytes();
                ByteBuffer bufferResult = ByteBuffer.wrap(messageResult);
                
                clientChannel.write(bufferResult);
               
                 clientChannel.close();
                 serverChannel.close();
                 }
              
                 
            } catch (JSONException | ExecutionException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
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