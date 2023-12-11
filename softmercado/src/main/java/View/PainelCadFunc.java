package View;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.CadGeralControl;

import java.awt.GridLayout;

public class PainelCadFunc extends JPanel {

    private JLabel tit, senha;
    private JTextField inptSen;
    private JButton enviar;
    private JPanel paiPrinc, paicont, paiCPF;

    public PainelCadFunc() {
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
        senha = new JLabel("Digite o CPF:");
        paiCPF.add(senha);
        inptSen = new JTextField("CPF");
        paiCPF.add(inptSen);

        enviar = new JButton("Cadastrar");

        paicont.add(tit);
        paicont.add(paiCPF);
        paicont.add(enviar);

        this.setVisible(true);
        enviar = new JButton();
        CadGeralControl cadastroGeral = new CadGeralControl();

        // Chamando a ação la da pagina cadGeralControl
        cadastroGeral.Adicionar(enviar);

    }
}
