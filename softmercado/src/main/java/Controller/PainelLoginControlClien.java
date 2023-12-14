package Controller;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import Connection.ClientesDAO;
import Model.Clientes;

public class PainelLoginControlClien {

    public void verificarUsuario(JButton btnAcion, JTextField textarea1) {

        btnAcion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClientesDAO().buscarUsuario(textarea1.getText());
               
                new ClientesDAO().listarTodos();
                
              /*   
                if(textarea1.getText() != ){

                } */

            }
        });

    }
}
