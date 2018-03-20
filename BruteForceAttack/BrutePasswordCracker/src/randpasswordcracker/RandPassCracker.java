/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randpasswordcracker;

/**
 *
 * @author Opeyemi
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// import CreateSaltPwdFileDict.Utils;
public class RandPassCracker {

    private static int numUsers = 10;
    private static User[] user = new User[numUsers];
    private static long startTime;
    private static long estimatedTime;
    private static char[] allowed = new char[]{
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '_', '-'};

    public static void main(String[] args) throws Exception {

        // read password file to get the hashed password of each user
        BufferedReader passFile = new BufferedReader(new FileReader("randPwdFile68.txt"));
        String line;
        int count = 0;
        while ((line = passFile.readLine()) != null) {
            String[] u = line.split(" ");
            user[count] = new User();
            user[count].setName(u[0]);
            user[count++].setPass(u[1]);
        }
        passFile.close();

        for (int len = 1; len <= 10; len++) {
            startTime = System.currentTimeMillis();
            // generate all possible password of length equal to len
            generateAllPass("", len);
        }

    }

    private static boolean generateAllPass(String pass, int len) throws Exception {
        int currLen = pass.length();
        if (currLen == len) {
            // when password reaches expected length, check it against all user
            MessageDigest hash = MessageDigest.getInstance("SHA1");
            for (int i = 0; i < numUsers; i++) {
                hash.update(Utils.toByteArray(pass));
                String hashedPass = Utils.toHex(hash.digest());
                if (hashedPass.equals(user[i].getPass())) {
                    estimatedTime = System.currentTimeMillis() - startTime;
                    System.out.println("Cracking Successful. Time taken: " + estimatedTime + " milliseconds. " + user[i].getName() + " " + pass);
                    return true;
                }
            }
            return false;

        }

        for (int i = 0; i < allowed.length; i++) {
            boolean ret = generateAllPass(pass + allowed[i], len);
            // if we have found a matching password of given length, no need to 
            // generate more password of that length. so, the go back to main. 
            if (ret) {
                return true;
            }
        }
        return false;

    }

}
