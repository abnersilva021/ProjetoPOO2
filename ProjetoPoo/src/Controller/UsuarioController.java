/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.swing.JOptionPane;
import model.Usuario;

import model.UsuarioDAO;

/**
 *
 * @author aluno.saolucas
 */
public class UsuarioController {
    
    private UsuarioDAO usuarioDAO;
    
    public UsuarioController(){
        usuarioDAO = new UsuarioDAO();
        
    }
    
    public boolean autenticar(String email, String senha){
        if(usuarioDAO.autenticar(email, senha) == true){
            return true;
        }else{
            JOptionPane.showMessageDialog(null,"usuario ou senha incorreta");
            return false;
        }
    }
    
    public boolean adicionarUsuario(Usuario u){
        if(usuarioDAO.adicionarUsuario(u)){
            return false;
            
        }else{
            JOptionPane.showMessageDialog(null, "Usuario não cadastrado");
            return false;
        }
    }
}
