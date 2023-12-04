package Controller;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Clientes;

public class CadClientControl {
    

    private List<Clientes> clientes;
    private DefaultTableModel modeloTableClien;
    private JTable tabelaClien;


    public CadClientControl(List<Clientes> clientes, DefaultTableModel modeloTabliClien ,JTable tabebelaClien) {
        this.clientes = clientes;
        this.modeloTableClien = modeloTabliClien;
        this.tabelaClien = tabebelaClien;
    }
}
