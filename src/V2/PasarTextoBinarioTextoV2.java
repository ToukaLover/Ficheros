package V2;

import java.io.*;

public class PasarTextoBinarioTextoV2 {

    public static void main(String[] args) {
        // Nombres de los ficheros
        String archivoTexto = "archivosV2/archivoTexto.txt";
        String archivoBinario = "archivosV2/archivo.bin";
        String archivoSalidaTexto = "archivosV2/salida.txt";

        // Convertir texto a binario
        textoABinario(archivoTexto, archivoBinario);

        // Convertir binario a texto
        binarioATexto(archivoBinario, archivoSalidaTexto);
    }

    // Método para convertir un archivo de texto a un archivo binario
    public static void textoABinario(String archivoTexto, String archivoBinario) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoTexto));
             DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivoBinario))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                // Convertimos la línea a bytes
                byte[] bytes = linea.getBytes();

                // Guardamos la longitud de la línea para poder leerla correctamente más tarde
                dos.writeInt(bytes.length);

                // Escribimos los bytes en el archivo binario
                dos.write(bytes);
            }

        } catch (IOException e) {
            System.err.println("Error leyendo/escribiendo archivos: " + e.getMessage());
        }
    }

    // Método para leer desde el archivo binario y escribirlo en un archivo de texto
    public static void binarioATexto(String archivoBinario, String archivoTextoSalida) {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(archivoBinario));
             BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTextoSalida))) {

            while (dis.available() > 0) {
                // Leemos la longitud de los bytes que representan una línea
                int longitud = dis.readInt();

                // Creamos un array de bytes para almacenar la línea
                byte[] bytes = new byte[longitud];

                // Leemos los bytes de la línea
                dis.readFully(bytes);

                // Convertimos los bytes a String y lo escribimos en el archivo de salida
                String linea = new String(bytes);
                writer.write(linea);
                writer.newLine(); // Añadimos una nueva línea
            }

        } catch (IOException e) {
            System.err.println("Error leyendo/escribiendo archivos: " + e.getMessage());
        }
    }
}
