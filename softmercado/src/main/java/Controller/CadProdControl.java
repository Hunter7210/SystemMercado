package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Connection.ProdutoDAO;
import Model.Produtos;
import View.PainelCadProd;
import logs.RegistroSistema;

public class CadProdControl {

    private List<Produtos> produtos;
    private DefaultTableModel modeloTableProd;
    private JTable tabelaProd;

    public CadProdControl(List<Produtos> produtos, DefaultTableModel modeloTableProd, JTable tabelaProd) {
        this.produtos = produtos;
        this.modeloTableProd = modeloTableProd;
        this.tabelaProd = tabelaProd;
    }

    public void cadastrar(JButton btnAciona, JTextField inptTexto1, JTextField inptTexto2, JTextField inptTexto3,
            JTextField inptTexto4, JTextField inptTexto5, JTextField inptTexto6, JTextField inptTexto7,
            JPanel paineMostra) {

        btnAciona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!inptTexto1.getText().isEmpty() && !inptTexto2.getText().isEmpty()
                        && !inptTexto3.getText().isEmpty() && !inptTexto4.getText().isEmpty()
                        && !inptTexto5.getText().isEmpty() && !inptTexto6.getText().isEmpty()
                        && !inptTexto7.getText().isEmpty()) {

                    // Pergunta se o usuario quer realmente se cadastrar
                    int podCadast = JOptionPane.showConfirmDialog(paineMostra,
                            "Tem certeza que deseja apagar o produto?",
                            "Escolha uma opção", JOptionPane.YES_NO_OPTION);
                    if (podCadast == JOptionPane.YES_OPTION) {

                        new ProdutoDAO().cadastrar(inptTexto1.getText(), inptTexto2.getText(), inptTexto3.getText(),
                                inptTexto4.getText(), inptTexto5.getText(), inptTexto6.getText(), inptTexto7.getText());

                        atualizarTableProd();
                        inptTexto1.setText("");
                        inptTexto2.setText("");
                        inptTexto3.setText("");
                        inptTexto4.setText("");
                        inptTexto5.setText("");
                        inptTexto6.setText("");
                        inptTexto7.setText("");

                        RegistroSistema.registroOperacao("Produto de nome: " + inptTexto1.getText() + "Do lote: "
                                + inptTexto4.getText() + "Foi cadastrdo com sucesso");

                        JOptionPane.showMessageDialog(paineMostra, "Cadastro de produto realizado com sucesso");

                    } else {
                        JOptionPane.showMessageDialog(paineMostra, "Por favor, preencha todos os campos.");
                    }
                }
            }
        });

    }

    public void atualizar(JButton btnAciona, JTextField inptTexto1, JTextField inptTexto2, JTextField inptTexto3,
            JTextField inptTexto4, JTextField inptTexto5, JTextField inptTexto6, JTextField inptTexto7,
            JPanel paineMostra) {

        btnAciona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!inptTexto1.getText().isEmpty() && !inptTexto2.getText().isEmpty()
                        && !inptTexto3.getText().isEmpty() && !inptTexto4.getText().isEmpty()
                        && !inptTexto5.getText().isEmpty() && !inptTexto6.getText().isEmpty()
                        && !inptTexto7.getText().isEmpty()) {

                    new ProdutoDAO().atualizar(inptTexto1.getText(), inptTexto2.getText(), inptTexto3.getText(),
                            inptTexto4.getText(), inptTexto5.getText(), inptTexto6.getText(), inptTexto7.getText());

                    JOptionPane.showMessageDialog(paineMostra, "Produto atualizado com sucesso.");

                    RegistroSistema.registroOperacao("Produto de nome: " + inptTexto1.getText() + "Do lote: "
                            + inptTexto4.getText() + "Foi atualizado com sucesso");

                    atualizarTableProd();
                } else {
                    JOptionPane.showMessageDialog(paineMostra, "Por favor preencha todos os campos");
                }
                /*
                 * PainelCadProd paineCad = new PainelCadProd();
                 * paineCad.atualizarTableProd();
                 */
            }
        });

    }

    public void apagar(String id, JButton btnAciona, JTextField inptTexto1, JTextField inptTexto4, JPanel paineMostra) {

        btnAciona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Pergunta se o usuario quer realmente se cadastrar
                int podCadast = JOptionPane.showConfirmDialog(paineMostra, "Tem certeza que deseja apagar o produto?",
                        "Escolha uma opção", JOptionPane.YES_NO_OPTION);

                if (podCadast == JOptionPane.YES_OPTION) {
                    new ProdutoDAO().apagar(id);

                    JOptionPane.showMessageDialog(paineMostra, "Produto apagado com sucesso!");
                    RegistroSistema.registroOperacao("Produto de nome: " + inptTexto1.getText() + "Do lote: "
                            + inptTexto4.getText() + "Foi apagado com sucesso");
                    atualizarTableProd();
                    /*
                     * PainelCadProd paineCad = new PainelCadProd();
                     * paineCad.atualizarTableProd();
                     */
                }
                // Fecha o JOptionPane automaticamente
            }
        });
    }

    public void atualizarTableProd() {

        produtos = new ProdutoDAO().listartodos();

        for (Produtos produto : produtos) {

            modeloTableProd.addRow(new Object[] {
                    produto.getNome(), produto.getCodigoBarra(), produto.getprecoUnit(),
                    produto.getLote(), produto.getQuantLot(),
                    produto.getDataEntr(), produto.getDataVenc()
            });
        }

    }

}
