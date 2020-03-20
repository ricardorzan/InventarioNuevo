/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author ricar
 */
public class RegistroPojo {
    int clave;
    int cantidadCompra;
    double precioProductos;
    String nombre;

    public RegistroPojo(int clave, int cantidadCompra, double precioProductos, String nombre) {
        this.clave = clave;
        this.cantidadCompra = cantidadCompra;
        this.precioProductos = precioProductos;
        this.nombre = nombre;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public int getCantidadCompra() {
        return cantidadCompra;
    }

    public void setCantidadCompra(int cantidadCompra) {
        this.cantidadCompra = cantidadCompra;
    }

    public double getPrecioProductos() {
        return precioProductos;
    }

    public void setPrecioProductos(int precioProductos) {
        this.precioProductos = precioProductos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
