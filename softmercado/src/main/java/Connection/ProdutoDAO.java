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
                "    nome VARCHAR(20)," +
                "    codigobarra VARCHAR(10)," +
                "    lote VARCHAR(10)," +
                "    quantLot INT," +
                "    dataentr VARCHAR(10)," +
                "    datavenc VARCHAR(10)" +
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
                        rs.getString("nome"),
                        rs.getString("codigobarra"),
                        rs.getString("lote"),
                        rs.getString("quantLot"),
                        rs.getString("dataEntr"),
                        rs.getString("dataVenc"));
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
                        rs.getString("nome"),
                        rs.getString("codigobarra"),
                        rs.getString("lote"),
                        rs.getString("quantLot"),
                        rs.getString("dataEntr"),
                        rs.getString("dataVenc"));
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

    // Cadastrar Carro no banco
    public void cadastrar(String nome, String codigoBarra, String lote, int quantLot, String dataEntr,
            String dataVenc) {



        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para cadastrar na tabela

        String sql = "INSERT INTO produt_sysmercad (nome, codigobarra, lote, quantLot, dataentr, datavenc) VALUES (?,?,?,?,?,?)";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, codigoBarra);
            stmt.setString(3, lote);
            stmt.setInt(4, quantLot);
            stmt.setString(5, dataEntr);
            stmt.setString(6, dataVenc);
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados no banco de dados.", e);

        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }

    }

    public void atualizar(String id, String nome, String codigoBarra, String lote, String quantLot, String dataEntr,
            String dataVenc) {

        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para atualizar dados pela placa

        String sql = "UPDATE produt_sysmercad SET nome = ?, codigobarra = ?, lote = ?, quantLot = ?, dataentr= ?, datavenc= ?  WHERE id = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, codigoBarra);
            stmt.setString(3, lote);
            stmt.setString(4, quantLot);
            stmt.setString(5, dataEntr);
            stmt.setString(6, dataVenc);
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
