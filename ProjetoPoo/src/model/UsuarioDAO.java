/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author aluno.saolucas
 */
public class UsuarioDAO {

    public boolean autenticar(String email, String senha) {
        String sql = "SELECT * from TBUSUARIO WHERE email = ? and senha = ? and ativo = true";

        GerenciadorConexao gerenciador = new GerenciadorConexao();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            gerenciador.closeConnection(stmt, rs);
        }
        return false;
    }

    public boolean adicionarUsuario(Usuario u) {
        String sql = "INSERT into TBUSUARIO (Nome, email, senha, dataNasc, ativo) VALUES (?,?,?,?,?)";

        GerenciadorConexao gerenciador = new GerenciadorConexao();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenha());
            stmt.setDate(4, new java.sql.Date(u.getDataNasc().getTime()));
            stmt.setBoolean(5, u.isAtivo());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario:" + u.getNome() + "insert com sucesso");
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO:" + e.getMessage());

        } finally {
            gerenciador.closeConnection(stmt);
        }
        return false;
    }

    public boolean alterarUsuario(Usuario u, Long pk) {
        String sql = "UPDATE TBUSUARIO SET Nome = ?, email = ?, senha = ?, dataNasc = ?, ativo = ? WHERE pkUsuario = ?";

        GerenciadorConexao gerenciador = new GerenciadorConexao();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenha());
            stmt.setDate(4, new java.sql.Date(u.getDataNasc().getTime()));
            stmt.setBoolean(5, u.isAtivo());
            stmt.setLong(6, pk);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario:" + u.getNome() + "insert com sucesso");
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO:" + e.getMessage());

        } finally {
            gerenciador.closeConnection(stmt);
        }
        return false;
    }

    public List<Usuario> readForDesc(int tipo, String desc) {
        String sql = "SELECT * FROM tbusuario ";
        if (!desc.equals("")) {
            if (tipo == 0 || tipo == 1) {
                sql = sql + "WHERE nome LIKE ?";
            } else {
                sql = sql + "WHERE email LIKE ?";
            }
        }
        GerenciadorConexao gerenciador = new GerenciadorConexao();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            if (!desc.equals("")) {
                if (tipo == 0 || tipo == 2) {
                    stmt.setString(1, desc + "%");
                } else {
                    stmt.setString(1, "%" + desc + "%");
                }
            }
            rs = stmt.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setPkUsuario(rs.getLong("pkusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("senha"));
                usuario.setDataNasc(rs.getDate("datanasc"));
                usuario.setAtivo(rs.getBoolean("ativo"));
                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            gerenciador.closeConnection(stmt, rs);

        }
        return usuarios;
    }

    public Usuario readForPk(long pk) {

        String sql = "SELECT * FROM tbusuario WHERE pkusuario = ?";
        GerenciadorConexao gerenciador = new GerenciadorConexao();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = new Usuario();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, pk);
            rs = stmt.executeQuery();
            if (rs.next()) {

                usuario.setPkUsuario(rs.getLong("pkusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("Email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setDataNasc(rs.getDate("datanasc"));
                usuario.setAtivo(rs.getBoolean("ativo"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            gerenciador.closeConnection(stmt, rs);

        }
        return usuario;
    }

    public boolean excluirUsuario(int pkUsuario) {
        String sql = "DELETE FROM tbusuario" + "WHERE pkusuario = ?";

        GerenciadorConexao gerenciador = new GerenciadorConexao();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, pkUsuario);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Usuario excluido com sucesso!");
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ap excluir" + ex);
        } finally {
            gerenciador.closeConnection(stmt);
        }
        return false;

    }
}
