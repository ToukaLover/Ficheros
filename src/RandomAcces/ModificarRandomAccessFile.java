package RandomAcces;

import java.io.RandomAccessFile;
import java.io.IOException;

public class ModificarRandomAccessFile {
    public static void main(String[] args) {
        try {
            RandomAccessFile raf = new RandomAccessFile("RandomAccess/datos.dat", "rw");

            // Mover el puntero para modificar el entero (que está justo después de la cadena)
            raf.seek(raf.getFilePointer() + "Hola Mundo".length() + 2);  // +2 es para los bytes extra de UTF
            raf.writeInt(54321);  // Cambiar el número 12345 por 54321

            // Leer el archivo desde el principio para verificar el cambio
            raf.seek(0);
            System.out.println("Texto: " + raf.readUTF());
            System.out.println("Número modificado: " + raf.readInt());
            System.out.println("Decimal: " + raf.readDouble());

            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
