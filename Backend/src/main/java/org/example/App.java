package org.example;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

import javax.crypto.KeyGenerator;
import java.io.*;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
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
        StringBuilder buffer = new StringBuilder();
        int tmp;
        while ((tmp = fileReader.read()) != -1) {
            buffer.append((char) tmp);
        }
        System.out.println("Initial content: " + buffer);

        String password = "MySecretPassword123";
        String salt = KeyGenerators.string().generateKey();

        TextEncryptor textEncryptor = Encryptors.text(password, salt);

        String encrypted = textEncryptor.encrypt(buffer.toString());
        System.out.println("Encrypted: " + encrypted);

        FileWriter fileWriter = new FileWriter("encrypted.txt");
        fileWriter.write(encrypted);
        fileWriter.flush();

        String decrypted = textEncryptor.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);

    }
}
