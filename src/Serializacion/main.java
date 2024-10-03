package Serializacion;

public class main {
    public static void main(String[] args) {
        Libro l = new Libro("si","no","jaja",0,10);
        Libro l2 = new Libro("si2","no2","jiji",0,10);
        Libro l3 = new Libro("si3","no3","jeje",0,10);

        Biblioteca b = new Biblioteca();
        b.agregarLibro(l);
        b.agregarLibro(l2);
        b.agregarLibro(l3);

        b.mostrarLista();
        System.out.println("-".repeat(l.toString().length()));
//        b.serializarLista();
        b.deserializarLista();
    }
}