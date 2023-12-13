package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Connection.ClientesDAO;
import Model.Clientes;
import logs.RegistroSistema;

public class CadClientControl {

    private List<Clientes> clientes;
    private DefaultTableModel modeloTableClien;
    private JTable tabelaClien;

    public CadClientControl(List<Clientes> clientes, DefaultTableModel modeloTableClien, JTable tabelaClien) {
        this.clientes = clientes;
        this.modeloTableClien = modeloTableClien;
        this.tabelaClien = tabelaClien;
    }

    public void atualizarTableClie() {
        modeloTableClien.setRowCount(0); // Limpa todas as linhas existentes na tabela

        clientes = new ClientesDAO().listarTodos();

        for (Clientes cliente : clientes) {

            modeloTableClien.addRow(new Object[] {
                    cliente.getCpf()
            });
        }
    }

    public void cadastrar(JButton btnAciona, JTextField inptTexto) {

        btnAciona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!inptTexto.getText().isEmpty()) {

            
        

                        new ClientesDAO().cadastrar(inptTexto.getText());
                        RegistroSistema.registroOperacao("Usuario se cadastrou com o CPF " + inptTexto.getText());

                        atualizarTableClie();
                        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
                        inptTexto.setText("");
                    /* } else {
                        JOptionPane.showMessageDialog(null, "Cliente já esta cadastrado");
                    } */
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor preencha todos os campos");
                }

            }
        });

    }

    /*
     * public void atualizar(String cpf, String nome) {
     * new ClientesDAO().atualizar(nome, cpf);
     * 
     * atualizarTableClie();
     * }
     */

    public void apagar(JButton btnAciona, String cpf) {

        btnAciona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Pergunta se o usuario quer realmente se cadastrar
                int podCadast = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja apagar os dados?",
                        "Escolha uma opção", JOptionPane.YES_NO_OPTION);
                // Verifica se a escolha foi YES
                if (podCadast == JOptionPane.YES_OPTION) {

                    new ClientesDAO().apagar(cpf);
                    RegistroSistema.registroOperacao("Usuario do CPF: " + cpf + "apagou os dados");

                    atualizarTableClie();
                    JOptionPane.showMessageDialog(null, "Apagou os dados com sucesso!");

                } else {
                    // Fecha o JOptionPane automaticamente
                }
            }
        });
    }

    public void buscarUsuario(String cpf) {
        new ClientesDAO().buscarUsuario(cpf);

        RegistroSistema.registroOperacao("Usuario do CPF: " + cpf + "buscou os dados");

        atualizarTableClie();
    }

}
