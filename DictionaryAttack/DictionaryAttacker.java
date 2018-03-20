//package declar
package crack22;

import java.io.IOException;
import java.text.DecimalFormat;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @author Opeyemi Olatunji
 */
public class DictionaryAttacker {

    private static long progStartTime;
    private static long runTotalTime;
    private static DecimalFormat decimalPlace = new DecimalFormat("0.000");

    public static void main(String[] args) throws IOException, Exception {
        progStartTime = System.currentTimeMillis();
        String passwordFile = "dictPwdFile68.txt";
        String wordListFile = "wordList1.txt";

        //creating new objects of the dictionary
        ReadDictionary newReading = new ReadDictionary();
        ReadDictionary newReading1 = new ReadDictionary();

        // reading the password file and the dictionary word lists
        newReading.readMyFile(passwordFile);
        newReading1.readDictionary(wordListFile);

        System.out.println("....Password Cracking Begins!!!......");
        System.out.println();

        // cracking the passowrd
        ReadDictionary.crack();
        long timeNow = System.currentTimeMillis();
        runTotalTime = MILLISECONDS.toSeconds(timeNow - progStartTime);
        System.out.println("Average time taken: "
                + (decimalPlace.format((runTotalTime))) + " seconds. ");
        System.out.println("..................End of Program................");
        System.out.println();
        System.out.println("CopyRight: Opeyemi Olatunji");
    }

}
