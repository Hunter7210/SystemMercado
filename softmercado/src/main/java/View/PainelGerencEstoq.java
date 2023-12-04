package View;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Clientes;
import Model.Produtos;
import Model.Vendas;

public class PainelGerencEstoq {

    private JLabel tit;
    private JComboBox<String> codProd, codLote, codVenci;
    private JButton btnFiltrar;
    private List<Produtos> produtos;
    private List<Clientes> clientes;
    private List<Vendas> vendas;
    
    private JTable tabelaGerencEstoq;
    private DefaultTableModel modeloTableGerenc;
}
