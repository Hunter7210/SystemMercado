package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Connection.VendasDAO;
import Model.Vendas;
import logs.RegistroSistema;

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

    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        vendas = new VendasDAO().listarVendas();
        // Obtém os carros atualizados do banco de dados
        for (Vendas venda : vendas) {
            // Adiciona os dados de cada carro como uma nova linha na tabela Swing
            tableModel.addRow(new Object[] { venda.getDataVenda(), venda.getQuantVendi(), venda.getDataVenda(),
                    venda.getValorCompra() });
        }
    }

    public void limparCombo(JButton bt1, JComboBox<String> combo1, JComboBox<String> combo2, JPanel thispPanel) {
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Criando confirmação para limpar componentes

                // Pergunta se o usuario quer realmente se cadastrar
                int podCadast = JOptionPane.showConfirmDialog(thispPanel,
                        "Tem certeza que deseja limpar os selecionados?",
                        "Escolha uma opção", JOptionPane.YES_NO_OPTION);

                if (podCadast == JOptionPane.YES_OPTION) {
                    RegistroSistema.registroOperacao("Usuario limpou as combobox");
                    combo1.setSelectedIndex(0);
                    combo2.setSelectedIndex(0);

                }
                // Fecha automaticamente o JPanel
            }
        });
    }

    // Método para cadastrar uma nova venda no banco de dados
    public void cadastrar(JButton btnAc, String dataVenda, String cliente, String quantVendi, String codProd,
            String valorCompra,
            JPanel thispPanel) {

        btnAc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int podCadast = JOptionPane.showConfirmDialog(thispPanel, "Deseja realmente realizar esta venda?",
                        "Escolha uma opção: ", JOptionPane.YES_NO_OPTION);

                if (podCadast == JOptionPane.YES_OPTION) {

                    new VendasDAO().cadastrar(dataVenda, quantVendi, codProd, valorCompra);

                    new RegistroSistema().registroOperacao("A compra realizada na data:" + dataVenda + "do produto com codigo igual a : "+ quantVendi + "tem o valor igual:" + valorCompra);
                    // Chama o método de cadastro no banco de dados
                    atualizarTabela(); // Atualiza a tabela de exibição após o cadastro
                    
                }

            }
        });

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
