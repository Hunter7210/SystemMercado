package Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Vendas;

public class VendasDAO {

    // atributos
    private Connection connection;
    private List<Vendas> vendas;

    // construtor contendo a conexão
    public VendasDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // Criação da tabela
    public void criarTabela() {

        String sql = "CREATE TABLE IF NOT EXISTS vendas_sysmercad (datavenda VARCHAR(10), cliente VARCHAR (20), quantvendi int, codprod VARCHAR(10) PRIMARY KEY, valorcompra int)";

        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso.");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    // Metodo para listar os valores
    public List<Vendas> listarVendas() {
        PreparedStatement stmt = null;
        // Declaração do objeto PreparedStatement para executar a consulta
        ResultSet rs = null;
        // Declaração do objeto ResultSet para armazenar os resultados da consulta

        vendas = new ArrayList<>();
        // Cria uma lista para armazenar os carros recuperados do banco de dados
        try {
            String query = "SELECT * FROM vendas_sysmercad;";
            // Prepara a consulta SQL para selecionar todos os registros da tabela
            stmt = connection.prepareStatement(query);
            // Resultado
            rs = stmt.executeQuery();
            // Executa a consulta e armazena os resultados no ResultSet
            while (rs.next()) {
                // Para cada registro no ResultSet, cria um objeto Carros com os valores do
                // registro
                Vendas venda = new Vendas(
                        rs.getString("datavenda"),
                        rs.getString("cliente"),
                        rs.getString("quantVendi"),
                        rs.getString("codProd"),
                        rs.getString("valorCompra"));
                vendas.add(venda);// Add o objeto com todos os dados nele
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
            // Fecha os três
        }
        return vendas; // Retorna a lista para o banco de dados
    }

    // Cadastrar venda
    public void cadastrar(String dataVenda, String cliente, String quantVendi, String codProd, String valorCompra) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para cadastrar na tabela

        String sql = "INSERT INTO vendas_sysmercad (datavenda, cliente, quantvendi, codprod, valorcompra)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, dataVenda);
            stmt.setString(2, cliente);
            stmt.setString(3, quantVendi);
            stmt.setString(4, codProd);
            stmt.setString(5, valorCompra);

            // Executa a consulta
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir dados no banco", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
            // Fecha a conexão e o PreparedStatement
        }
    }
}
