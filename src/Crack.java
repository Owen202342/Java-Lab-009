import org.apache.commons.codec.digest.Crypt;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;
/*
Edited by: Owen van Mantgem, 3/26/2023
 */
public class Crack {
    public final User[] users;
    private final String dictionary;

    private static int x;
    private static String shadowLine;



    static File userFile;
    static File englishFile;

    public Crack(String shadowFile, String dictionary) throws IOException {
        this.dictionary = dictionary;
        this.users = Crack.parseShadow(shadowFile);
    }

    public void crack() throws IOException {
        Path trail = (Path) Paths.get(dictionary);
        englishFile = trail.toFile();
        FileInputStream fileInput1 = new FileInputStream(englishFile);

        BufferedReader fileReader1 = new BufferedReader(new InputStreamReader(fileInput1));

        String englishPass = "place_holder";
        String encryption;
        String otherHolder;
        char charHolder;

        for (int i = 0; getLineCount(dictionary) > i; i++) {
           englishPass = fileReader1.readLine();
           for (int j = 0; this.users.length > j; j++) {

               otherHolder =users[j].getPassHash();
               charHolder = otherHolder.charAt(0);
               otherHolder = ""+ charHolder;


               if (otherHolder.equals("$")) {
                    encryption = Crypt.crypt(englishPass,users[j].getPassHash() );
                    if (encryption.equals(users[j].getPassHash())) {
                        System.out.println(" Found Password: " + englishPass + " for user: " + users[j].getUserName());
                    }
               }



           }


        }







    }

    public static int getLineCount(String path) {
        int lineCount = 0;
        try (Stream<String> stream = Files.lines(Path.of(path), StandardCharsets.UTF_8)) {
            lineCount = (int)stream.count();
        } catch(IOException ignored) {}
        return lineCount;
    }

    public static User[] parseShadow(String shadowFile) throws IOException {
        User[] thisUser = new User[getLineCount(shadowFile)];

        Path path = (Path) Paths.get(shadowFile);
        userFile = path.toFile();
        FileInputStream fileInput = new FileInputStream(userFile);
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(fileInput));

        x = 0;
        while (x < getLineCount(shadowFile))  {
            shadowLine = fileReader.readLine();
            String[] splitShadow = shadowLine.split(":");
            thisUser[x] = new User(splitShadow[0],splitShadow[1]);
            x++;
        }
        return thisUser;


    }


    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.print("Type the path to your shadow file: ");

        String shadowPath = sc.nextLine();


        System.out.print("Type the path to your dictionary file: ");

        String dictPath = sc.nextLine();

        Crack c = new Crack(shadowPath, dictPath);
        c.crack();



    }
}
