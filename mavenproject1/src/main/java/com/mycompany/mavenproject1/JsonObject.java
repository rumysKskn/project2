/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author rumys
 */
public class JsonObject {
   
    
      public static void runInstance() throws JSONException {
        JSONObject jObject = new JSONObject();
        jObject.put("id", randomIdGenerator());
        jObject.put("operation", randomOpGenerator());
        jObject.put("num1", randomNumGenerator());
        jObject.put("num2",randomNumGenerator());
    
	System.out.println(jObject);
      }
  


 public static int  randomIdGenerator(){
        int random_num = (int) (Math.random() * (100) + (10) );
        return random_num;
    }
  public static int  randomNumGenerator(){
        int random_num = (int) (Math.random() * (10) + (1) );
        return random_num;
    }
  
  public static String  randomOpGenerator(){
       int random_num  = (int) (Math.random() * (4)  );
       String str=null;
       
       switch (random_num) {
          case 0:
            str="add";
            break;
          case 1:
            str="substract";
            break;
          case 2:  
            str="multiply";  
              break;
          case 3:
            str="divide";
            break;
    }
  
       return str;
}

}