package V1;

import java.io.*;

public class ListarContenidoTreeYBytes {
    public static void main(String[] args) throws IOException {
        File archivo1 = new File("archivosV1/archivoTexto.txt");
        File archivoBin = new File("archivosV1/archivoBin.bin");
        File archivoText= new File("archivosV1/archivoT.txt");

        FileWriter w = new FileWriter(archivo1);
        BufferedWriter bf = new BufferedWriter(w);
        bf.write("");

        String rutaCarpeta = "/home/tarde/Escritorio";
        listarContenidoRecursivo(new File(rutaCarpeta), "", true,archivo1);

        aBytes(archivo1,archivoBin);

        aTexto(archivoBin,archivoText);
    }



    public static void listarContenidoRecursivo(File carpeta, String prefijo, boolean esUltimo,File archivo1) throws IOException {
        FileWriter w = new FileWriter(archivo1,true);
        BufferedWriter bf = new BufferedWriter(w);

        if (carpeta.isDirectory()) {
            //Carpeta Usuario
            // Muestra el nombre de la carpeta con el prefijo adecuado
            bf.write(prefijo + (esUltimo ? "└── " : "├── ") + "Carpeta: " + carpeta.getName());
            bf.newLine();
            bf.close();
            System.out.println(prefijo + (esUltimo ? "└── " : "├── ") + "Carpeta: " + carpeta.getName());
            //Un array de objetos File con dos elementos: la carpeta etc y la carpeta home
            File[] archivos = carpeta.listFiles();


            if (archivos != null) {
                // Recorre los archivos y carpetas dentro de la carpeta actual
                for (int i = 0; i < archivos.length; i++) {
                    // Determina si es el último archivo para ajustar el prefijo de la siguiente línea
                    boolean esUltimoHijo = (i == archivos.length - 1);
                    listarContenidoRecursivo(archivos[i], prefijo + (esUltimo ? "    " : "│   "), esUltimoHijo,archivo1);
                }
            }
        }else if (carpeta.isFile()) {
            // Muestra el nombre del archivo con el prefijo adecuado
            bf.write(prefijo + (esUltimo ? "└── " : "├── ") + "Archivo: " + carpeta.getName());
            bf.newLine();
            bf.close();
            System.out.println(prefijo + (esUltimo ? "└── " : "├── ") + "Archivo: " + carpeta.getName());
        }
    }

    private static byte[] intToBytes(int value) {
        return new byte[] {
                (byte)(value >> 24),
                (byte)(value >> 16),
                (byte)(value >> 8),
                (byte)value
        };
    }

    public static void aBytes(File archivoHecho,File archivoBin) {
        try (FileReader fr = new FileReader(archivoHecho);BufferedReader br = new BufferedReader(fr);BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(archivoBin))) {
            String linea= br.readLine();
            while(linea!=null){

                byte[] bytesAEscribir = (linea).getBytes();

                bos.write(intToBytes(bytesAEscribir.length));

                bos.write(bytesAEscribir);

                linea=br.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int bytesToInt(byte[] bytes) {
        return ((bytes[0] & 0xFF) << 24) |
                ((bytes[1] & 0xFF) << 16) |
                ((bytes[2] & 0xFF) << 8) |
                (bytes[3] & 0xFF);
    }

    public static void aTexto(File archivoBin,File archivoTexto){
        try(FileInputStream fip = new FileInputStream(archivoBin);BufferedInputStream bis = new BufferedInputStream(fip);FileWriter fw = new FileWriter(archivoTexto,true);BufferedWriter bw = new BufferedWriter(fw)) {
//mamawebo
            byte[] primerEntero = new byte[4];

            while (bis.read(primerEntero)!= -1){

                int longitud = bytesToInt(primerEntero);

                byte[] bytesLeidos = new byte[longitud];

                bis.read(bytesLeidos);

                String linea = new String(bytesLeidos);

                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
