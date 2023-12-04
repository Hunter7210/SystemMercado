package View;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Connection.ProdutoDAO;
import Model.Clientes;
import Model.Produtos;
import Model.Vendas;

public class PainelVendas extends JPanel{
    
    private JLabel tit;
    private JComboBox<String> codProd, codPeri;
    private JButton btnFiltrar;
    private List<Clientes> clientes;
    private List<Produtos> produtos;
    private List<Vendas> vendas;
    
    private JTable tabelarRegisVend;
    private DefaultTableModel modeloTableRegis;

}
