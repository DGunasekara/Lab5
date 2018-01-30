/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.BufferedReader;
import java.io.*;
import java.util.HashMap;

/**
 *
 * @author kuuri
 */
public class AddressBook {
    
     HashMap <String, String> hashMap;// = new HashMap<String , String>();
     FileWriter in;
     BufferedWriter br;
     
    AddressBook() {
        hashMap = new HashMap<String , String>();
    }

    void initAddressBook(String path) {
     
        try{       

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path))); 
            String line = null;
        
            while ((line = br.readLine()) != null) {
               
		String[] comaSplit = line.split(",",2);   				//splitting the space separated array by comas
                hashMap.put(comaSplit[0],comaSplit[1]);	
            }
	br.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    
    }   
    //search details of the requested contact in the address book
    public String search(String name){
      
        if(hashMap.containsKey(name)){
            return "Details of "+name+" "+hashMap.get(name);
        }
	return "Details  Not found";       
    }
    
    public synchronized void add(String name,String details){
        
        hashMap.put(name,details); 

    }
    public void write(String fileName) {

    try{
               in = new FileWriter(fileName);
                br = new BufferedWriter(in);
                
                for(String str:hashMap.keySet()){
                
                    br.write(str+","+hashMap.get(str));             
                    br.newLine();
                    br.flush();
                }

                br.close();
                in.close();

            } catch (Exception x) {
                System.out.println(x);
            }
  }    
    
}
