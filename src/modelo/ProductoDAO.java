/**
 * Asignación del programa: InventarioNuevo                          
 * @author: Ricardo Ruíz Alarcón                          
 * Fecha: 17/03/2020                                      
 * Descripción: Clase que maneja los registros de los productos en la base de datos, incluyendo altas, bajas, consultas y modificar.
**/

package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO implements ProductoInterface {

    @Override
    /**
     * 
     * @return boolean
     */
    public boolean create(ProductoPojo producto) {
        boolean created = false;
        Statement stm = null;
        Connection con = null;
        String sql = "INSERT INTO producto VALUES(" + producto.getClave() + ",'" + producto.getNombre() + "','" + producto.getDescripcion() + "','" + producto.getUnidad() + "'," + producto.getCantidad() + "," + producto.getPrecio() + ")";

        ConexionDB cc = new ConexionDB();
        try {
            con = cc.conectarMySQL();
            stm = con.createStatement();
            stm.execute(sql);
            created = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase DaoImple, método registrar");
            e.printStackTrace();
        }
        return created;
    }

    @Override
    public List<ProductoPojo> readAll() {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM producto order by clave";

        List<ProductoPojo> listaProductos = new ArrayList<ProductoPojo>();

        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                ProductoPojo c = new ProductoPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDouble(6));
                listaProductos.add(c);
                /*String clave = (rs.getString("clave") != null) ? rs.getString("clave").toString() : "";
                String nombre = (rs.getString("nombre") != null) ? rs.getString("nombre").toString() : "";
                String descripcion = (rs.getString("descripcion") != null) ? rs.getString("descripcion").toString() : "";
                String unidad = (rs.getString("unidad") != null) ? rs.getString("unidad").toString() : "";
                String cantidad = (rs.getString("cantidad") != null) ? rs.getString("cantidad").toString() : "";
                String precio = (rs.getString("precio") != null) ? rs.getString("precio").toString() : "";
                System.out.println(clave + ":    " + nombre + ":" + descripcion + ":" + unidad + ":" + cantidad + ":" + precio);*/
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase EstudianteDAO, método readAll()");
            e.printStackTrace();
        }

        return listaProductos;
    }

    public boolean update(String sentencia) {
        boolean updated = false;
        Statement stm = null;
        Connection con = null;

        ConexionDB cc = new ConexionDB();
        try {
            con = cc.conectarMySQL();
            stm = con.createStatement();
            System.out.println(sentencia);
            stm.executeUpdate(sentencia);
            updated = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase DaoImple, método modificar");
            e.printStackTrace();
        }
        return updated;
    }

    public boolean delete(int clave) {
        boolean deleted = false;
        Statement stm = null;
        Connection con = null;
        String sql = "DELETE FROM producto WHERE clave = " + clave + ";";

        ConexionDB cc = new ConexionDB();
        try {
            con = cc.conectarMySQL();
            stm = con.createStatement();
            stm.executeUpdate(sql);
            deleted = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase DaoImple, método registrar");
            e.printStackTrace();
        }
        return deleted;
    }

    public boolean comprobarStock(ProductoPojo actualP, int cantidad) {
        boolean suficiente = false;
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        
        ConexionDB cc = new ConexionDB();
        try {
            con = cc.conectarMySQL();
            stm = con.createStatement();
            
            if (actualP.getCantidad() >= cantidad) {
                actualP.setCantidad(actualP.getCantidad()-cantidad);
                this.update("UPDATE producto SET cantidad =" + actualP.getCantidad() + " WHERE clave = " + actualP.getClave() + "");
            }
            suficiente = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase DaoImple, método comprobarStock");
            e.printStackTrace();
        }
        return suficiente;
    }
    
    public ProductoPojo readOne(int clave) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        ProductoPojo c = null;
        String sql = "SELECT * FROM producto WHERE clave = " + clave +";";


        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                c = new ProductoPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDouble(6));
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase EstudianteDAO, método readAll()");
            e.printStackTrace();
        }

        return c;
    }
}
