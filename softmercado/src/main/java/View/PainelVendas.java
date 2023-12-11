package View;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Connection.ClientesDAO;
import Connection.ProdutoDAO;
import Connection.VendasDAO;
import Controller.VendasControl;
import Model.Clientes;
import Model.Produtos;
import Model.Vendas;

public class PainelVendas extends JPanel {

    private JLabel tit;
    private JComboBox<String> codProd, codPeri;
    private JButton btnFiltrar, limpar;
    private List<Clientes> clientes;
    private List<Produtos> produtos;
    private List<Vendas> vendas;

    private JTable tabelarRegisVend;
    private DefaultTableModel modeloTableRegis;

    public PainelVendas() {
        super();

        JPanel painelPrinc = new JPanel();
        add(painelPrinc);

        // Listar carros cadastrados no JCombobox
        codProd = new JComboBox<>();
        
        // Listar clientes cadastrados no JCombobox
        codPeri = new JComboBox<>();

        // Preencha o JComboBox com os campos
        // Carros
        produtos = new ProdutoDAO().listartodos();
        codProd.addItem("Selecionar o produto");
        // Clientes
        vendas = new VendasDAO().listarVendas();
        codPeri.addItem("Selecionar o Periodo");

        // Preenche o comboBox
        for (Produtos produto : produtos) {
            codProd.addItem(produto.getNome()+ " " + produto.getCodigoBarra());
        }

        for (Vendas venda : vendas) {
            codPeri.addItem(venda.getDataVenda());
        }

        // Adiciona os componentes
        painelPrinc.add(codProd);
        painelPrinc.add(codPeri);

        // Criação de um painel para conter os botoes
        JPanel botoes = new JPanel();
        btnFiltrar = new JButton("Filtrar");

        limpar = new JButton("Limpar");
        painelPrinc.add(botoes);

        botoes.add(btnFiltrar);
        botoes.add(limpar);

        // tabela de carros
        JScrollPane jSPane = new JScrollPane();
        add(jSPane);

        modeloTableRegis = new DefaultTableModel(new Object[][] {},
                new String[] { "Data Venda", "Carro Vendido", "Cliente" });
        tabelarRegisVend = new JTable(modeloTableRegis);
        jSPane.setViewportView(tabelarRegisVend);

        // Criar tabela vendas
        new VendasDAO().criarTabela();
    }

    /*     // Atualizar os dados da tabela
        atualizarTabela();
 */
       /*  // Instanciando um obj da classe VendasConstrol
        VendasControl operacoesVend = new VendasControl(vendas, modeloTableRegis, tabelarRegisVend);
        // Buscando o item selecionado no comboBox
 */

    private void limparCombo(Object combo1, Object combo2) {
        codProd.setSelectedIndex(0);
        codPeri.setSelectedIndex(0);
    }
/* 
    // Método para atualizar a tabela de exibição com dados do banco de dados
    private void atualizarTabela() {
        modeloTableRegis.setRowCount(0); // Limpa todas as linhas existentes na tabela
        vendas = new VendasDAO().listarVendas();
        // Obtém as vendas atualizados do banco de dados
        for (Vendas venda : vendas) {
            // Adiciona os dados de cada venda como uma nova linha na tabela Swing
            modeloTableRegis.addRow(new Object[] { venda.getDataVenda(), venda.getCliente(), venda.getCarroVendi(),
                    venda.getValorCompra() });
        }
    }
 */
}
