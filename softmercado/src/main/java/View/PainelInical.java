package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelInical extends JPanel {

    // Atributos
    private JLabel titulo;
    private JButton gerente, caixa;
    private JPanel painelPrincipal;

    public PainelInical() {
        super();
       

        painelPrincipal = new JPanel(new GridBagLayout());
         this.add(painelPrincipal);
        GridBagConstraints gbc = new GridBagConstraints();

        // Adicionando a JLabel no topo e centralizada
        JLabel label = new JLabel("Seja Bem-Vindo(a)\nQual o seu cargo?");
        label.setFont(new Font("Arial", Font.BOLD, 24)); // Ajuste a fonte e o tamanho conforme necessário
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 40, 0); // Aumentando a distância vertical para 40 pixels
        gbc.anchor = GridBagConstraints.PAGE_START;
        painelPrincipal.add(label, gbc);

        // Criando os botões no centro com um layout de Flow
        JPanel buttonPanel = new JPanel();

        JButton caixaButton = new JButton("Caixa");
        JButton gerenteButton = new JButton("Gerente");

        // Aumentando a fonte dos botões
        Font buttonFont = new Font("Arial", Font.PLAIN, 18); // Ajuste a fonte e o tamanho conforme necessário
        caixaButton.setFont(buttonFont);
        gerenteButton.setFont(buttonFont);

        buttonPanel.add(caixaButton);
        buttonPanel.add(Box.createHorizontalStrut(20)); // Adicionando um espaçamento horizontal de 20 pixels
        buttonPanel.add(gerenteButton);

        // Adicionando os botões ao centro
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(40, 0, 0, 0); // Aumentando a distância vertical para 40 pixels
        gbc.anchor = GridBagConstraints.CENTER;
        painelPrincipal.add(buttonPanel, gbc);

    }
}
