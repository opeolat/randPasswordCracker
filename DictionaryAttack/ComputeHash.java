/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crack22;

import java.security.MessageDigest;

/**
 * @author Opeyemi Olatunji
 */
public class ComputeHash {

    //confidguring the hex conversion that will be applied to a byte array
    public static String bytesToHex(byte[] b) {
    char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'a', 'b', 'c', 'd', 'e', 'f'};
    
        StringBuilder buf = new StringBuilder();

        for (int j = 0; j < b.length; j++) {
            buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
            buf.append(hexDigit[b[j] & 0x0f]);
        }
        return buf.toString();
    }
    
    // HASHING: converting Strings to SHA1 hashing
    public static String convertStringToSha1(String input) throws Exception {
        //Setup MessageDigest for SHA1
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.reset();

        //Setup a MessageDigest with our input string
        md.update(input.getBytes("UTF-8"));

        //convert the string's digest to HEX
        String sha1 = bytesToHex(md.digest());
        return sha1;
    }


}