import java.util.ArrayList;

public class Cliente extends Pessoa{
    private String dataCadastro;
    private double renda, limite;
    
    public ArrayList<String> getCliente(){
        ArrayList<String> retorno = getPessoa();
        
        retorno.add(dataCadastro);
        retorno.add(String.valueOf(renda));
        retorno.add(String.valueOf(limite));
        
        return retorno;
    }
    
    @Override
    public String exibir(){
        return super.exibir()+"\nData de cadastro: "+dataCadastro+
                "\nRenda: "+renda+"\nLimite: "+limite;
    }
    
    public Cliente(int id, String sexo, String nome, String dataCadastro,
            double renda, double limite) {
        
        super(id, sexo, nome);
        this.dataCadastro = dataCadastro;
        this.renda = renda;
        this.limite = limite;
    }
    
    public Cliente(){
        this(0,"","","", 0, 0);
    }
    
    public double calcularLimite(double renda){
        double limite = renda*0.75;      
        return limite;
    }

    //getters n setters
    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public double getRenda() {
        return renda;
    }

    public void setRenda(double renda) {
        this.renda = renda;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    
}
