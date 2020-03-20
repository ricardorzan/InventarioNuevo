/**
 * Asignación del programa: InventarioNuevo                          
 * @author: Ricardo Ruíz Alarcón                          
 * Fecha: 17/03/2020                                      
 * Descripción: Operaciones del programa, incluyendo el menu y funciones que no incluyan manejo de base de datos.
**/

package controlador;

import java.util.List;
import modelo.ProductoDAO;
import modelo.ProductoPojo;
import modelo.RegistroDAO;
import modelo.RegistroPojo;
import modelo.VentaDAO;
import modelo.VentaPojo;
import vista.Mensajes;

public class Operaciones {

    Mensajes ms;
    ProductoDAO productoDAO;
    VentaDAO ventaDAO;
    RegistroDAO registroDAO;

    /**
     * Constructor de la clase que inicializa sus atributos y permite sus funciones.
     * @return void
     */
    public Operaciones() {
        this.ms = new Mensajes();
        this.productoDAO = new ProductoDAO();
        this.ventaDAO = new VentaDAO();
        this.registroDAO = new RegistroDAO();
    }

    /*
    public void guardar(String nombre) {
        EstudiantePojo ep = new EstudiantePojo(nombre);
        EstudianteDAO ei = new EstudianteDAO();
        ei.create(ep);
    }
     */
    
    /**
     * Muestra los productos registrados en la base de datos
     * @return void
     */
    public void mostrar() {
        List<ProductoPojo> lista = productoDAO.readAll();
        System.out.println("Aqui bien final");
        ms.mostrarProductos(lista);
    }

    /**
     * Muestra las ventas registradas en la base de datos
     * @return void
     */
    public void mostrarVentas() {
        List<RegistroPojo> lista = registroDAO.readAll();
        ms.mostrarVentas(lista);
    }

    /**
     * Permite modificar un producto registrado en la base de datos recibiendo su clave y lo que se modificará 
     * @param clave
     * @param indice
     * @return 
     */
    public String modificarProductos(int clave, int indice) {
        String modificar = null;
        String newString;
        int newInt;
        switch (indice) {
            case 1:
                newInt = ms.leerClave();
                modificar = String.format("UPDATE producto SET clave =" + newInt + " WHERE clave = " + clave + ";");
                break;
            case 2:
                newString = ms.leerNombre();
                modificar = String.format("UPDATE producto SET nombre ='" + newString + "' WHERE clave = " + clave + ";");
                break;
            case 3:
                newString = ms.leerDescripcion();
                modificar = String.format("UPDATE producto SET descripcion ='" + newString + "' WHERE clave = " + clave + ";");
                break;
            case 4:
                newString = ms.leerUnidad();
                modificar = String.format("UPDATE producto SET unidad ='" + newString + "' WHERE clave = " + clave + ";");
                break;
            case 5:
                newInt = ms.leerCantidad();
                modificar = String.format("UPDATE producto SET cantidad =" + newInt + " WHERE clave = " + clave + ";");
                break;
            case 6:
                double newDouble = ms.leerPrecio();
                modificar = String.format("UPDATE producto SET precio =" + newDouble + " WHERE clave = " + clave + ";");
                break;
        }
        return modificar;
    }

