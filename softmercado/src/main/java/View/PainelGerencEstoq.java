package View;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Connection.ProdutoDAO;
import Connection.VendasDAO;
import Controller.EscolGerenControl;
import Model.Clientes;
import Model.Produtos;
import Model.Vendas;

public class PainelGerencEstoq extends JPanel {

    private JLabel tit;
    private JComboBox<String> codProd, codLote, codVenci;
    private JButton btnFiltrar, limpar;
    private List<Produtos> produtos;
    private List<Clientes> clientes;
    private List<Vendas> vendas;

    private JTable tabelaGerencEstoq;
    private DefaultTableModel modeloTableGerenc;

    public PainelGerencEstoq() {
        JPanel painelPrinc = new JPanel();
        add(painelPrinc);

        // Listar carros cadastrados no JCombobox
        codProd = new JComboBox<>();

        // Listar clientes cadastrados no JCombobox
        codLote = new JComboBox<>();

        codVenci = new JComboBox<>();

        // Preencha o JComboBox com os campos
        // Carros
        produtos = new ProdutoDAO().listartodos();
        codProd.addItem("Selecionar o Produto");
        // Clientes
        produtos = new ProdutoDAO().listartodos();
        codLote.addItem("Selecionar o Lote");

        produtos = new ProdutoDAO().listartodos();
        codVenci.addItem("Selecionar o Periodo de vencimento");

        // Preenche o comboBox
        for (Produtos produto : produtos) {
            codProd.addItem(produto.getNome() + " " + produto.getCodigoBarra());

            codLote.addItem(produto.getLote());

            codVenci.addItem(produto.getDataVenc());

        }

        // Adiciona os componentes
        painelPrinc.add(codProd);
        painelPrinc.add(codLote);
        painelPrinc.add(codVenci);

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

        modeloTableGerenc = new DefaultTableModel(new Object[][] {},
                new String[] { "Lote", "Nome/Codigo Produto", "Quantidade Restante", "Data de entrega",
                        "Data Vencimento" });
        tabelaGerencEstoq = new JTable(modeloTableGerenc);
        jSPane.setViewportView(tabelaGerencEstoq);

        // Criar tabela vendas
        new VendasDAO().criarTabela();

        EscolGerenControl controlGerencEstoq = new EscolGerenControl();
    
        controlGerencEstoq.limparCombo(limpar, codProd, codLote, codVenci);
        
        controlGerencEstoq.filtrarTabela(btnFiltrar);



        // Atualizar os dados da tabela

        // Instanciando um obj da classe VendasConstrol
        /*
         * VendasControl operacoesVend = new VendasControl(vendas, modeloTableGerenc,
         * tabelaGerencEstoq);
         */
        // Buscando o item selecionado no comboBox
    }

}
