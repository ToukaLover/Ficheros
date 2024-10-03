package com.iesvdc;

import java.io.*;

public class EjemploFicherosBinarios {

    public static void main(String[] args) {
        // Archivos de entrada y salida
        String archivoEntrada = "archivo_entrada.bin";
        String archivoSalida = "archivo_salida.bin";

        // Crear un archivo de ejemplo
        crearArchivoEjemplo(archivoEntrada);

        // Proceso de copia usando buffering
        copiarArchivoConBuffer(archivoEntrada, archivoSalida);

        // Verificar que el archivo se haya copiado correctamente
        verificarArchivoCopia(archivoSalida);
    }

    // Método para copiar un archivo utilizando buffering
    public static void copiarArchivoConBuffer(String archivoEntrada, String archivoSalida) {
        // Declaración de los streams
        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            // Inicializamos FileInputStream y FileOutputStream
            fis = new FileInputStream(archivoEntrada);
            fos = new FileOutputStream(archivoSalida);

            // Usamos BufferedInputStream y BufferedOutputStream para mejorar el rendimiento
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            // Tamaño del buffer para leer y escribir (8192 bytes por defecto)
            byte[] buffer = new byte[8192];
            int bytesLeidos;

            // Leemos del archivo de entrada y escribimos en el archivo de salida
            while ((bytesLeidos = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesLeidos);  // Escribimos los bytes leídos en el archivo de salida
            }

            // Aseguramos que los datos sean escritos en el archivo de salida
            bos.flush();

            System.out.println("Archivo copiado correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cerramos los streams para liberar recursos
            try {
                if (bis != null) bis.close();
                if (bos != null) bos.close();
                if (fis != null) fis.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para crear un archivo de ejemplo con datos binarios
    public static void crearArchivoEjemplo(String archivoEntrada) {
        try (FileOutputStream fos = new FileOutputStream(archivoEntrada)) {
            String contenido = "Este es un archivo de prueba con contenido binario.";
            fos.write(contenido.getBytes());  // Convertimos el texto en bytes y lo escribimos en el archivo
            System.out.println("Archivo de ejemplo creado: " + archivoEntrada);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para verificar el contenido del archivo copiado
    public static void verificarArchivoCopia(String archivoSalida) {
        try (FileInputStream fis = new FileInputStream(archivoSalida)) {
            int data;
            System.out.println("Contenido del archivo copiado:");
            while ((data = fis.read()) != -1) {
                System.out.print((char) data);  // Mostramos el contenido del archivo
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
