/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author anyi4
 */
public class libro {
    private int id_libro;
    private String nombre_libro;
    private double precio_alquiler;

    public libro() {
    }

    public libro(String nombre_libro, double precio_alquiler) {
        this.nombre_libro = nombre_libro;
        this.precio_alquiler = precio_alquiler;
    }

    public libro(int id_libro, String nombre_libro, double precio_alquiler) {
        this.id_libro = id_libro;
        this.nombre_libro = nombre_libro;
        this.precio_alquiler = precio_alquiler;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getNombre_libro() {
        return nombre_libro;
    }

    public void setNombre_libro(String nombre_libro) {
        this.nombre_libro = nombre_libro;
    }

    public double getPrecio_alquiler() {
        return precio_alquiler;
    }

    public void setPrecio_alquiler(double precio_alquiler) {
        this.precio_alquiler = precio_alquiler;
    }
    
}
