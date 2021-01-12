
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

public class Client {
    
     static final int    PORT = 6060;
    static final String HOST = "127.0.0.1";
    
    
    public static void runInstance()   throws IOException, InterruptedException, JSONException, ExecutionException  {
        
        try(AsynchronousSocketChannel clientChannel = AsynchronousSocketChannel.open()) {
            InetSocketAddress hostAddress = new InetSocketAddress(HOST,PORT);
            Future future = clientChannel.connect(hostAddress);
            
            while(!future.isDone()) {
				//do something
				//or sleep
				Thread.sleep(20);
			}
             
                 try{
                  future.get();
      
                 }catch(InterruptedException | ExecutionException e){
                  }   
                                      
                    JSONObject json =  JsonObject.runInstance(); // creating object with random values
                     System.out.println(json);
                     
                    if ((clientChannel.isOpen())) {
                    
                        byte [] messageJson = json.toString().getBytes();
                        ByteBuffer buffer = ByteBuffer.wrap(messageJson);
                        Future result = clientChannel.write(buffer);
                        
                        while (!result.isDone()) {
                            
                        }
                        buffer.clear();
                         
                    }
                    if(clientChannel.isOpen()){
                        
                        ByteBuffer  buffer2 = ByteBuffer.allocate(1024);
                        Future result2 = clientChannel.read(buffer2);
                        
                        while (! result2.isDone()) {
                            
                        }
                        
                        buffer2.flip();
                        String Strjson = new String(buffer2.array()).trim();
                        JSONObject jsonResult = new JSONObject(Strjson);
                        
                        
                        
                        System.out.println(jsonResult.get("id")); // sending id
                        System.out.println(jsonResult.get("equals")); // result of operation
                        buffer2.clear();
                        
                   } 
                     Thread.sleep(1000);
                     clientChannel.close();
                    
                }   catch (InterruptedException | JSONException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
}
    

