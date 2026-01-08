package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        System.out.println("Give a path to file which should be encrypted");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        File file = new File(path);
        if (file.exists() != true) {
            System.out.println("Specified file doesn't exist");
            System.exit(0);
        }
        FileReader fileReader = new FileReader(file);
        char[] buffer = new char[1024];
        int res = fileReader.read(buffer);
        if (res == 0) {
            System.out.println("File was empty");
            System.exit(0);
        }
        System.out.println(buffer);
        int key = encrypt(buffer);
        System.out.printf("This is encrypted content: %s%n", new String(buffer, 0, res));
        decrypt(buffer, key);
        System.out.printf("This is decrypted content: %s%n", new String(buffer, 0, res));
    }


    public static int encrypt(char[] s) {
        Random random = new Random();
        int offset = random.nextInt(65536);
        for (int i = 0; i < s.length; i++) {
            s[i] += offset;
            s[i] %= 65536;
        }
        System.out.println();
        return offset;
    }

    public static void decrypt(char[] s, int key) {
        for (int i = 0; i < s.length; i++) {
            s[i] -= key;
        }
    }
}
