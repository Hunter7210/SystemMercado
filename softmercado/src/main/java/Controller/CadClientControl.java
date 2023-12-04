package Controller;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Connection.ClientesDAO;
import Model.Clientes;

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
    }
}
