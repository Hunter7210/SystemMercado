package View;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class JanelaPrincipal extends JFrame {


    public JanelaPrincipal() {
    
        JTabbedPane abas = new JTabbedPane();

        this.add(abas);
        PainelInical inicio = new PainelInical();
        abas.add(inicio, "Tela Inicial");

        PainelCadGeral cadastroGeral = new PainelCadGeral();
        abas.add(cadastroGeral, "Cadastro Geral");
        
        PainelCadFunc cadasClien = new PainelCadFunc();
        abas.add(cadasClien, "Cadastrar Cliente");
        
        PainelCadProd cadasProd = new PainelCadProd();
        abas.add(cadasProd, "Cadastrar Produto");

        PainelCaixInic escolhaCai = new PainelCaixInic();
        abas.add(escolhaCai, "Clube");
                
        PainelEscolCaix querCadastr = new PainelEscolCaix();
        abas.add(querCadastr, "Quer se Cadastrar");
        
        PainelLoginFunc login = new PainelLoginFunc();
        abas.add(login, "Faça o login");
        
        PainelGerencEstoq estoque = new PainelGerencEstoq();
        abas.add(estoque, "Estoque");
        
        PainelFuncInseProd comprasClien = new PainelFuncInseProd();
        abas.add(comprasClien, "Compra");
        
        PainelVendas vendasRegistro = new PainelVendas();
        abas.add(vendasRegistro, "Vendas Registro");

        setBounds(200, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void run() {
        this.setVisible(true);
    }

}