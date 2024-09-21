import java.util.Scanner;//Impotando um leitor

public class Loja {
    
    public static void main(String[] args) {




        Scanner leitor = new Scanner(System.in);//Criando o objeto leitor


        //Mostrando pro usuario o que o prgrama esta pedindo
        System.out.println("Digite o nome do produto, a marca, preco de custo e o preco de venda:");
        //Atributo do pedido
        Produto p = new Produto(leitor.nextLine(), leitor.nextLine(), leitor.nextFloat(), leitor.nextFloat());
        //Mostrar na tela os metodos
        System.out.println("O lucro do/a " + p.getNome() + " da marca " + p.getMarca() + " teve um lucro de " + p.calcularLucro());

        leitor.close();//Fechando o objeto leitor
    }

}
