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
import javax.swing.JOptionPane;

/**
 *
 * @author aluno.saolucas
 */
public class UsuarioDAO {
    public boolean autenticar(String email, String senha){
        String sql = "SELECT * from TBUSUARIO WHERE email = ? and senha = ? and ativo = true";
        
        GerenciadorConexao gerenciador = new GerenciadorConexao();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            gerenciador.closeConnection(stmt, rs);
        }
        return false;
    }
}
