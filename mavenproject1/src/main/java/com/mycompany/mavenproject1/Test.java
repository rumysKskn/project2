package com.mycompany.mavenproject1;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;


public class Test {
   
   
    public static void main(String[] args) throws IOException, InterruptedException, JSONException{
         
        int i=0;
          
           while(i<1000){
                Thread thr = new Thread(new Runnable() {
             @Override
             public void run() {
                 try { 
                       
                     Server.getInstance().runInstance();
                      
                 } catch (InterruptedException | IOException   ex) {
                     Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 
             }
         });
         
             thr.start();
             Client cli = new Client();
             cli.start();
                    
                      
          while(thr.isAlive()){
                
            }
        
           
                i++;
            
            
            
               
           }
        
    }   
    
   
    } 
    

      
 

   
    
 

