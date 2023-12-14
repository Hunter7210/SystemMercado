package Model;

public class Produtos {
    
    //Atributos
    private String codigoBarra;
    private String nome;
    private String precounit;
    private String dataVenc;
    private String lote;
    private String dataEntr;
    private String quantLot;

    //Construtor
    public Produtos(String codigoBarra, String nome,String precoUnit, String dataVenc, String lote, String dataEntr, String quantLot) {
        this.codigoBarra = codigoBarra;
        this.nome = nome;
        this.precounit = precoUnit;
        this.dataVenc = dataVenc;
        this.lote = lote;
        this.dataEntr = dataEntr;
        this.quantLot = quantLot;
        
    }

    //Criação de Getters and Setters

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getprecoUnit() {
        return precounit;
    }

    public void setprecoUnit(String precounit) {
        this.precounit = precounit;
    }

    public String getDataVenc() {
        return dataVenc;
    }

    public void setDataVenc(String dataVenc) {
        this.dataVenc = dataVenc;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getDataEntr() {
        return dataEntr;
    }

    public void setDataEntr(String dataEntr) {
        this.dataEntr = dataEntr;
    }

    public String getQuantLot() {
        return quantLot;
    }

    public void setQuantLot(String quantLot) {
        this.quantLot = quantLot;
    }

}
