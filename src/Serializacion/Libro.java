package Serializacion;

import java.io.Serializable;

public class Libro implements Serializable {
    private String titulo,autor,isbn;
    private double precio;
    private transient double descuento; //transient hace que no se serialice

    public Libro(String titulo, String autor, String isbn, double precio, double descuento) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.precio = precio;
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", isbn='" + isbn + '\'' +
                ", precio=" + precio +
                ", descuento=" + descuento +
                '}';
    }
}
