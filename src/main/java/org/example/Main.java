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
        file.renameTo(new File(file.getPath().replaceAll("english", "korean")));
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line.replaceAll("english", "korean")).append("\n");
            }
        } catch (IOException e) {
            System.out.println("파일 읽기 중 오류 발생");
            e.printStackTrace();
            return;
        }

        // 변경된 내용을 파일에 다시 씀
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content.toString());
        } catch (IOException e) {
            System.out.println("파일 쓰기 중 오류 발생");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter the location of your folder: ");
            String fileName = sc.nextLine();
            File folder = new File(fileName);
            replaceInFolder(folder);
        }
    }
}