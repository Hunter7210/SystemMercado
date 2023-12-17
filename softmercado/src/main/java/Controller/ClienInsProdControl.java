package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
/* import logs.RegistroSistema; */
import logs.RegistroSistema;

public class ClienInsProdControl {

    private List<Vendas> vendas;
    private List<Produtos> produtos;

    private JTable tabelarRegisVend;
    private DefaultTableModel modeloTableRegis;

    // Método para atualizar a tabela de exibição com dados do banco de dados

    // Método para cadastrar um novo carro no banco de dados
    public void cadastrar(JButton btnAciona, JComboBox<String> combo1, JTextField quantVendi, JPanel paineMostra) {

        btnAciona.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Pergunta se o usuario quer realmente se cadastrar
                int podCadast = JOptionPane.showConfirmDialog(paineMostra,
                        "Tem certeza que deseja apagar o produto?",
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

                        System.out.println(prodSelecStr);

                        // Pegando o valor da compra por unid
                        new ProdutoDAO().listar_apenas_um(prodSelecInt);

                        String precoUnit = produtos.get(prodSelecInt).getprecoUnit();

                        String precoTotal = formValorTota(Integer.parseInt(precoUnit), quantVendi.getText());
                        System.out.println(precoUnit);

                        new VendasDAO().cadastrar(horario, quantVendi.getText(), prodSelecStr,
                                precoTotal);

                        JOptionPane.showMessageDialog(paineMostra, "Compra realizada com sucesso!");

                        RegistroSistema.registroOperacao("Produto de nome: " + prodSelecStr + "Foi vendido com sucesso, na data: "+ horario + "Por: "+ precoTotal);

                        // Chama o método de cadastro no banco de dados
                        combo1.setSelectedIndex(0);
                    } else {
                        JOptionPane.showMessageDialog(paineMostra,
                                "Por favor, escolha um Produto!");
                    }
                }
            }
        });
    }

    private String formValorTota(int precoUnitario, String qtdVendi) {

        int result = precoUnitario * Integer.parseInt(qtdVendi);
       String resultString = Integer.toString(result);
       return resultString;

    }
}