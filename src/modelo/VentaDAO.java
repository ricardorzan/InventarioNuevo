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
public class VentaDAO {

    public boolean generate() {
        boolean created = false;
        Statement stm = null;
        Connection con = null;
        String sql = "INSERT INTO venta VALUES(null,null,null);";
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

    public boolean actualizarFolio(VentaPojo venta) {
        boolean updated = false;
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        String sql = "SELECT folio FROM venta";

        ConexionDB cc = new ConexionDB();
        try {
            con = cc.conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            int ultimoFolio = 0;
            while (rs.next()) {
                ultimoFolio = rs.getInt(1);
            }
            venta.setFolio(ultimoFolio);
            updated = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase DaoImple, método comprobarStock");
            e.printStackTrace();
        }
        return updated;
    }

    public boolean actualizarV(VentaPojo venta) {
        boolean updated = false;
        Statement stm = null;
        Connection con = null;
        String sql = "UPDATE venta SET total =" + venta.total + " WHERE folio = " + venta.getFolio() + ";";

        ConexionDB cc = new ConexionDB();
        try {
            con = cc.conectarMySQL();
            stm = con.createStatement();
            System.out.println(sql);
            stm.executeUpdate(sql);
            updated = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase DaoImple, método actualizarV");
            e.printStackTrace();
        }
        return updated;
    }

    
}
