package View;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Connection.ProdutoDAO;
import Controller.CadProdControl;
import Model.Produtos;

public class PainelCadProd extends JPanel {

        private JLabel tit, nomeProd, codProd, loteProd, qtdloteProd, datEntreg, datVenc;
        private JTextField nomeProdInpt, codProdInpt, loteProdInpt, qtdLoteProdInpt, datEntregInpt,
                        datVencInpt;
        private JButton btnEnvi;
        private JPanel painelPrinc, painelContEle, painelNome, painelCod, painelPrecUnit, painelLote, painelQtdLote,
                        painelDataEntr,
                        painelDataVenc;

        private List<Produtos> produtos;
        private DefaultTableModel modeloTableProd;
        private JTable tabelaProd;

        public PainelCadProd() {
                super();

                GridLayout grid8x1 = new GridLayout(8, 1);
                GridLayout grid2x1 = new GridLayout(2, 1);

                painelPrinc = new JPanel();
                this.add(painelPrinc);

                tit = new JLabel("Cadastre seu produto");

                painelContEle = new JPanel();
                painelContEle.setLayout(grid8x1);
                painelPrinc.add(painelContEle);

                // Nome
                painelNome = new JPanel();
                painelNome.setLayout(grid2x1);
                nomeProd = new JLabel("Produto:");
                painelNome.add(nomeProd);
                nomeProdInpt = new JTextField("Produto");
                painelNome.add(nomeProdInpt);

                // Codigo
                painelCod = new JPanel();
                painelCod.setLayout(grid2x1);
                codProd = new JLabel("Codigo do Produto:");
                painelCod.add(codProd);
                codProdInpt = new JTextField("Codigo do Produto");
                painelCod.add(codProdInpt);

                /*
                 * // Preç Unit
                 * painelPrecUnit = new JPanel();
                 * painelPrecUnit.setLayout(grid2x1);
                 * precUitProd = new JLabel("Valor unitario:");
                 * painelPrecUnit.add(precUitProd);
                 * precUitProdInpt = new JTextField("Valor Unitario");
                 * painelPrecUnit.add(precUitProdInpt);
                 */

                // Lote
                painelLote = new JPanel();
                painelLote.setLayout(grid2x1);
                loteProd = new JLabel("N° lote:");
                painelLote.add(loteProd);
                loteProdInpt = new JTextField("Codigo do Produto");
                painelLote.add(loteProdInpt);

                // Quantidade Lote
                painelQtdLote = new JPanel();
                painelQtdLote.setLayout(grid2x1);
                qtdloteProd = new JLabel("Quantidade lote:");
                painelQtdLote.add(qtdloteProd);
                qtdLoteProdInpt = new JTextField("12");
                painelQtdLote.add(qtdLoteProdInpt);

                // Data de entraga
                painelDataEntr = new JPanel();
                painelDataEntr.setLayout(grid2x1);
                datEntreg = new JLabel("Data de Entrega:");
                painelDataEntr.add(datEntreg);
                datEntregInpt = new JTextField("dd/mm/aaaa");
                painelDataEntr.add(datEntregInpt);

                // Data de vencimento
                painelDataVenc = new JPanel();
                painelDataVenc.setLayout(grid2x1);
                datVenc = new JLabel("Data de Vencimento:");
                painelDataVenc.add(datVenc);
                datVencInpt = new JTextField("dd/mm/aaaa");
                painelDataVenc.add(datVencInpt);

                btnEnvi = new JButton("Entrar");

                painelContEle.add(tit);
                painelContEle.add(painelNome);
                painelContEle.add(painelCod);
                /* painelContEle.add(painelPrecUnit); */
                painelContEle.add(painelLote);
                painelContEle.add(painelQtdLote);
                painelContEle.add(painelDataEntr);
                painelContEle.add(painelDataVenc);

                painelContEle.add(btnEnvi);

                new ProdutoDAO().criarTabela();

                CadProdControl controlCadasProd = new CadProdControl(produtos, modeloTableProd, tabelaProd);

                controlCadasProd.cadastrar(btnEnvi, nomeProdInpt, codProdInpt, loteProdInpt, qtdLoteProdInpt,
                                datEntregInpt,
                                datVencInpt);

        }
}
