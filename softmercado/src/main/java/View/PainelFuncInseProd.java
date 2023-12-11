package View;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Connection.ProdutoDAO;
import Connection.VendasDAO;
import Model.Clientes;
import Model.Produtos;
import Model.Vendas;

import java.awt.GridLayout;

/**
 * PainelClienInsProd
 */
public class PainelFuncInseProd extends JPanel {

    private JLabel tit, valorFinal;
    private JComboBox<String> produt;
    private JTextField qtdVend;
    private JButton inserirVenda, limpar;
    private List<Produtos> produtos;
    private JPanel painelPrinc;

    private JTable tabelarVend;
    private DefaultTableModel modeloTableVend;

    public PainelFuncInseProd() {

        GridLayout grid2x1 = new GridLayout(2,1);
        painelPrinc = new JPanel();
        this.add(painelPrinc);

        painelPrinc.setLayout(grid2x1);
        tit = new JLabel("Preencha os campos e insira o produto");

        produt = new JComboBox<>();

        qtdVend = new JTextField();

        produtos = new ProdutoDAO().listartodos();
        produt.addItem("Selecionar o produto");

        // Preenche o comboBox
        for (Produtos produto : produtos) {
            produt.addItem(produto.getNome() + " " + produto.getCodigoBarra());
        }

        // Adiciona os componentes
        painelPrinc.add(produt);
        painelPrinc.add(qtdVend);

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

        modeloTableVend = new DefaultTableModel(new Object[][] {},
                new String[] { "Excluir", "Nome/Codigo Produto", "Quantidade", "Valor Unitário", "Valor" });
        tabelarVend = new JTable(modeloTableVend);
        jSPane.setViewportView(tabelarVend);

        // Criar tabela vendas
        new VendasDAO().criarTabela();

        // Criar nos controllers a parte de pagamento com o JOptionpane

    }

}