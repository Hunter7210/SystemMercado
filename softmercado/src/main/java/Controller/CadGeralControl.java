package Controller;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Connection.ClientesDAO;
import Model.Clientes;
import javafx.event.ActionEvent;
import logs.RegistroSistema;

public class CadGeralControl {

    private List<Clientes> clientes;
    private DefaultTableModel modeloTableClien;
    private JTable tabelaClien;

    private JButton btnEnvi;

    public CadGeralControl(List<Clientes> clientes, DefaultTableModel modeloTableClien, JTable tabelaClien,
            JButton btnEnvi) {
        this.clientes = clientes;
        this.modeloTableClien = modeloTableClien;
        this.tabelaClien = tabelaClien;
        this.btnEnvi = btnEnvi;
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

    public void cadastrar(JButton btnAciona, JTextField inptTexto, JPanel painelMostra) {
        btnAciona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // TODO Auto-generated method stub

                if (!inptTexto.getText().isEmpty()) {

                    new ClientesDAO().cadastrar(inptTexto.getText());
                    RegistroSistema.registroOperacao("Usuario se cadastrou com o CPF " + inptTexto.getText());

                    atualizarTableClie();
                    JOptionPane.showMessageDialog(painelMostra, "Cliente cadastrado com sucesso!");
                    inptTexto.setText("");
                } else {
                    JOptionPane.showMessageDialog(painelMostra, "Por favor preencha todos os campos");
                }

            }
        });

    }

}
