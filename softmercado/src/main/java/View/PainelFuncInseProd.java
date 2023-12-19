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

    private JLabel tit, qtdPrd, valorFinal, valorTot;
    private JComboBox<String> produt;
    private JTextField qtdVend /* valorCompra */;
    private JButton inserirVenda, limpar;
    private JPanel painelPrinc, painelQtd, painelValor;
    private List<Vendas> vendas;
    private List<Produtos> produtos;
    private JTable tabelarVend;
    private DefaultTableModel modeloTableRegis;

    public PainelFuncInseProd() {

        GridLayout grid1x2 = new GridLayout(1,2);
        GridLayout grid2x1 = new GridLayout(2, 1);
        painelPrinc = new JPanel();
        this.add(painelPrinc);

        painelPrinc.setLayout(grid2x1);
        tit = new JLabel("Preencha os campos e insira o produto");

        produt = new JComboBox<>();

        tit = new JLabel("Realize sua compra");
        valorTot = new JLabel("TOTAL (R$): ");
        valorFinal = new JLabel("");
        qtdPrd = new JLabel("Quantidade de produtos:");
        qtdVend = new JTextField("");

        produtos = new ProdutoDAO().listartodos();
        produt.addItem("Selecionar o produto");

        // Preenche o comboBox
        for (Produtos produto : produtos) {
            produt.addItem(produto.getNome() + " " + produto.getCodigoBarra());
        }

        painelQtd = new JPanel(grid2x1);
        painelQtd.add(qtdPrd);
        painelQtd.add(qtdVend);

        painelValor = new JPanel(grid1x2);
        painelValor.add(valorTot);
        painelValor.add(valorFinal);
        // Adiciona os componentes
        painelPrinc.add(produt);
        painelPrinc.add(painelQtd);
        painelPrinc.add(painelValor); 
       
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

        

        modeloTableRegis = new DefaultTableModel(new Object[][] {},
                new String[] { "datavenda", "quantVendi", "codProd", "valorCompra" });
        tabelarVend = new JTable(modeloTableRegis);
        jSPane.setViewportView(tabelarVend);

        // Criar tabela vendas
        new VendasDAO().criarTabela();
        atualizarTabela();

        ClienInsProdControl controlInserProd = new ClienInsProdControl();
        controlInserProd.mostrarValorTot(valorFinal, qtdVend, produt, this);
        
        controlInserProd.cadastrar(inserirVenda, produt, qtdVend, this);
        controlInserProd.limparCombo(produt);
        

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

    }

}