package RandomAcces;

import java.io.IOException;
import java.io.RandomAccessFile;

public class EjemploRandomAccessFile {
    public static void main(String[] args) {
        try {
            // Crear o abrir el archivo en modo lectura/escritura
            RandomAccessFile raf = new RandomAccessFile("archivo.txt", "rw");

            // 1. Escribir contenido inicial al archivo
            raf.writeUTF("Este es el contenido inicial del archivo.");
            System.out.println("Contenido inicial: " + leerContenido("archivo.txt"));

            // 2. Insertar contenido en el punto intermedio, desplazando lo que ya existe
            long posicionInsertar = 10; // Posición para insertar
            insertarContenido(raf, posicionInsertar, " -- CONTENIDO INSERTADO -- ");
            System.out.println("Después de insertar en la posición intermedia: " + leerContenido("archivo.txt"));

            // 3. Añadir contenido al final del archivo
            raf.seek(raf.length());  // Mover el puntero al final del archivo
            raf.writeUTF(" -- CONTENIDO FINAL -- ");
            System.out.println("Después de añadir contenido al final: " + leerContenido("archivo.txt"));

            raf.close(); // Cerrar el archivo al finalizar
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para leer el contenido del archivo
    public static String leerContenido(String filePath) {
        StringBuilder contenido = new StringBuilder();
        try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
            long fileLength = raf.length();
            while (raf.getFilePointer() < fileLength) {
                contenido.append(raf.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contenido.toString();
    }

    // Método para insertar contenido en una posición y desplazar el contenido existente
    public static void insertarContenido(RandomAccessFile raf, long posicion, String contenido) throws IOException {
        raf.seek(posicion); // Mover el puntero a la posición indicada

        // Leer el contenido que se encuentra desde la posición hasta el final del archivo
        byte[] contenidoRestante = new byte[(int) (raf.length() - posicion)];
        raf.readFully(contenidoRestante);

        // Volver a la posición de inserción
        raf.seek(posicion);

        // Escribir el nuevo contenido
        raf.writeUTF(contenido);

        // Escribir el contenido que estaba en esa posición hacia adelante
        raf.write(contenidoRestante);
    }
}
