package modelo;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class personaDao {

    coneccion conectar = new coneccion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<persona> listar() {
        ArrayList<persona> lstpersona = new ArrayList<>();
        String sql = "select * from persona";
        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = (ResultSet) ps.executeQuery();
            while (rs.next()) {
                persona p = new persona();
                p.setNum_doc(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setApellidos(rs.getString(3));
                p.setMail(rs.getString(4));
                lstpersona.add(p);
            }
        } catch (Exception e) {
        }
        return lstpersona;
    }

    public int crear(persona p) {

        String sql = "insert into persona(num_doc, nombre, apellidos, mail) values(?,?,?,?)";

        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getNum_doc());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getApellidos());
            ps.setString(4, p.getMail());
            ps.executeUpdate();
        } catch (Exception e) {
        }

        return 1;
    }

    public int actualizar(persona p) {
        int r=0;
        String sql = "update persona set num_doc = ?, nombre = ?, apellidos = ?, mail = ? where num_doc = ?";

        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getNum_doc());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getApellidos());
            ps.setString(4, p.getMail());
            ps.setInt(5, p.getNum_doc());
            r = ps.executeUpdate();
            if(r==1){
              return 1;
            }else{
              return 0;
            }
        } catch (Exception e) {
        }

        return r;
    }

    public void eliminar(int num_doc){

     String sql = "delete from persona where num_doc = " + num_doc;

        try {
             con = conectar.conectar();
             ps = con.prepareStatement(sql);
             ps.executeUpdate();
        } catch (Exception e) {
        }

    }

}
