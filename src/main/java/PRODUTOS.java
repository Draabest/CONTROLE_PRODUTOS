import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PRODUTOS {
    private String nomeProduto;
    private float precoCusto;
    private float precoVenda;

    public PRODUTOS(String nomeProduto, float precoCusto, float precoVenda) {
        this.nomeProduto = nomeProduto;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public float getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(float precoCusto) {
        this.precoCusto = precoCusto;
    }

    public float getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(float precoVenda) {
        this.precoVenda = precoVenda;
    }

    // Método concreto para calcular o lucro
    public float calcularLucro() {
        return precoVenda - precoCusto;
    }

    // Métodos para salvar, atualizar e deletar registros no banco de dados
    public void salvarRegistro() {
        String sql = "INSERT INTO produtos (nomeProduto, precoCusto, precoVenda) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeProduto);
            stmt.setFloat(2, precoCusto);
            stmt.setFloat(3, precoVenda);
            stmt.executeUpdate();
            System.out.println("Registro salvo com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarRegistro(int id) {
        String sql = "UPDATE produtos SET nomeProduto = ?, precoCusto = ?, precoVenda = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeProduto);
            stmt.setFloat(2, precoCusto);
            stmt.setFloat(3, precoVenda);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            System.out.println("Registro atualizado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarRegistro(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Registro deletado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obter a conexão com o banco de dados
    protected Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/seu_banco";
        String user = "root";
        String password = "sua_senha";
        return DriverManager.getConnection(url, user, password);
    }
}
