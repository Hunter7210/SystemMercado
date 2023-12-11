package View;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelEscolCaix extends JPanel {

    // Atributos
    private JLabel tit;
    private JButton cadastraClien, vender;
    private JPanel painelPrinci, painelBtns;

    public PainelEscolCaix() {
        super();

        BorderLayout borderLa = new BorderLayout();
        /* GridLayout grid = new GridLayout(1,2); */

        painelPrinci = new JPanel();
        this.add(painelPrinci);

        tit = new JLabel("QUER SE CADASTRAR?");
        painelPrinci.add(tit);

        painelBtns = new JPanel();
        painelBtns.setLayout(borderLa);
        painelPrinci.add(painelBtns);

        cadastraClien = new JButton("Sim");
        vender = new JButton("Não");

        painelBtns.add(cadastraClien, BorderLayout.WEST);
        painelBtns.add(vender, BorderLayout.EAST);

    }
}
