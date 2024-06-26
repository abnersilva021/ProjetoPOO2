/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.List;
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
    
    public boolean alterarUsuario(Usuario u, Long pk){
        if(usuarioDAO.alterarUsuario(u, pk)){
            return false;
            
        }else{
            JOptionPane.showMessageDialog(null, "Usuario não cadastrado");
            return false;
        }
    }
    //abner
    public List<Usuario> readForDesc(int tipo, String desc){
        return usuarioDAO.readForDesc(tipo, desc);
    }
    public Usuario readForPk(long pk){
      Usuario usu = usuarioDAO.readForPk(pk);
      if(usu == null){
          JOptionPane.showMessageDialog(null, "Usuario não encontrado");
          return null;
      }
      return usu;
    }
   public boolean excluirUsuario(int pkUsuario){
       if(usuarioDAO.excluirUsuario(pkUsuario)){
           return false;
           
       }else{
           JOptionPane.showMessageDialog(null, "Usuario nao excluido");
           return false;
       }
   }
           
}
