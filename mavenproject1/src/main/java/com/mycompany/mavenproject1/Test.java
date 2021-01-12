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
                     
                 } catch (IOException | InterruptedException   ex) {
                     Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 try {
                  
                     Client.runInstance();
                      
                 } catch (IOException | InterruptedException | JSONException | ExecutionException ex) {
                     Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         });
         
         thr.start();
    }
        
    }
    
      
 

   
    
 

