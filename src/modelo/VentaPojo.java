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
public class VentaPojo {
    int folio;
    String fechaCompra;
    double total;

    public VentaPojo(int folio, String fechaCompra, double total) {
        this.folio = folio;
        this.fechaCompra = fechaCompra;
        this.total = total;
    }
    
    public VentaPojo() {
        this.folio = 0;
        this.fechaCompra = "";
        this.total = 0;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
