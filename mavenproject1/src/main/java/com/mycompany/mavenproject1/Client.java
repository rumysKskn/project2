
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

public class Client extends Thread {
    
    static  int  PORT = 4060;
    static String HOST = "127.0.0.1";
    
    
             public void run() {
                
             try(AsynchronousSocketChannel clientChannel = AsynchronousSocketChannel.open()) {
            InetSocketAddress hostAddress = new InetSocketAddress(HOST,PORT);
            Future future = clientChannel.connect(hostAddress);
             
                 try{
                  future.get();
      
                 }catch(InterruptedException | ExecutionException e){
                  }   
                   
                        JSONObject json =  JsonObject.CreateJson(); // creating object with random values
                  
                     
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
                        
                        
                        
                        System.out.println("id:"+jsonResult.get("id")); // sending id
                        System.out.println("equals:"+jsonResult.get("equals")); // result of operation
                        buffer2.clear();
                        
                   } 
                  
                   
                     clientChannel.close();
                     
                }   catch (JSONException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
    }       catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
             }
                 }
          
     
     
  

    

    

