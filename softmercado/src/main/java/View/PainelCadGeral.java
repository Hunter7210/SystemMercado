package View;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.CadGeralControl;

public class PainelCadGeral extends JPanel {

    // Atributos
    private JLabel tit, usuario, senha;
    private JTextField inptUsua, inptSen;
    private JButton enviar;



    public PainelCadGeral() {
        super();
        enviar = new JButton();
        CadGeralControl cadastroGeral = new CadGeralControl();

        //Chamando a ação la da pagina cadGeralControl
        cadastroGeral.Adicionar(enviar);
    }
}
