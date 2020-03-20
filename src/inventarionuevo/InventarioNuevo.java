/**
 * Asignación del programa: InventarioNuevo                          
 * @author: Ricardo Ruíz Alarcón                          
 * Fecha: 17/03/2020                                      
 * Descripción: Clase principal del proyecto, que crea el objeto operaciones y ejecuta los metodos de dicha clase.
**/

package inventarionuevo;

//import controlador.Operaciones;
//import java.sql.Connection;
import modelo.ConexionDB;
import modelo.ProductoDAO;
import modelo.ProductoPojo;
import controlador.Operaciones;

public class InventarioNuevo {

    public static void main(String[] args) {
        //ConexionDB conexion = new ConexionDB();
        //conexion.conectarMySQL();
        
        //ProductoDAO prodDAO = new ProductoDAO();
        //ProductoPojo prod = new ProductoPojo(111,"Peras","Verdes y frescas","Kg",6,43);
        
        //prodDAO.create(prod);
        Operaciones ope = new Operaciones();
        ope.ejecutarAplicacion();
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
}
