
package com.mycompany.mavenproject1;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.concurrent.Future;


public class Client {
    
     static final int    PORT = 4060;
    static final String HOST = "127.0.0.1";
    public static void runInstance()   throws IOException, InterruptedException {
     
        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        InetSocketAddress hostAddress = new InetSocketAddress(HOST,PORT);
        Future future = client.connect(hostAddress);
        try{
             future.get();
        }catch(Exception e){
           e.printStackTrace();  
        }
       
 
        
         
            if ((client.isOpen())) {
                
                 System.out.println("bağlantı başarılı client");
            }
            
           
           
    }
       

   
}

