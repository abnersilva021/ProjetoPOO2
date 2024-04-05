/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
//
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author aluno.saolucas
 */
public class GerenciadorConexao {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/projetopoo2";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private Connection conexao;

    public GerenciadorConexao() {
        try {
            conexao = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e.getMessage().toString());
        }
    }

    public Connection getConexao() {
        return conexao;

    }

    public void closeConnection() {
        try {
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection(PreparedStatement stmt) {
        closeConnection();

        try {
            if (stmt != null);
            stmt.close();

        }
    
    catch (SQLException ex

    
        ){
    Logger.getLogger(GerenciadorConexao.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    public void closeConnection(PreparedStatement stmt, ResultSet rs) {
        closeConnection(stmt);

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
