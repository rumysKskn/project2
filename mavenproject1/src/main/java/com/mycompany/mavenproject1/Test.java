package com.mycompany.mavenproject1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.nio.channels.CompletionHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;


public class Test {
   
   
    public static void main(String[] args) throws IOException, InterruptedException, JSONException{
        
     
     Thread srv = new Thread(new Runnable() {
            @Override
            public void run() {
               
                try {
                    Server.runInstance();
                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        srv.start();  
         Thread cli = new Thread(new Runnable() {
            @Override
            public void run() { 
                try {
                    Client.runInstance();
                } catch (IOException | InterruptedException | JSONException ex) {
                    Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
         
         cli.start();
      
 }
}
   
    
 

