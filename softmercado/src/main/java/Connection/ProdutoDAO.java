package Connection;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Produtos;

public class ProdutoDAO {

    private Connection connection;
    private List<Produtos> produtos;

    public ProdutoDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS produt_sysmercad (" +
                "    id SERIAL PRIMARY KEY," +
                "    codigoprod VARCHAR(10)," +
                "    nome VARCHAR(20)," +
                "    datavenc VARCHAR(10)," +
                "    lote VARCHAR(10)," +
                "    dataentr VARCHAR(10)," +
                "    quantprod INT" +
                ");";

        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso.");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    public List<Produtos> listartodos() {
        PreparedStatement stmt = null;

        // Declaração do objeto PreparedStatement para executar a consulta
        ResultSet rs = null;
        // Declaração do objeto ResultSet para armazenar os resultados da consulta

        produtos = new ArrayList<>();

        // Cria uma lista para armazenar os carros recuperados do banco de dados
        try {
            String sql = "SELECT * FROM produt_sysmercad";
            stmt = connection.prepareStatement(sql);
            // Prepara a consulta SQL para selecionar todos os registros da tabela
            rs = stmt.executeQuery();
            // Executa a consulta e armazena os resultados no ResultSet
            while (rs.next()) {
                // Para cada registro no ResultSet, cria um objeto Carros com os valores do
                // registro
                Produtos produto = new Produtos(
                        rs.getString("codigoprod"),
                        rs.getString("nome"),
                        rs.getString("datavenc"),
                        rs.getString("lote"),
                        rs.getString("dataentr"),
                        rs.getString("quantprod"));
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.out.println(ex); // Em caso de erro durante a consulta, imprime o erro
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
            // Fecha a conexão, o PreparedStatement e o ResultSet
        }
        return produtos; // Retorna a lista de carros recuperados do banco de dados

    }

    public List<Produtos> listar_apenas_um() {
         PreparedStatement stmt = null;


        // Declaração do objeto PreparedStatement para executar a consulta
        ResultSet rs = null;
        // Declaração do objeto ResultSet para armazenar os resultados da consulta

        produtos = new ArrayList<>();
        // Cria uma lista para armazenar os carros recuperados do banco de dados
        try {
            String sql = "SELECT * FROM produt_sysmercad Where id=?";
            stmt = connection.prepareStatement(sql);
            // Prepara a consulta SQL para selecionar todos os registros da tabela
            stmt.setString(1, "id");
            rs = stmt.executeQuery();

            // Executa a consulta e armazena os resultados no ResultSet
            while (rs.next()) {
                // Para cada registro no ResultSet, cria um objeto Carros com os valores do
                // registro
                Produtos produto = new Produtos(
                        rs.getString("codigoprod"),
                        rs.getString("nome"),
                        rs.getString("datavenc"),
                        rs.getString("lote"),
                        rs.getString("dataentr"),
                        rs.getString("quantprod"));
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.out.println(ex); // Em caso de erro durante a consulta, imprime o erro
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
            // Fecha a conexão, o PreparedStatement e o ResultSet
        }
        return produtos; // Retorna a lista de carros recuperados do banco de dados

    }
    

    public void atualizar(String id, String codigoBarra, String nome, String dataVenc, String lote, String dataEntr,
            String quantLot) {

        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para atualizar dados pela placa

        String sql = "UPDATE produt_sysmercad SET codigoprod = ?, nome = ?, datavenc= ?, lote = ?, dataentr= ?, quantprod = ?  WHERE id = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, codigoBarra);
            stmt.setString(2, nome);
            stmt.setString(3, dataVenc);
            stmt.setString(4, lote);
            stmt.setString(5, dataEntr);
            stmt.setString(6, quantLot);
            stmt.setString(7, id);
            stmt.executeUpdate();
            System.out.println("Dados atualizados com sucesso");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public void apagar(String id) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para apagar dados pela placa
        String sql = "DELETE FROM produt_sysmercad WHERE id = ?";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, id);

            stmt.executeUpdate(); // Executa a instrução
            System.out.println("Dado apagado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar dados no banco de dados.", e);
        }
        ConnectionFactory.closeConnection(connection, stmt);
    }
}
