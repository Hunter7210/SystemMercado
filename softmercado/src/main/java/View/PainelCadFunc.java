package View;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.CadGeralControl;

import java.awt.GridLayout;

public class PainelCadFunc extends JPanel {

    private JLabel tit, usuario, senha;
    private JTextField inptUsua, inptSen;
    private JButton enviar;
    private JPanel paiPrinc, paicont, paiNome, paiCPF;

    public PainelCadFunc() {
        // Atributos

        GridLayout grid3x1 = new GridLayout(4, 1);
        GridLayout grid2x1 = new GridLayout(2, 1);

        paiPrinc = new JPanel();
        this.add(paiPrinc);

        tit = new JLabel("Qual seu cargo");

        paicont = new JPanel();
        paicont.setLayout(grid3x1);
        paiPrinc.add(paicont);

        paiNome = new JPanel();
        paiNome.setLayout(grid2x1);
        usuario = new JLabel("Digite seu usuario:");
        paiNome.add(usuario);
        inptUsua = new JTextField("Usuario");
        paiNome.add(inptUsua);

        paiCPF = new JPanel();
        paiCPF.setLayout(grid2x1);
        senha = new JLabel("Digite sua senha:");
        paiCPF.add(senha);
        inptSen = new JTextField("Senha");
        paiCPF.add(inptSen);

        enviar = new JButton("Entrar");

        paicont.add(tit);
        paicont.add(paiNome);
        paicont.add(paiCPF);
        paicont.add(enviar);

        this.setVisible(true);
        enviar = new JButton();
        CadGeralControl cadastroGeral = new CadGeralControl();

        // Chamando a ação la da pagina cadGeralControl
        cadastroGeral.Adicionar(enviar);

    }
}
