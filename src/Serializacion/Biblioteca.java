package Serializacion;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Libro> lista;

    public Biblioteca() {
        this.lista = new ArrayList<>();
    }


    public void agregarLibro(Libro libro){
        this.lista.add(libro);
    }
    public void mostrarLista(){
        for(Libro l : lista){
            System.out.println(l.toString());
        }
    }
    public void serializarLista(){
        try(FileOutputStream fos = new FileOutputStream("Serializacion/biblioteca.dat"); ObjectOutputStream obs = new ObjectOutputStream(fos)){
            for (Libro l : lista) {
                obs.writeObject(l);
            }
            System.out.println("Lista Serializada");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void deserializarLista(){
        try (FileInputStream fis = new FileInputStream("Serializacion/biblioteca.dat");ObjectInputStream ois = new ObjectInputStream(fis)){

            /*while (true) {
                try {
                    // Leer y deserializar la siguiente mascota
                    Libro l = (Libro) ois.readObject();
                    System.out.println("\nInformación de la mascota:");
                    System.out.println(l.toString());
                }catch (EOFException e) {
                    System.err.println("Alcanzado el final del archivo");
                    break; // Se alcanzó el final del archivo
                }
            } El que ha hecho el profesor */

            Libro l = (Libro) ois.readObject();
            while(l!=null){
                try {
                    System.out.println(l.toString());
                    l = (Libro) ois.readObject();
                }catch (EOFException e){
                    System.err.println("Yo ya");
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.err.println("Lista acabada");
        }
    }
}
