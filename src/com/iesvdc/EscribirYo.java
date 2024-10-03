package com.iesvdc;

import java.io.*;
import java.util.Scanner;

public class EscribirYo {
    public static void main(String[] args) throws IOException {
        File file1 = new File("archivo1.txt");
        File file2 = new File("archivo2.txt");

        FileWriter fw = new FileWriter(file2);
        BufferedWriter bw = new BufferedWriter(fw);

        FileWriter fw2= new FileWriter(file1);
        BufferedWriter bw2 = new BufferedWriter(fw2);
        Scanner sc = new Scanner(System.in);

        String lineaMia;
        System.out.println("Dime a linea que quieres ingresar, si la dejas vacia no seguiras");
        do{
            lineaMia=sc.nextLine();
            bw2.write(lineaMia);
            bw2.newLine();
        }while(sc.hasNextLine());

        bw2.close();

        FileReader fileReader = new FileReader(file1);
        BufferedReader br = new BufferedReader(fileReader);
        String linea = br.readLine();

        while(linea != null){
            bw.write(linea);
            bw.newLine();
            linea= br.readLine();
        }
        br.close();
        bw.close();

    }
}
