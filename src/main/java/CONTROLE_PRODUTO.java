import java.sql.*;
import java.util.Scanner;

public class CONTROLE_PRODUTO {

    // Método para obter conexão com o banco de dados
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/db_loja"; // Nome do banco de dados
        String user = "root"; // Usuário do MySQL
        String password = ""; // Senha do MySQL

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados.");
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        while (true) {
            System.out.println("\n----- MENU DE PRODUTOS -----");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Deletar Produto");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    cadastrarProduto(scanner);
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    atualizarProduto(scanner);
                    break;
                case 4:
                    deletarProduto(scanner);
                    break;
                case 5:
                    System.out.println("Encerrando o programa...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void cadastrarProduto(Scanner scanner) {
        System.out.println("\n----- Cadastrar Produto -----");
        System.out.print("Nome do Produto: ");
        String nome = scanner.nextLine();
        System.out.print("Preço de Custo: ");
        float precoCusto = scanner.nextFloat();
        System.out.print("Preço de Venda: ");
        float precoVenda = scanner.nextFloat();

        // Pergunta sobre o tipo do produto
        System.out.println("Selecione o tipo de produto:");
        System.out.println("1. Produto Alimentício");
        System.out.println("2. Produto de Vestuário");
        int tipoProduto = scanner.nextInt();
        scanner.nextLine();  // Consumir a quebra de linha

        // Definir o tipo de produto
        String tipo = (tipoProduto == 1) ? "Alimenticio" : "Vestuario";

        String sql = "INSERT INTO produtos (nomeProduto, precoCusto, precoVenda, tipo) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setFloat(2, precoCusto);
            stmt.setFloat(3, precoVenda);
            stmt.setString(4, tipo);
            stmt.executeUpdate();
            System.out.println("Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produto.");
            e.printStackTrace();
        }
    }

    public static void listarProdutos() {
        System.out.println("\n----- Lista de Produtos -----");
        String sql = "SELECT * FROM produtos";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("nomeProduto"));
                System.out.println("Preço de Custo: R$" + rs.getFloat("precoCusto"));
                System.out.println("Preço de Venda: R$" + rs.getFloat("precoVenda"));
                System.out.println("Tipo: " + rs.getString("tipo"));
                System.out.println("-------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos.");
            e.printStackTrace();
        }
    }

    public static void atualizarProduto(Scanner scanner) {
        System.out.println("\n----- Atualizar Produto -----");
        System.out.print("ID do Produto: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        System.out.print("Novo Nome do Produto: ");
        String nome = scanner.nextLine();
        System.out.print("Novo Preço de Custo: ");
        float precoCusto = scanner.nextFloat();
        System.out.print("Novo Preço de Venda: ");
        float precoVenda = scanner.nextFloat();

        String sql = "UPDATE produtos SET nomeProduto = ?, precoCusto = ?, precoVenda = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setFloat(2, precoCusto);
            stmt.setFloat(3, precoVenda);
            stmt.setInt(4, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto.");
            e.printStackTrace();
        }
    }

    public static void deletarProduto(Scanner scanner) {
        System.out.println("\n----- Deletar Produto -----");
        System.out.print("ID do Produto: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM produtos WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produto deletado com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar produto.");
            e.printStackTrace();
        }
    }
}
