/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import conexiones.BDConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.Usuario;

/**
 *
 * @author anyi4
 */
public class UsuarioControlador {
    
    public Usuario obtenerUsuarioPorId(int idUsuario) {
        Usuario usuario = null;
        String query = "SELECT * FROM usuario WHERE id_usuario = ?";
        try (Connection con = BDConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(); 
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
    public boolean insertarUsuario(Usuario usuario) {
        String query = "INSERT INTO usuario (nombre, apellidos) VALUES (?, ?)";
        try (Connection con = BDConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidos());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void saludarOInsertarUsuario(int idUsuario, String nombre, String apellidos) {
        Usuario usuario = obtenerUsuarioPorId(idUsuario);
        if (usuario != null) {
            System.out.println("Hola " + usuario.getNombre() + " " + usuario.getApellidos());
        } else {
            Usuario nuevoUsuario = new Usuario(0, nombre, apellidos);
            if (insertarUsuario(nuevoUsuario)) {
                System.out.println("Usuario insertado correctamente. Hola " + nombre + " " + apellidos);
            } else {
                System.out.println("Error al insertar el usuario.");
            }
        }
    }
    public void listarTodosLosUsuarios() {
        String query = "SELECT * FROM usuario";
        try (Connection con = BDConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<Usuario> usuarios = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuarios.add(usuario);
            }
            for (Usuario usuario : usuarios) {
                System.out.println("ID: " + usuario.getId_usuario() + ", Nombre: " + usuario.getNombre() + ", Apellidos: " + usuario.getApellidos());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
