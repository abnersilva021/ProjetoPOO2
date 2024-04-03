/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.swing.JOptionPane;

import model.UsuarioDAO;

/**
 *
 * @author aluno.saolucas
 */
public class UsuarioController {
    
    private UsuarioDAO usuarioDao;
    
    public UsuarioController(){
        usuarioDao = new UsuarioDAO();
        
    }
    
    public boolean autenticar(String email, String senha){
        if(usuarioDao.autenticar(email, senha) == true){
            return true;
        }else{
            JOptionPane.showMessageDialog(null,"usuario ou senha incorreta");
            return false;
        }
    }
}
