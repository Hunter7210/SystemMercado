package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Connection.VendasDAO;
import Model.Clientes;
import Model.Produtos;
import Model.Vendas;
import logs.RegistroSistema;

public class ClienInsProdControl {

    private List<Vendas> vendas;

    private JTable tabelarRegisVend;
    private DefaultTableModel modeloTableRegis;

    // Método para atualizar a tabela de exibição com dados do banco de dados

    public void atualizarTabela() {
       
        vendas = new VendasDAO().listarVendas();
        // Obtém os carros atualizados do banco de dados
        for (Vendas venda : vendas) {
            // Adiciona os dados de cada carro como uma nova linha na tabela Swing
            modeloTableRegis.addRow(new Object[] {
                    venda.getDataVenda(), venda.getQuantVendi(), venda.getCodProd(),
                    venda.getValorCompra()
            });
        }
         /* modeloTableRegis.setRowCount(0); // Limpa todas as linhas existentes na tabela */
    }

    // Método para cadastrar um novo carro no banco de dados
    public void cadastrar(JButton btnAciona, JComboBox<String> combo1, String quantVendi, String valorCompra) {

        btnAciona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object prodSelecObj = combo1.getSelectedItem();

                int prodSelecInt = combo1.getSelectedIndex();

                if (prodSelecInt != 0) {

                    // Pegar data e hora atual do computador
                    Date dataEHora = new Date();
                    // Formatando
                    String data = new SimpleDateFormat("dd/mm").format(dataEHora);
                    String hora = new SimpleDateFormat("HH:mm:ss aaaa").format(dataEHora);
                    String horario = data + " " + hora;

                    // Transformando o item para String
                    String prodSelecStr = prodSelecObj.toString();

                    new VendasDAO().cadastrar(horario, quantVendi, prodSelecStr, valorCompra);
                    new RegistroSistema().registroOperacao("Venda cadastrada: Data:" + horario
                            + "Quantidade: " + quantVendi + "Codigo Produto: " + prodSelecStr + " Valor: "
                            + valorCompra);

                    // Chama o método de cadastro no banco de dados
                    atualizarTabela(); // Atualiza a tabela de exibição após o cadastro
                    combo1.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Por favor, escolha um Produto!");
                }
            }
        });

    }
}
