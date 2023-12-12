package Controller;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Connection.ClientesDAO;
import Model.Clientes;
import javafx.event.ActionEvent;

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

        for(Clientes cliente : clientes){

            modeloTableClien.addRow(new Object[]{
                cliente.getCpf(),
                cliente.getNome() 
            });
        }
    }

    public void cadastrar(String cpf, String nome) {
        new ClientesDAO().cadastrar(nome, cpf);
        atualizarTableClie();
    }


    public void atualizar(String cpf, String nome) {
        new ClientesDAO().atualizar(nome, cpf);
    
        atualizarTableClie();
    }


    public void apagar(String cpf) {
        new ClientesDAO().apagar(cpf);

        atualizarTableClie();
    }

    public void verificar(String nome, String cpf) {
        new ClientesDAO().verificar(nome, cpf);

        atualizarTableClie();
    }


  
}
