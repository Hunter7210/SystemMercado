package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Connection.ProdutoDAO;
import Connection.VendasDAO;
import Model.Clientes;
import Model.Produtos;
import Model.Vendas;

public class EscolGerenControl {

    private List<Produtos> produtos;

    private JTable tabelaGerencEstoq;
    private DefaultTableModel modeloTableGerenc;

    public void limparCombo(JButton btnAciona, JComboBox<String> combo1, JComboBox<String> combo2,
            JComboBox<String> combo3) {

        btnAciona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object comb1SelecObj = combo1.getSelectedItem();
                Object comb2SelecObj = combo2.getSelectedItem();
                Object comb3SelecObj = combo3.getSelectedItem();

                // Retona ao index inicial
                if (!comb1SelecObj.equals("Selecionar o Produto") || !comb2SelecObj.equals("Selecionar o Lote")
                        || !comb3SelecObj.equals("Selecionar o Periodo de vencimento")) {
                    combo1.setSelectedIndex(0);
                    combo2.setSelectedIndex(0);
                    combo3.setSelectedIndex(0);

                    JOptionPane.showMessageDialog(null, "Campos limpos com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Necessário preencher algum campo!");
                }

            }
        });
    }
/* 
    public void filtrarTabela(JButton btnAciona) {
        
        btnAciona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    } */
}
