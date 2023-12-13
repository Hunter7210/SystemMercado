package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * PainelCaixInic
 */
public class PainelCaixInicClub extends JPanel {

    //Atributos
    private JLabel tit;
    private JButton confirm, cancel;
    private JPanel painelPrici, painelBtns;

    public PainelCaixInicClub() {
        super();

        BorderLayout borderLa = new BorderLayout(); 
    /*     GridLayout grid = new GridLayout(1,2);  */

        painelPrici = new JPanel();
        this.add(painelPrici);

        tit = new JLabel("JÁ POSSUI O CLUBE");
        painelPrici.add(tit);

        painelBtns = new JPanel();
        painelBtns.setLayout(borderLa);
        painelPrici.add(painelBtns);

        confirm = new JButton("Sim");
        cancel = new JButton("Não");

        painelBtns.add(confirm, BorderLayout.WEST);
        painelBtns.add(cancel, BorderLayout.EAST);


    }
}