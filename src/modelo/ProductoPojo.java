package modelo;

public class ProductoPojo {

    int clave;
    String nombre; 
    String descripcion;
    String unidad;
    int cantidad;
    double precio;

    public ProductoPojo(int clave, String nombre, String descripcion, String unidad, int cantidad, double precio) {
        this.clave = clave;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

}