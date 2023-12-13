package Controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlCards {
    private CardLayout nomeDoLayout;
    private JPanel nomePainelComCard;
    private String nomePagMost;
    private JLabel nomeBtn;

    // Metodo criado para mudar as paginas
    public void trocarPagina(CardLayout nomeDoLayout, JLabel nomeBtn, JPanel nomePainelComCard, String nomePagMost) {
        nomeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nomeDoLayout.show(nomePainelComCard, nomePagMost);
                nomeBtn.setForeground(Color.GREEN);
            }
        });
    }
}
