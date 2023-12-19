package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Connection.ProdutoDAO;
import Connection.VendasDAO;
import Model.Clientes;
import Model.Produtos;
import Model.Vendas;
import javafx.scene.control.ComboBox;
/* import logs.RegistroSistema; */
import logs.RegistroSistema;

public class ClienInsProdControl {

    private List<Produtos> produtos;
    private List<Vendas> vendas;

    private JTable tabelarRegisVend;
    private DefaultTableModel modeloTableRegis;

    private DefaultTableModel modeloTableProd;
    private JTable tabelaProd;
    // Método para atualizar a tabela de exibição com dados do banco de dados

    // Metodo para atualizar a tabela com os dados
    public void atualizarTabela() {
        modeloTableRegis.setRowCount(0); // Limpa todas as linhas da tabela
        vendas = new VendasDAO().listarVendas();
        // Pega as vendas realizadas
        for (Vendas venda : vendas) {
            // Adiciona os dados a cadas venda no java swing
            modeloTableRegis.addRow(new Object[] { venda.getDataVenda(), venda.getQuantVendi(), venda.getCodProd(),
                    venda.getValorCompra() });
        }
    }

    // Metodo para atualizar a tabela com os dados
    public void atualizarTableProd() {
        /* modeloTableProd.setRowCount(0); */// Limpa todas as linhas da tabela
        produtos = new ProdutoDAO().listartodos();
        // Pega as produtos realizadas
        for (Produtos produto : produtos) {
            // Adiciona os dados a cadas venda no java swing
            modeloTableProd.addRow(new Object[] {
                    produto.getNome(), produto.getCodigoBarra(), produto.getprecoUnit(),
                    produto.getLote(), produto.getQuantLot(),
                    produto.getDataEntr(), produto.getDataVenc()
            });
        }
    }

    // Método para cadastrar um novo carro no banco de dados
    public void cadastrar(JButton btnAciona, JComboBox<String> combo1, JTextField quantVendi, JPanel paineMostra) {

        btnAciona.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Pergunta se o usuario quer realmente se cadastrar
                int podCadast = JOptionPane.showConfirmDialog(paineMostra,
                        "Tem certeza que deseja comprar o produto?",
                        "Escolha uma opção", JOptionPane.YES_NO_OPTION);

                if (podCadast == JOptionPane.YES_OPTION) {
                    Object prodSelecObj = combo1.getSelectedItem();

                    int prodSelecInt = combo1.getSelectedIndex();
                    System.out.println(prodSelecInt);
                    System.out.println(prodSelecObj);

                    if (prodSelecInt != 0) {

                        // Pegar data e hora atual do computador
                        Date dataEHora = new Date();
                        // Formatando
                        String data = new SimpleDateFormat("dd/mm").format(dataEHora);
                        String hora = new SimpleDateFormat("HH:mm:ss aaaa").format(dataEHora);
                        String horario = data + " " + hora;

                        System.out.println(horario);
                        // Transformando o item para String
                        String prodSelecStr = prodSelecObj.toString();

                        System.out.println(prodSelecStr + "/////");
                        produtos = new ProdutoDAO().listar_apenas_um(prodSelecInt);

                        System.out.println(produtos);
                        for (Produtos produto : produtos) {
                            System.out.println(produto.getprecoUnit());

                            try {

                                Double precoTot = Double.parseDouble(produto.getprecoUnit().replace(".", "").trim())
                                        * Integer.parseInt(quantVendi.getText());
                                Double precoTotCorrig = precoTot / 100;

                                new VendasDAO().cadastrar(horario, quantVendi.getText().toString(), prodSelecStr,
                                        precoTotCorrig.toString());

                                JOptionPane.showMessageDialog(paineMostra, "Compra realizada com sucesso!");

                                RegistroSistema.registroOperacao("Produto de nome: " + prodSelecStr
                                        + " Foi vendido com sucesso, na data: " + horario + " Por: " + precoTotCorrig);

                                // Chama o método de cadastro no banco de dados
                                combo1.setSelectedIndex(0);
                                atualizarTabela();
                                atualizarTableProd();
                            } catch (NullPointerException err) {
                                System.out.println(err);
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(paineMostra,
                                "Por favor, escolha um Produto!");
                    }
                }
            }
        });
    }

    public void limparCombo(JComboBox<String> combo1) {
        combo1.setSelectedItem(0);

    }

    public void mostrarValorTot(JLabel valorFina, JTextField quantVendi, JComboBox<String> combo1, JPanel painelthis) {

        painelthis.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                Object prodSelecObj = combo1.getSelectedItem();

                int prodSelecInt = combo1.getSelectedIndex();
                System.out.println(prodSelecInt);
                System.out.println(prodSelecObj);

                if (prodSelecInt != 0) {
                    produtos = new ProdutoDAO().listar_apenas_um(prodSelecInt);
                    for (Produtos produto : produtos) {

                        Double precoTot = Double.parseDouble(produto.getprecoUnit().replace(".", "").trim())
                                * Integer.parseInt(quantVendi.getText());
                        Double precoTotCorrig = precoTot / 100;
                        valorFina.setText(precoTotCorrig.toString());

                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent arg0) {

            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

        });

    }
}