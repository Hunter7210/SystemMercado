package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Connection.VendasDAO;
import Model.Vendas;

public class VendasControl {
    private JComboBox<String> combo1, combo2;

    private List<Vendas> vendas;
    private DefaultTableModel tableModel;
    private JTable table;

    // Construtor
    public VendasControl(List<Vendas> vendas, DefaultTableModel tableModel, JTable table) {
        this.vendas = vendas;
        this.tableModel = tableModel;
        this.table = table;
    }

    public void limparCombo(JButton bt1, JComboBox<String> combo1, JComboBox<String> combo2) {
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                combo1.setSelectedIndex(0);
                combo2.setSelectedIndex(0);

            }
        });
    }

    // Método para atualizar a tabela de exibição com dados do banco de dados
    public void atualizarTabela(DefaultTableModel modeloTabela, List<Vendas> vendas) {
        modeloTabela.setRowCount(0); // Limpa todas as linhas existentes na tabela
        vendas = new VendasDAO().listarVendas();
        // Obtém as vendas atualizados do banco de dados
        for (Vendas venda : vendas) {
            // Adiciona os dados de cada venda como uma nova linha na tabela Swing
            modeloTabela.addRow(new Object[] { venda.getCliente(), venda.getCodProd(), venda.getDataVenda(),
                    venda.getValorCompra(), venda.getQuantVendi() });
        }
    }

    // Método para cadastrar uma nova venda no banco de dados
    public void cadastrar(String dataVenda, String carroVendi, String cliente) {
        new VendasDAO().cadastrar(dataVenda, cliente, dataVenda, carroVendi, cliente);
        // Chama o método de cadastro no banco de dados
        atualizarTabela(tableModel, vendas); // Atualiza a tabela de exibição após o cadastro
    }


    public void verificarBtn(JButton btn1, JComboBox<String> combo1, JComboBox<String> combo2) {

        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int carroSelecInt = combo1.getSelectedIndex();
                int clienteSelecInt = combo2.getSelectedIndex();

                if (carroSelecInt != 0 || clienteSelecInt != 0) {
                    vendas = new VendasDAO().listarVendas();

                    combo1.setSelectedIndex(0);
                    combo2.setSelectedIndex(0);

                } else {
                    JOptionPane.showMessageDialog(null,
                            "Por favor, escolha um carro e um cliente!");
                }
            }
        });
    }
}
