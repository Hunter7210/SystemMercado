package View;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Connection.ClientesDAO;
import Controller.CadClientControl;
import Controller.LimitaCaracteres;
import Model.Clientes;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

public class PainelCadClient extends JPanel {

    private JLabel tit, cpf;
    private JTextField inptCPF;
    private JButton enviar;
    private JPanel paiPrinc, paicont, paiCPF;

    private List<Clientes> clientes;
    private DefaultTableModel modeloTableClien;
    private JTable tabelaClien;

    public PainelCadClient() {
        // Atributos

        GridLayout grid3x1 = new GridLayout(4, 1);
        GridLayout grid2x1 = new GridLayout(2, 1);

        paiPrinc = new JPanel();
        this.add(paiPrinc);

        tit = new JLabel("CADASTRO");

        paicont = new JPanel();
        paicont.setLayout(grid3x1);
        paiPrinc.add(paicont);

        paiCPF = new JPanel();
        paiCPF.setLayout(grid2x1);
        cpf = new JLabel("Digite o CPF:");
        paiCPF.add(cpf);
        inptCPF = new JTextField();
        paiCPF.add(inptCPF);

        enviar = new JButton("Cadastrar");

        paicont.add(tit);
        paicont.add(paiCPF);
        paicont.add(enviar);

         // Atribuindo o limitador de caracteres a cada um dos meus Inputs com os
        // paramêtros de qtdCaracteres e o TipoEntrada
        inptCPF.setDocument(new LimitaCaracteres(14, LimitaCaracteres.TipoEntrada.CPF));
        
        List<Clientes> clientes = new ArrayList<>();
        DefaultTableModel modeloTableClien = new DefaultTableModel();
        JTable tabelaClien = new JTable(modeloTableClien);

        new ClientesDAO().criarTabela();

        CadClientControl cadastroClient = new CadClientControl(clientes, modeloTableClien, tabelaClien);
        // Chamando a ação la da pagina cadGeralControl
        cadastroClient.cadastrar(enviar, inptCPF, this);

    }
}
