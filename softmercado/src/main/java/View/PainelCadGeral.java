package View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;

public class PainelCadGeral extends JPanel {

    private JLabel tit, nome, senha;
    private JTextField inptNome, inptSenha;
    private JButton btnEnvi;
    private JPanel painelPrinc, painelContem, painelSenha, painelNome;

    public PainelCadGeral() {
        super();

        GridLayout grid3x1 = new GridLayout(4, 1);
        GridLayout grid2x1 = new GridLayout(2, 1);

        painelPrinc = new JPanel();
        this.add(painelPrinc);

        tit = new JLabel("Cadastro Usuario");
        painelContem = new JPanel();
        painelContem.setLayout(grid3x1);
        painelPrinc.add(painelContem);

        painelNome = new JPanel();
        painelNome.setLayout(grid2x1);
        nome = new JLabel("Digite seu usuario:");
        painelNome.add(nome);
        inptNome = new JTextField("Usuario");
        painelNome.add(inptNome);

        painelSenha = new JPanel();
        painelSenha.setLayout(grid2x1);
        senha = new JLabel("Digite sua senha:");
        painelSenha.add(senha);
        inptSenha = new JTextField("Senha");
        painelSenha.add(inptSenha);

        btnEnvi = new JButton("Entrar");

        painelContem.add(tit);
        painelContem.add(painelNome);
        painelContem.add(painelSenha);
        painelContem.add(btnEnvi);

        

    }
}
