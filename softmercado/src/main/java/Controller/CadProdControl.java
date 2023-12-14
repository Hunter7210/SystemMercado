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
import View.PainelCadProd;

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
            JTextField inptTexto4, JTextField inptTexto5, JTextField inptTexto6, JTextField inptTexto7) {

        btnAciona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!inptTexto1.getText().isEmpty() && !inptTexto2.getText().isEmpty()
                        && !inptTexto3.getText().isEmpty() && !inptTexto4.getText().isEmpty()
                        && !inptTexto5.getText().isEmpty() && !inptTexto6.getText().isEmpty()
                        && !inptTexto7.getText().isEmpty()) {

                    // Fazer a classe para bloquear de escrever um valor errado
                    new ProdutoDAO().cadastrar(inptTexto1.getText(), inptTexto2.getText(), inptTexto3.getText(),
                            inptTexto4.getText(), inptTexto5.getText(), inptTexto6.getText(), inptTexto7.getText());

                    JOptionPane.showMessageDialog(null, "Cadastro de produto realizado com sucesso");

                    PainelCadProd paineCad = new PainelCadProd();
                    paineCad.atualizarTableProd();
                    inptTexto1.setText("");
                    inptTexto2.setText("");
                    inptTexto3.setText("");
                    inptTexto4.setText("");
                    inptTexto5.setText("");
                    inptTexto6.setText("");
                    inptTexto7.setText("");

                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                }
            }
        });

    }

    public void atualizar(String id, String nome, String codigoBarra, String precoUnit, String lote, String quantLot,
            String dataEntr,
            String dataVenc) {
        new ProdutoDAO().atualizar(nome, codigoBarra, precoUnit, lote, quantLot, dataEntr, dataVenc, id);

        PainelCadProd paineCad = new PainelCadProd();
        paineCad.atualizarTableProd();

    }

    public void apagar(String id) {
        new ProdutoDAO().apagar(id);

        PainelCadProd paineCad = new PainelCadProd();
        paineCad.atualizarTableProd();
    }
    /*
     * public void verificar(String cpf) {
     * new ProdutoDAO().verificar(cpf);
     * 
     * atualizarTableProd();
     * }
     */

}
