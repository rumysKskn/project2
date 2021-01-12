package com.mycompany.mavenproject1;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;


public class Test {
   
   
    public static void main(String[] args) throws IOException, InterruptedException, JSONException{
        
       
         
                Thread thr = new Thread(new Runnable() {
             @Override
             public void run() {
                 try { 
                       
                     Server.runInstance();
                  
                 } catch (InterruptedException | IOException   ex) {
                     Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 
             }
         });
         
           thr.start();
         
           for(int i=0;i<2;i++){
           new Thread(Client.cli).start();
              Thread.sleep(1000);
           }
         
    }   
    
   
    } 
    

      
 

   
    
 

