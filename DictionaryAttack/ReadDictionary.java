/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crack22;

/**
 *
 * @author Opeyemi
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ReadDictionary {

    private static ArrayList<String> username = new ArrayList<>();
    private static ArrayList<String> salt = new ArrayList<>();
    private static ArrayList<String> hashedPassword = new ArrayList<>();
    
    private static ArrayList<String> wordList = new ArrayList<>();
    private static ArrayList<String> concatList = new ArrayList<>();
    public static String[] fillList;
    public String passList;
    String file;
    
    //default constructor
    public ReadDictionary() {
    }
   
    
    // method to read password file
    public void readMyFile(String file) throws IOException {

        try {
            File myFile = new File(file);

            try ( //FileReader fr = new FileReader(myFile);
                    BufferedReader read = new BufferedReader(new FileReader(myFile))) {
                while ((passList = read.readLine()) != null) {
                    
                    //System.out.println(passList.split(", ").toString());
                    fillList = passList.split(", ");
                    //dicList.add(read.readLine());
                    
                    username.add(fillList[0]);
                    salt.add(fillList[1]);
                    hashedPassword.add(fillList[2]);
                    
                                  
                }
            }
        } catch (FileNotFoundException e) {

            System.out.println("File Not found");
        }

    }
    
    //method to read the dictionary words
    public void readDictionary(String file) throws IOException, Exception {

        try {
            String dicoReader;
           // String[] dicoArray;
            File myFile = new File(file);

            try ( //FileReader fr = new FileReader(myFile);
                    BufferedReader read1 = new BufferedReader(new FileReader(myFile))) {
                while ((dicoReader = read1.readLine()) != null) {
                    
                    //System.out.println(passList.split(", ").toString());
                    //dicoArray = dicoReader.split("\\s)");
                    //dicList.add(read.readLine());
                    //System.out.println(dicoReader);
                    
                    wordList.add(dicoReader);
                }
            }
        } catch (FileNotFoundException e) {

            System.out.println("File Not found");
        }

    }
   
    
     
     //to break the password
     public static void crack() throws Exception{
         int i = wordList.size();
         int j = salt.size();
          concatList = new ArrayList<>(i*j);
         for (int y=0; y < i; y++){
             for(int d=0; d < j; d++){
                 concatList.add(wordList.get(y) + salt.get(d));
                 //System.out.println(concatList.get(y));
             }
         }
         //System.out.println(concatList.size());
         
         for(int m=0; m < concatList.size(); m++){
             //System.out.println(concatList.get(m));
             
             for(int n=0; n < hashedPassword.size(); n++){
                 
                 
 
                 if (hashedPassword.get(n).equals
                        (ComputeHash.convertStringToSha1(concatList.get(m))))
                 {
                     System.out.println("Username is: "+ username.get(n));
                     System.out.println("Password is: "+ wordList.get(m/10));
                     System.out.println("Salt is: "+ salt.get(n));
                     System.out.println();
                     //System.out.println(m);
                     break;
                                      
                 }

                 
             }
              
                    
                 
         }
       
     }
     
     

}
