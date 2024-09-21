public class Produto {


    //Atributos
    private String nome;
    private String marca;
    private float precoDeCusto;
    private float precoDevenda;



    //getters e setters
    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getMarca(){
        return marca;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    public float getPrecoDeCusto(){
        return precoDeCusto;
    }

    public void setPrecoDeCusto(float precoDeCusto){
        this.precoDeCusto = precoDeCusto;
    }

    public float getPrecoDevenda(){
        return precoDevenda;
    }

    public void setPrecoDeVenda(float precoDevenda){
        this.precoDevenda = precoDevenda;
    }


    //contrutor dos produtos
    public Produto(String nome, String marca, float precoDeCusto, float precoDevenda){

        this.nome = nome;
        this.marca = marca;
        this.precoDeCusto = precoDeCusto;
        this.precoDevenda = precoDevenda;

    }



    // Método para calcular o lucro
    public float calcularLucro() {

        return this.precoDevenda - this.precoDeCusto;

    }

}