    /**
     * Funcion principal que ejecuta la aplicacion y maneja los llamados a funciones. Permite la ejecución de los menus llamado los metodos necesarios.
     * @return void
     */
    public void ejecutarAplicacion() {
        int opcion = 0;
        int opcion2 = 0;
        do {
            ms.menu();
            opcion = ms.opcionEnterio();
            switch (opcion) {
                case 1:
                    ms.agregarMensaje();
                    int clave = ms.leerClave();
                    String nombre = ms.leerNombre();
                    String descripcion = ms.leerDescripcion();
                    String unidad = ms.leerUnidad();
                    int cantidad = ms.leerCantidad();
                    double precio = ms.leerPrecio();
                    ProductoPojo producto = new ProductoPojo(clave, nombre, descripcion, unidad, cantidad, precio);
                    boolean creado = this.productoDAO.create(producto);
                    ms.resutladoOperacion(creado, " crear ");
                    break;
                case 2:
                    this.mostrar();
                    //List<ProductoPojo> lista = this.productoDAO.readAll();
                    //ms.mostrarProductos(lista);
                    break;
                case 3:
                    ms.modificarMensaje();
                    clave = ms.leerClave();
                    int indice = ms.leerModificar();
                    String sentencia = this.modificarProductos(clave, indice);
                    boolean actualizado = this.productoDAO.update(sentencia);
                    ms.resutladoOperacion(actualizado, " modificar ");
                    break;
                case 4:
                    ms.eliminarMensaje();
                    clave = ms.leerClave();
                    boolean eliminado = this.productoDAO.delete(clave);
                    ms.resutladoOperacion(eliminado, " eliminar ");
                    break;
                case 5:
                    do {
                        ms.menuVentas();
                        opcion2 = ms.opcionEnterio();
                        switch (opcion2) {
                            case 1:
                                VentaPojo venta = new VentaPojo(); // Crea el objeto                                
                                boolean generado = this.ventaDAO.generate(); // Genera la venta en la BD
                                ms.resutladoOperacion(generado, " generar ");
                                boolean folio = ventaDAO.actualizarFolio(venta);
                                ms.resutladoOperacion(folio, " cambia folio, nuevo folio: " + venta.getFolio());
                                do {
                                    clave = ms.realizarVenta();
                                    cantidad = ms.realizarCompra();
                                    ProductoPojo actualP = productoDAO.readOne(clave);
                                    boolean suficiente = productoDAO.comprobarStock(actualP, cantidad);
                                    if (suficiente) {
                                        boolean agregar = registroDAO.agregarRegistro(venta.getFolio(), actualP, cantidad);

                                    } else {
                                        ms.sinCantidad();
                                    }
                                    indice = ms.terminarVenta();
                                } while (indice != 2);
                                double total = registroDAO.calcularTotal(venta);
                                boolean actualizarV = this.ventaDAO.actualizarV(venta);
                                break;
                            case 2:
                                this.mostrarVentas();
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Reiniciando el menu..");
                        }
                    } while (opcion2 != 3);
                    break;
                case 6:
                    ms.añadirCantidad();
                    clave = ms.leerClave();
                    cantidad = ms.leerCantidad();
                    precio = ms.leerPrecio();
                    ProductoPojo actualP = productoDAO.readOne(clave);
                    double newPrecio = ((actualP.getCantidad() * actualP.getPrecio()) + (cantidad * precio)) / (cantidad + actualP.getCantidad());
                    actualP.setCantidad((cantidad + actualP.getCantidad()));
                    actualP.setPrecio(newPrecio);
                    sentencia = "UPDATE producto SET cantidad =" + actualP.getCantidad() + " WHERE clave = " + actualP.getClave() + "";
                    boolean añadidoC = this.productoDAO.update(sentencia);
                    sentencia = "UPDATE producto SET precio =" + actualP.getPrecio() + " WHERE clave = " + actualP.getClave() + "";
                    boolean añadidoP = this.productoDAO.update(sentencia);
                    ms.resutladoOperacion(añadidoP, " añadir cantidad ");
                    break;
                case 7:
                    ms.quitarCantidad();
                    clave = ms.leerClave();
                    cantidad = ms.leerCantidad();
                    actualP = productoDAO.readOne(clave);
                    newPrecio = (actualP.getCantidad() * actualP.getPrecio());

                    boolean suficiente = productoDAO.comprobarStock(actualP, cantidad);
                    if (suficiente) {
                        newPrecio = newPrecio/actualP.getCantidad();
                        actualP.setPrecio(newPrecio);
                        sentencia = "UPDATE producto SET precio =" + actualP.getPrecio() + " WHERE clave = " + actualP.getClave() + "";
                        añadidoP = this.productoDAO.update(sentencia);
                        ms.resutladoOperacion(añadidoP, " añadir cantidad ");
                        ms.resutladoOperacion(suficiente, "quitar cantidad");
                    } else {
                        ms.sinCantidad();
                    }

                    break;
                case 8:
                    break;
                default:
                    System.out.println("Reiniciando el sistema..");
            }
        } while (opcion != 8);
    }
}