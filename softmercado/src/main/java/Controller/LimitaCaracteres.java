package Controller;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument; //Esta classe é responsável por suportar a edição de documentos grandes com eficiência.

//Limita os inputs para receberem tipos expecificos de dados
public class LimitaCaracteres extends PlainDocument {

    // enumerador para organizar o tipo de dado que deseja aparecer
    public enum TipoEntrada {
        USUARIO, SENHA, PRODUTO, CODPRODUTO, VALORUNIT, NLOTE, CPF, QTDLOTE, DATAENTREG, DATAVENC;
    }

    // Atributos
    private int qtdCaracteres;
    private TipoEntrada tpEntrada;

    public LimitaCaracteres(int qtdCaracteres, TipoEntrada tpEntrada) {
        this.qtdCaracteres = qtdCaracteres;
        this.tpEntrada = tpEntrada; // Atribui o enumerador ao meu construtor
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

        // Criando teste para caso caracterDigi> qtdCarateres ela ja saía do metodo
        // automaticamete
        if (str == null || getLength() == qtdCaracteres) {
            return;
        }

        // Cria variavel para armazenar todos os caracteres e tambem as palavras
        // digitadas
        int totalCarac = getLength() + str.length();

        // Filtragem dos dados
        // String para armazenar os filtros
        String recebfilt = "";
        switch (tpEntrada) {
            case
                    // Exluir tudo exceto(simbolo = ^) o que estiver no meu delimitador(recebfilt);
                    USUARIO:
                recebfilt = "[^\\p{IsLatin} ]"; // p{IsLatin} significa: qualquer caractere de escrita latina. //FORD
                break;
            case
                    SENHA:
                recebfilt = "[^\\p{IsLatin} 0-9_@]"; // Para aceitar o - é preciso utilizar \\  F-1000
                break;
            case
                    PRODUTO:
                recebfilt = "[^\\p{IsLatin} ]"; // Tomate
                break;
            case
                    CODPRODUTO:
                recebfilt = "[^\\p{IsLatin}\\-0-9\\-]"; // FOR-123
                break;
            case
                    VALORUNIT:
                recebfilt = "[^0-9.]"; // 30.000
                break;
            case
                    NLOTE:
                recebfilt = "[^\\p{IsLatin}0-9]"; // 23002L23H
                break;
            case
                    CPF:
                recebfilt = "[^\\-0-9\\/]"; // 123.123.123/12
                break;
            case
                    QTDLOTE:
                recebfilt = "[^0-9]"; // 21312
                break;
            case
                    DATAENTREG:
                recebfilt = "[^0-9/.]"; // 12/12/1212
                break;
            case
                    DATAVENC:
                 recebfilt = "[^0-9/.]"; // 12/12/1212
                 break;
        }

        // Fazendo a substituição dos dados que não estiverem de acordo com os
        // parametros acima
        str = str.replaceAll(recebfilt, ""); // Substitui os caracteres que estão contidos na minha str excetos os
                                             // recebfilt por vazio("")

        // Limitando a quantidade de caracteres digitado
        if (totalCarac <= qtdCaracteres) {
            // Modifica o corpo
            super.insertString(offs, str, a); // Deve ser chamado apos a string ja estar formatada
        } else {
            String nova = str.substring(0, qtdCaracteres);
            super.insertString(offs, nova, a);
        }
    }
}