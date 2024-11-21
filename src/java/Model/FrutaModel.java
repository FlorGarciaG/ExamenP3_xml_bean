/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author florc
 */
public class FrutaModel {
    private int idFruta;
    private String nombre; 
    private double peso;
    private String color;
    private int cantidad;
    private String tipo;

    public FrutaModel() {
    }

    public FrutaModel(int idFruta, String nombre, double peso, String color, int cantidad, String tipo) {
        this.idFruta = idFruta;
        this.nombre = nombre;
        this.peso = peso;
        this.color = color;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    public int getIdFruta() {
        return idFruta;
    }

    public void setIdFruta(int idFruta) {
        this.idFruta = idFruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
