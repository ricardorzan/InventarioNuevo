/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ricar
 */
public class RegistroDAO {

    public boolean agregarRegistro(int folio, ProductoPojo actualP, int cantidad) {
        boolean agregado = false;
        Statement stm = null;
        Connection con = null;

        String sql = "INSERT INTO registro VALUES (" + folio + "," + actualP.getClave() + "," + cantidad + "," + (actualP.getPrecio() * cantidad) + ");";

        ConexionDB cc = new ConexionDB();
        try {
            con = cc.conectarMySQL();
            stm = con.createStatement();
            stm.execute(sql);
            agregado = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase DaoImple, método registrar");
            e.printStackTrace();
        }
        return agregado;
    }

    public double calcularTotal(VentaPojo venta) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        ProductoPojo c = null;
        String sql = "SELECT precioProductos FROM registro WHERE folio = " + venta.getFolio() + ";";
        double acumulador = 0;

        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                acumulador = acumulador + rs.getInt(1);
            }
            acumulador = (acumulador + (acumulador * 0.16));
            venta.setTotal(acumulador);
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase EstudianteDAO, método readAll()");
            e.printStackTrace();
        }

        return acumulador;
    }

    public List<RegistroPojo> readAll() {
        System.out.println("Aqui bien 1");
        Connection con = null;
        Statement stm = null;
        Statement stm2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        System.out.println("Aqui bien 2");
        String sql = "";

        List<RegistroPojo> listaVentas = new ArrayList<RegistroPojo>();
        List<Integer> folios = new ArrayList<Integer>();

        try {
            sql = "SELECT folio FROM registro GROUP BY folio;";
            con = new ConexionDB().conectarMySQL();
            System.out.println("Aqui bien 3");
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            System.out.println("Aqui bien 4");
            while (rs.next()) {
                int f = rs.getInt(1);
                folios.add(f);
                System.out.println(f);
            }
            Iterator<Integer> iteradorE = folios.iterator();
            System.out.println("Aqui bien 5");

            while (iteradorE.hasNext()) {

                System.out.println(iteradorE.next());

                int folioActual = iteradorE.next();
                sql = "select registro.clave,cantidadCompra,precioProductos,nombre from registro join producto on registro.clave = producto.clave where folio = " + folioActual + ";";
                //con = new ConexionDB().conectarMySQL();
                stm2 = con.createStatement();
                rs2 = stm2.executeQuery(sql);
                while (rs2.next()) {
                    RegistroPojo c = new RegistroPojo(rs.getInt(1), rs2.getInt(2), rs2.getDouble(3), rs2.getString(4));
                    listaVentas.add(c);

                    System.out.println(c.getClave() + c.getCantidadCompra() + c.getPrecioProductos() + c.getNombre());
                    /*String clave = (rs.getString("clave") != null) ? rs.getString("clave").toString() : "";
                String nombre = (rs.getString("nombre") != null) ? rs.getString("nombre").toString() : "";
                String descripcion = (rs.getString("descripcion") != null) ? rs.getString("descripcion").toString() : "";
                String unidad = (rs.getString("unidad") != null) ? rs.getString("unidad").toString() : "";
                String cantidad = (rs.getString("cantidad") != null) ? rs.getString("cantidad").toString() : "";
                String precio = (rs.getString("precio") != null) ? rs.getString("precio").toString() : "";
                System.out.println(clave + ":    " + nombre + ":" + descripcion + ":" + unidad + ":" + cantidad + ":" + precio);*/
                }
                stm.close();
                stm2.close();
                rs.close();
                rs2.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error: Clase RegistroDAO, método readAll()");
            e.printStackTrace();
        }

        return listaVentas;
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
}
