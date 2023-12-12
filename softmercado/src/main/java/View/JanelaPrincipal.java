package View;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import Controller.ControlCards;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;


public class JanelaPrincipal extends JFrame {

    private JPanel painelPrinc, painelLateral, paineDisplay;
    private JLabel telaInic, cadastGer, cadasClie, cadasProdGere, club, querCadas, loginClien, estoq, compra, regisVend;

    public JanelaPrincipal() {

        BorderLayout borderLa = new BorderLayout();
        painelPrinc = new JPanel();
        painelPrinc.setLayout(borderLa);
        this.add(painelPrinc);

        // Painel para conter os cards

        CardLayout card = new CardLayout();

        JPanel paineDisplay = new JPanel();
        paineDisplay.setLayout(card);
        painelPrinc.add(paineDisplay, BorderLayout.CENTER);

        // Implementando e criando os cards
        PainelInical inicio = new PainelInical();
        paineDisplay.add(inicio, "Tela Inicial");

        PainelCadGeral cadastroGeral = new PainelCadGeral();
        paineDisplay.add(cadastroGeral, "Cadastro Geral");

        PainelCadFunc cadasClien = new PainelCadFunc();
        paineDisplay.add(cadasClien, "Cadastrar Cliente");

        PainelCadProd cadasProd = new PainelCadProd();
        paineDisplay.add(cadasProd, "Cadastrar Produto");

        PainelCaixInic escolhaCai = new PainelCaixInic();
        paineDisplay.add(escolhaCai, "Clube");

        PainelEscolCaix querCadastr = new PainelEscolCaix();
        paineDisplay.add(querCadastr, "Quer se Cadastrar");

        PainelLoginFunc login = new PainelLoginFunc();
        paineDisplay.add(login, "Faça o login");

        PainelGerencEstoq estoque = new PainelGerencEstoq();
        paineDisplay.add(estoque, "Estoque");

        PainelFuncInseProd comprasClien = new PainelFuncInseProd();
        paineDisplay.add(comprasClien, "Compra");

        PainelVendas vendasRegistro = new PainelVendas();
        paineDisplay.add(vendasRegistro, "Vendas Registro");

        // Criando e Adicionando componentes ao SplitPane
        JPanel painelcompo = new JPanel();

        telaInic = new JLabel("Inicio");
        cadastGer = new JLabel("Login Geral");
        cadasClie = new JLabel("Cadastrar Cliente");
        cadasProdGere = new JLabel("Cadastrar Produto");
        club = new JLabel("Clube");
        querCadas = new JLabel("Quer Cadastrar");
        loginClien = new JLabel("Login");
        estoq = new JLabel("Estoque");
        compra = new JLabel("Compra");
        regisVend = new JLabel("Registro Vendas");

        painelcompo.add(telaInic);
        painelcompo.add(cadastGer);
        painelcompo.add(cadasClie);
        painelcompo.add(cadasProdGere);
        painelcompo.add(club);
        painelcompo.add(querCadas);
        painelcompo.add(loginClien);
        painelcompo.add(estoq);
        painelcompo.add(compra);
        painelcompo.add(regisVend);

        GridLayout grid10x1 = new GridLayout(10, 1);

        painelLateral = new JPanel();
        painelLateral.setBackground(Color.BLUE);
        painelLateral.add(painelcompo);
        painelcompo.setLayout(grid10x1);

        painelPrinc.add(painelLateral, BorderLayout.WEST);

        // Criando um JSplitPane com os dois painéis
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painelLateral, painelPrinc);
        // Definindo a divisão inicial
        split.setDividerLocation(120);
        /* this.add(splitPane); */

        add(split);
        setBounds(200, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Criando um contrutor do controler
        ControlCards ControleCards = new ControlCards();

        // Metodo para mudar as paginas
        //Mudando da pagina inicial para a pagina de Cadastro Geral
        ControleCards.trocarPagina(card, cadastGer, paineDisplay, "Cadastro Geral");




    }

    public void run() {
        this.setVisible(true);
    }

}