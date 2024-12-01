import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PRODUTO_ALIMENTICIO extends PRODUTOS {
    private String dataValidade;
    private String informacoesNutricionais;

    public PRODUTO_ALIMENTICIO(String nomeProduto, float precoCusto, float precoVenda, String dataValidade, String informacoesNutricionais) {
        super(nomeProduto, precoCusto, precoVenda);
        this.dataValidade = dataValidade;
        this.informacoesNutricionais = informacoesNutricionais;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getInformacoesNutricionais() {
        return informacoesNutricionais;
    }

    public void setInformacoesNutricionais(String informacoesNutricionais) {
        this.informacoesNutricionais = informacoesNutricionais;
    }

    @Override
    public void salvarRegistro() {
        String sql = "INSERT INTO produtos_alimenticios (nomeProduto, precoCusto, precoVenda, dataValidade, informacoesNutricionais) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, getNomeProduto());
            stmt.setFloat(2, getPrecoCusto());
            stmt.setFloat(3, getPrecoVenda());
            stmt.setString(4, dataValidade);
            stmt.setString(5, informacoesNutricionais);
            stmt.executeUpdate();
            System.out.println("Registro de produto alimentício salvo com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizarRegistro(int id) {
        String sql = "UPDATE produtos_alimenticios SET nomeProduto = ?, precoCusto = ?, precoVenda = ?, dataValidade = ?, informacoesNutricionais = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, getNomeProduto());
            stmt.setFloat(2, getPrecoCusto());
            stmt.setFloat(3, getPrecoVenda());
            stmt.setString(4, dataValidade);
            stmt.setString(5, informacoesNutricionais);
            stmt.setInt(6, id);
            stmt.executeUpdate();
            System.out.println("Registro de produto alimentício atualizado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletarRegistro(int id) {
        String sql = "DELETE FROM produtos_alimenticios WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Registro de produto alimentício deletado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obter a conexão com o banco de dados (herdado de Produto)
}
