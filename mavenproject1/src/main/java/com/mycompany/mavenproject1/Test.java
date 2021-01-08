package com.mycompany.mavenproject1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.nio.channels.CompletionHandler;


public class Test extends Server {
   
   
    public static void main(String[] args) throws IOException, InterruptedException{
        
     
     Thread srv = new Thread(new Runnable() {
            @Override
            public void run() {
               Server.runInstance();
            }
        });
        srv.start();
        
         Client.runInstance();
      
 }
}
   
    
 

