package org.example;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void replaceInFolder(File folder) {
        for(File file : Objects.requireNonNull(folder.listFiles())) {
            if(file.isFile()) {
                replaceLanguage(file);
            }else {
                replaceInFolder(file);
            }
        }
    }

    public static void replaceLanguage(File file) {
        file.renameTo(new File(file.toURI().toString().replace("english", "korean")));
        FileInputStream input;
        FileOutputStream output;
        BufferedReader reader;
        BufferedWriter writer;
        try {
            input = new FileInputStream(file);
            output = new FileOutputStream(file);
            reader = new BufferedReader(new InputStreamReader(input));
            writer = new BufferedWriter(new OutputStreamWriter(output));
        }catch(Exception e) {
            System.out.println("생성 중에 오류남.");
            e.printStackTrace();
            return;
        }
        try{
            String line;
            String afterline;
            while((line = reader.readLine())!=null) {
                afterline = line.replaceAll("english", "korean");
                writer.write(afterline, 0, afterline.length());
                writer.newLine();
            }
        }catch(Exception e) {
            System.out.println("바꾸는 중에 오류남");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter the location of your folder: ");
            String fileName = sc.nextLine();
            File folder = new File(fileName);

        }
    }
}