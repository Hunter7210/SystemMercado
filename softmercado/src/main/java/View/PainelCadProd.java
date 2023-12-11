package View;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PainelCadProd extends JPanel {

    private JLabel tit, nomeProd, codProd, precUitProd, loteProd, datEntreg, datVenc;
    private JTextField nomeProdInpt, codProdInpt, precUitProdInpt, loteProdInpt, datEntregInpt, datVencInpt;
    private JButton btnEnvi;
    private JPanel painelPrinc, painelContEle, painelNome, painelCod, painelPrecUnit, painelLote, painelDataEntr, painelDataVenc;

    public PainelCadProd() {
        super();
        
        GridLayout grid8x1 = new GridLayout(8, 1);
        GridLayout grid2x1 = new GridLayout(2, 1);

        painelPrinc = new JPanel();
        this.add(painelPrinc);
        
        tit = new JLabel("Qual seu cargo");

        painelContEle = new JPanel();
        painelContEle.setLayout(grid8x1);
        painelPrinc.add(painelContEle);

        //Nome
        painelNome = new JPanel();
        painelNome.setLayout(grid2x1);
        nomeProd = new JLabel("Digite seu produto:");
        painelNome.add(nomeProd);
        nomeProdInpt = new JTextField("Produto");
        painelNome.add(nomeProdInpt);

        //Codigo
        painelCod = new JPanel();
        painelCod.setLayout(grid2x1);
        codProd = new JLabel("Digite o codigo do Produto:");
        painelCod.add(codProd);
        codProdInpt = new JTextField("Codigo do Produto");
        painelCod.add(codProdInpt);

        //Preç Unit
        painelPrecUnit = new JPanel();
        painelPrecUnit.setLayout(grid2x1);
        precUitProd = new JLabel("Digite o valor unitario:");
        painelPrecUnit.add(precUitProd);
        precUitProdInpt = new JTextField("Valor Unitario");
        painelPrecUnit.add(precUitProdInpt);

        //Lote
        painelLote = new JPanel();
        painelLote.setLayout(grid2x1);
        loteProd = new JLabel("Digite o n° do lote:");
        painelLote.add(loteProd);
        loteProdInpt = new JTextField("Codigo do Produto");
        painelLote.add(loteProdInpt);

        //Data de entraga
        painelDataEntr = new JPanel();
        painelDataEntr.setLayout(grid2x1);
        datEntreg = new JLabel("Digite sua senha:");
        painelDataEntr.add(datEntreg);
        datEntregInpt = new JTextField("Codigo do Produto");
        painelDataEntr.add(datEntregInpt);

        //Data de vencimento
        painelDataVenc = new JPanel();
        painelDataVenc.setLayout(grid2x1);
        datVenc = new JLabel("Digite sua senha:");
        painelDataVenc.add(datVenc);
        datVencInpt = new JTextField("Codigo do Produto");
        painelDataVenc.add(datVencInpt);

        btnEnvi = new JButton("Entrar");

        painelContEle.add(tit);
        painelContEle.add(painelNome);
        painelContEle.add(painelCod);
        painelContEle.add(painelPrecUnit);
        painelContEle.add(painelLote);
        painelContEle.add(painelDataEntr);
        painelContEle.add(painelDataVenc);

        painelContEle.add(btnEnvi);


    }
}
