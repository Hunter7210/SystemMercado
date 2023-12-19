package View;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Connection.ProdutoDAO;
import Controller.CadProdControl;
import Controller.LimitaCaracteres;
import Model.Produtos;

public class PainelCadProd extends JPanel {

        private JLabel tit, nomeProd, codProd, precUitProd, loteProd, qtdloteProd, datEntreg, datVenc;
        private JTextField nomeProdInpt, codProdInpt, precUitProdInpt, loteProdInpt, qtdLoteProdInpt, datEntregInpt,
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

                // Preç Unit
                painelPrecUnit = new JPanel();
                painelPrecUnit.setLayout(grid2x1);
                precUitProd = new JLabel("Valor unitario:");
                painelPrecUnit.add(precUitProd);
                precUitProdInpt = new JTextField("Valor Unitario");
                painelPrecUnit.add(precUitProdInpt);

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
                painelContEle.add(painelPrecUnit);
                painelContEle.add(painelLote);
                painelContEle.add(painelQtdLote);
                painelContEle.add(painelDataEntr);
                painelContEle.add(painelDataVenc);

                painelContEle.add(btnEnvi);

                // Atribuindo o limitador de caracteres a cada um dos meus Inputs com os
                // paramêtros de qtdCaracteres e o TipoEntrada
                nomeProdInpt.setDocument(new LimitaCaracteres(30, LimitaCaracteres.TipoEntrada.PRODUTO));
                codProdInpt.setDocument(new LimitaCaracteres(14, LimitaCaracteres.TipoEntrada.CODPRODUTO));
                precUitProdInpt.setDocument(new LimitaCaracteres(15, LimitaCaracteres.TipoEntrada.VALORUNIT));
                loteProdInpt.setDocument(new LimitaCaracteres(10, LimitaCaracteres.TipoEntrada.NLOTE));
                qtdLoteProdInpt.setDocument(new LimitaCaracteres(6, LimitaCaracteres.TipoEntrada.QTDLOTE));
                datEntregInpt.setDocument(new LimitaCaracteres(10, LimitaCaracteres.TipoEntrada.DATAENTREG));
                datVencInpt.setDocument(new LimitaCaracteres(10, LimitaCaracteres.TipoEntrada.DATAVENC));
                

                // tabela de carros
                JScrollPane jSPane = new JScrollPane();
                add(jSPane);

                modeloTableProd = new DefaultTableModel(new Object[][] {},
                                new String[] { "Lote", "Nome/Codigo Produto", "Quantidade Restante", "Data de entrega",
                                                "Data Vencimento" });
                tabelaProd = new JTable(modeloTableProd);
                jSPane.setViewportView(tabelaProd);

                new ProdutoDAO().criarTabela();

                CadProdControl controlCadasProd = new CadProdControl(produtos, modeloTableProd, tabelaProd);

                controlCadasProd.cadastrar(btnEnvi, nomeProdInpt, codProdInpt, precUitProdInpt, loteProdInpt,
                                qtdLoteProdInpt,
                                datEntregInpt,
                                datVencInpt, this);
                atualizarTableProd();

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
