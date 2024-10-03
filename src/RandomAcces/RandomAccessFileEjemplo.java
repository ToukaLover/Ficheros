package RandomAcces;

import java.io.RandomAccessFile;
import java.io.IOException;

public class RandomAccessFileEjemplo {


    public static void main(String[] args) {
        try {
            // Abrimos el archivo en modo lectura y escritura
            RandomAccessFile raf = new RandomAccessFile("RandomAccess/datos.dat", "rw");

            // Escribimos algunos datos en el archivo
            raf.writeUTF("Hola Mundo"); // Escribir una cadena
            raf.writeInt(12345);         // Escribir un entero
            raf.writeDouble(3.14159);    // Escribir un double

            // Movemos el puntero al inicio del archivo
            raf.seek(0);

            // Leemos la cadena que escribimos
            String texto = raf.readUTF();
            System.out.println("Texto: " + texto);

            // Leemos el entero que escribimos
            int numero = raf.readInt();
            System.out.println("Número: " + numero);

            // Leemos el double que escribimos
            double decimal = raf.readDouble();
            System.out.println("Decimal: " + decimal);

            raf.close();  // Cerramos el archivo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
