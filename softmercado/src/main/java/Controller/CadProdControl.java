package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Connection.ProdutoDAO;
import Model.Produtos;

public class CadProdControl {

    private List<Produtos> produtos;
    private DefaultTableModel modeloTableProd;
    private JTable tabelaProd;

    public CadProdControl(List<Produtos> produtos, DefaultTableModel modeloTableProd, JTable tabelaProd) {
        this.produtos = produtos;
        this.modeloTableProd = modeloTableProd;
        this.tabelaProd = tabelaProd;
    }

    public void atualizarTableProd() {

        produtos = new ProdutoDAO().listartodos();

        for (Produtos produto : produtos) {

            modeloTableProd.addRow(new Object[] {
                    produto.getNome(), produto.getCodigoBarra(), produto.getLote(), produto.getQuantLot(),
                    produto.getDataEntr(), produto.getDataVenc()
            });
        }

    }

    public void cadastrar(JButton btnAciona, JTextField inptTexto1, JTextField inptTexto2, JTextField inptTexto3,
            JTextField inptTexto4, JTextField inptTexto5, JTextField inptTexto6) {

        btnAciona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!inptTexto1.getText().isEmpty() && !inptTexto2.getText().isEmpty()
                        && !inptTexto3.getText().isEmpty() && !inptTexto4.getText().isEmpty()
                        && !inptTexto5.getText().isEmpty() && !inptTexto6.getText().isEmpty()) {

                    int intInpt4 = Integer.parseInt(inptTexto4.getText());

                    // Fazer a classe para bloquear de escrever um valor errado
                    new ProdutoDAO().cadastrar(inptTexto1.getText(), inptTexto2.getText(), inptTexto3.getText(),
                            intInpt4, inptTexto5.getText(), inptTexto6.getText());

                    JOptionPane.showMessageDialog(null, "Cadastro de produto realizado com sucesso");

                    /* atualizarTableProd(); */
                    inptTexto1.setText("");
                    inptTexto2.setText("");
                    inptTexto3.setText("");
                    inptTexto4.setText("");
                    inptTexto5.setText("");
                    inptTexto6.setText("");

                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                }
            }
        });

    }

    public void atualizar(String id, String nome, String codigoBarra, String lote, String quantLot, String dataEntr,
            String dataVenc) {
        new ProdutoDAO().atualizar(nome, codigoBarra, lote, quantLot, dataEntr, dataVenc, id);

        atualizarTableProd();
    }

    public void apagar(String id) {
        new ProdutoDAO().apagar(id);

        atualizarTableProd();
    }
    /*
     * public void verificar(String cpf) {
     * new ProdutoDAO().verificar(cpf);
     * 
     * atualizarTableProd();
     * }
     */

}
