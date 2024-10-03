package com.iesvdc;

import java.io.File;

public class ListarContenidoRecursivo {
    public static void main(String[] args) {


        String rutaCarpeta = "/home/tarde/Escritorio";
        listarContenidoRecursivo(new File(rutaCarpeta),0);
    }

    public static void listarContenidoRecursivo(File carpeta,int nivel) {
        String tab = " ".repeat(nivel*2);
        if (carpeta.isDirectory()) {
            System.out.println( tab+"|-- Carpeta: " + carpeta.getName());

            File[] archivos = carpeta.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    listarContenidoRecursivo(archivo,nivel +1);
                }
                for (int i=0;i< archivos.length;i++){
                    if(i== archivos.length-1){

                    }
                }
            }
        } else if (carpeta.isFile()) {
            System.out.println(tab+"|-- Archivo: " + carpeta.getName());
        }
    }
}