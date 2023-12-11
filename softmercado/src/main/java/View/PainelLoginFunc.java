package View;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.CadGeralControl;

public class PainelLoginFunc extends JPanel {

    // Atributos
    private JLabel tit, cpf;
    private JTextField inptCpf;
    private JButton btnEnvi;
    private JPanel painelPrincipal, painelConterComp, painelCPF;

    public PainelLoginFunc() {
        super();

        GridLayout grid3x1 = new GridLayout(4, 1);
        GridLayout grid2x1 = new GridLayout(2, 1);

        painelPrincipal = new JPanel();
        this.add(painelPrincipal);

        tit = new JLabel("Qual seu cargo");

        painelConterComp = new JPanel();
        painelConterComp.setLayout(grid3x1);
        painelPrincipal.add(painelConterComp);


        painelCPF = new JPanel();
        painelCPF.setLayout(grid2x1);
        cpf = new JLabel("Digite seu cpf:");
        painelCPF.add(cpf);
        inptCpf = new JTextField("Senha");
        painelCPF.add(inptCpf);

        btnEnvi = new JButton("Entrar");

        painelConterComp.add(tit);
        painelConterComp.add(painelCPF);
        painelConterComp.add(btnEnvi);


    }
}
