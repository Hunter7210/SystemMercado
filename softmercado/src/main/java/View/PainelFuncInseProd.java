package View;

import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Connection.ProdutoDAO;
import Connection.VendasDAO;
import Controller.ClienInsProdControl;
import Model.Clientes;
import Model.Produtos;
import Model.Vendas;
/* import logs.RegistroSistema; */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

/**
 * PainelClienInsProd
 */
public class PainelFuncInseProd extends JPanel {

    private JLabel tit, valorFinal;
    private JComboBox<String> produt;
    private JTextField qtdVend, valorCompra;
    private JButton inserirVenda, limpar;
    private JPanel painelPrinc;
    private List<Vendas> vendas;
    private List<Produtos> produtos;
    private JTable tabelarVend;
    private DefaultTableModel modeloTableRegis;

    public PainelFuncInseProd() {

        GridLayout grid2x1 = new GridLayout(2, 1);
        painelPrinc = new JPanel();
        this.add(painelPrinc);

        painelPrinc.setLayout(grid2x1);
        tit = new JLabel("Preencha os campos e insira o produto");

        produt = new JComboBox<>();

        qtdVend = new JTextField("");
        valorCompra = new JTextField("");

        produtos = new ProdutoDAO().listartodos();
        produt.addItem("Selecionar o produto");

        // Preenche o comboBox
        for (Produtos produto : produtos) {
            produt.addItem(produto.getNome() + " " + produto.getCodigoBarra());
        }

        // Adiciona os componentes
        painelPrinc.add(produt);
        painelPrinc.add(qtdVend);
        painelPrinc.add(valorCompra);

        // Criação de um painel para conter os botoes
        JPanel botoes = new JPanel();
        inserirVenda = new JButton("Realizar Venda");

        limpar = new JButton("Limpar");
        painelPrinc.add(botoes);

        botoes.add(inserirVenda);
        botoes.add(limpar);

        // tabela de carros
        JScrollPane jSPane = new JScrollPane();
        add(jSPane);

        valorFinal = new JLabel();

        modeloTableRegis = new DefaultTableModel(new Object[][] {},
                new String[] { "datavenda", "quantVendi", "codProd", "valorCompra" });
        tabelarVend = new JTable(modeloTableRegis);
        jSPane.setViewportView(tabelarVend);

        // Criar tabela vendas
        new VendasDAO().criarTabela();

        ClienInsProdControl controlInserProd = new ClienInsProdControl();

        controlInserProd.cadastrar(inserirVenda, produt, qtdVend);
        atualizarTabela();

    }

    public void atualizarTabela() {

        vendas = new VendasDAO().listarVendas();
        // Obtém os carros atualizados do banco de dados
        for (Vendas venda : vendas) {
            // Adiciona os dados de cada carro como uma nova linha na tabela Swing
            modeloTableRegis.addRow(new Object[] {
                    venda.getDataVenda(), venda.getQuantVendi(), venda.getCodProd(),
                    venda.getValorCompra()
            });
        }
        /*
         * modeloTableRegis.setRowCount(0); // Limpa todas as linhas existentes na
         * tabela
         */
    }

}