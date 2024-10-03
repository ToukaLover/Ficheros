package com.iesvdc;

import java.io.*;

public class LeerArchivo {
    public static void main(String[] args) {
        try{
            String ruta = "D:/DAM-2/Acceso a Datos/Tema 1/Ficheros/archivoRenombrado.txt";
            FileReader fr = new FileReader(ruta);

            int i;
            while ((i= fr.read()) != -1) {
                System.out.println((char)i);
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
