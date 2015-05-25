import java.util.ArrayList;

public class Vendedor extends Pessoa{
    private String numeroCarteira, serieCarteira, dataContrato;
    private double salarioBase, comissao;
    
    //retornando um ArrayList com o vendedor concatenado com Pessoa
    public ArrayList<String> getVendedor(){
        ArrayList<String> retorno = getPessoa();
        
        retorno.add(numeroCarteira);
        retorno.add(serieCarteira);
        retorno.add(dataContrato);
        retorno.add(String.valueOf(salarioBase));
        retorno.add(String.valueOf(comissao));
        
        return retorno;
    }
    
    @Override
    public String exibir(){
        return super.exibir()+"\nNúmero da carteira: "+numeroCarteira+
                "\nNúmero de série da carteira: "+serieCarteira+
                "\nData de contrato: "+dataContrato+
                "\nSalário base: "+salarioBase+
                "\nComissão: "+comissao;
    }
    
    public Vendedor(int id, String sexo, String nome, String numeroCarteira, 
            String serieCarteira, String dataContrato, double salarioBase, double comissao){
        
        super(id, sexo, nome);
        
        this.numeroCarteira = numeroCarteira;
        this.serieCarteira = serieCarteira;
        this.dataContrato = dataContrato;
        this.salarioBase = salarioBase;
        this.comissao = comissao;
    }
    
    public double calcularComissao(double salario){       
        double comissao;
        
        if(salario > 790 && salario <= 1000){
            comissao = 25;
        }else if(salario > 1000 && salario <= 1750){
            comissao = 27.3;
        }else{
            comissao = 23.1;
        }
        
        
        return comissao;
    }
    
    public double calcularComissao(double salario, double venda){
        double comissaoFinal;
        double comissao = calcularComissao(salario);
        
        comissaoFinal = (comissao * venda)/100;
        comissaoFinal = venda - comissaoFinal;
        
        return comissaoFinal;
        
    }
    
    //getters n setters
    public String getNumeroCarteira() {
        return numeroCarteira;
    }

    public void setNumeroCarteira(String numeroCarteira) {
        this.numeroCarteira = numeroCarteira;
    }

    public String getSerieCarteira() {
        return serieCarteira;
    }

    public void setSerieCarteira(String serieCarteira) {
        this.serieCarteira = serieCarteira;
    }

    public String getDataContrato() {
        return dataContrato;
    }

    public void setDataContrato(String dataContrato) {
        this.dataContrato = dataContrato;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }
    
    
    
}
