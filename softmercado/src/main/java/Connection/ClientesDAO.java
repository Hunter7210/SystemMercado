package Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Clientes;

public class ClientesDAO {

    private Connection connection;
    private List<Clientes> clientes;

    // Construtor para conter a conexão
    public ClientesDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // Criação da tabela
    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS clientes_sysmercad (nome VARCHAR(30), cpf VARCHAR(14) PRIMARY KEY)";

        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso.");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    // Listar todos os valores cadastrados
    public List<Clientes> listarTodos() {
        PreparedStatement stmt = null;
        // Declaração do objeto PreparedStatement para executar a consulta
        ResultSet rs = null;
        // Declaração do objeto ResultSet para armazenar os resultados da consulta
        clientes = new ArrayList<>();
        // Cria uma lista para armazenar os carros recuperados do banco de dados
        try {
            String sql = "SELECT * FROM clientes_sysmercad";
            stmt = connection.prepareStatement(sql);
            // Prepara a consulta SQL para selecionar todos os registros da tabela
            rs = stmt.executeQuery();
            // Executa a consulta e armazena os resultados no ResultSet
            while (rs.next()) {
                // Para cada registro no ResultSet, cria um objeto Carros com os valores do
                // registro
                Clientes cliente = new Clientes(rs.getString("nome"), rs.getString("cpf"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println(ex); // Em caso de erro durante a consulta, imprime o erro
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
            // Fecha a conexão, o PreparedStatement e o ResultSet
        }
        return clientes; // Retorna a lista de carros recuperados do banco de dados
    }

    // Cadastrar Carro no banco
    public void cadastrar(String nome, String cpf) {

        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para cadastrar na tabela

        String sql = "INSERT INTO clientes_sysmercad VALUES (?,?)";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados no banco de dados.", e);

        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }

    }

    // Atualizar dados no banco
    public void atualizar(String nome, String cpf) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para atualizar dados pela placa

        String sql = "UPDATE clientes_sysmercad SET nome = ? WHERE cpf = ?";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.executeUpdate();
            System.out.println("Dados atualizados com sucesso");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public void apagar(String cpf) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para apagar dados pela placa
        String sql = "DELETE FROM clientes_sysmercad WHERE cpf = ?";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.executeUpdate(); // Executa a instrução
            System.out.println("Dado apagado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar dados no banco de dados.", e);
        }
        ConnectionFactory.closeConnection(connection, stmt);
    }
}