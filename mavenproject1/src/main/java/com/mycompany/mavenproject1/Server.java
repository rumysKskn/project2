package com.mycompany.mavenproject1;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.nio.channels.CompletionHandler;
public class Server {
    
     
   
    static final int    PORT = 4060;
    static final String HOST = "127.0.0.1";
    public static void runInstance(){
        
         try {
            final AsynchronousServerSocketChannel server = 
                    AsynchronousServerSocketChannel.open().bind(
                            new InetSocketAddress(HOST,PORT));

            System.out.println("Server listening on " + PORT);

            server.accept("Client connection", 
                    new CompletionHandler<AsynchronousSocketChannel, Object>() {
                @Override
                public void completed(AsynchronousSocketChannel ch, Object att) {
                    System.out.println("Accepted a connection");

                    // accept the next connection
                    server.accept("Client connection", this);

                    // handle this connection
                    //TODO handle(ch);
                }

                @Override
                public void failed(Throwable exc, Object att) {
                    System.out.println("Failed to accept connection");
                }
            });
        } catch (IOException e) {
        }
        
    }
       
    }
    



    