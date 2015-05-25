import java.util.ArrayList;

public class Pessoa {
    private int id;
    private String nome, sexo;
    
    public String exibir(){
        return "ID: "+id+"\nNome: "+nome+"\nSexo: "+sexo;
    }
    
    public ArrayList<String> getPessoa(){
        ArrayList<String> retorno = new ArrayList<String>();
        
        retorno.add(String.valueOf(id));
        retorno.add(nome);
        retorno.add(sexo);
        
        return retorno;
    }
    
    public Pessoa(int id, String sexo, String nome){
        this.id = id;
        this.sexo = sexo;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}


