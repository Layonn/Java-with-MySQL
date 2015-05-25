import java.util.*;
import java.sql.*;

public abstract class VendedorBD {
    public static boolean cadastrar(Vendedor v){
        boolean status = true;
        
        try{
            //chama o método cadastrar da classe PessoaBD, que irá cadastrar
            //apenas os campos que são comuns do Vendedor com a Pessoa (herdados),
            //neste caso o nome e sexo
            status = PessoaBD.cadastrar(v);
            
            System.out.println("Cad. Vendedor: "+status);
            
            //Se o cadastro da PessoBD retornar true
            if(status){
                //recupera o id da pessoa cadastrada que usará o nome e sexo da pessoa
                //cadastrada por último
                v.setId(PessoaBD.recuperarId(v));
                
                String sqlVendedor = "insert into vendedor(id, numeroCarteira, "
                        + "serieCarteira, dataContrato, salarioBase, comissao) "
                        + "values(?,?,?,?,?,?)";
                
                PreparedStatement ps = Conexao.conectar().prepareStatement(sqlVendedor);
                
                //chamando o getter herdado da classe Pessoa
                ps.setInt(1, v.getId());
                ps.setString(2, v.getNumeroCarteira());
                ps.setString(3, v.getSerieCarteira());
                ps.setString(4, v.getDataContrato());
                ps.setDouble(5, v.getSalarioBase());
                ps.setDouble(6, v.getComissao());
                
                ps.execute();
                
                status = ps.getUpdateCount() > 0;
                
                System.out.println("Cadastro de vendedor: "+status);
                
                //Caso o cadastro dê errado, exclui a pessoa do banco
                if(!status){
                    PessoaBD.deletarPessoa(v.getId());
                }
                ps.close();
            }
            Conexao.desconectar();
        }catch(SQLException se){
            System.out.println(se.getMessage()+"Erro ao cadastrar o vendedor no banco");
        }
        return status;
    }
    
    public static ArrayList<Vendedor> buscar(int filtro, String valor){
        String sql = "select p.id, p.nome, p.sexo, v.numeroCarteira, "
                + "v.serieCarteira, v.dataContrato, v.salarioBase, v.comissao,"
                + " from pessoa p inner join vendedor v on p.id = v.id";
        
        switch(filtro){
            //filtro por nome (like)
            case 1:
                sql += "where p.nome like '%"+valor+"%'";
            break;
            
            case 2:
                sql += "where p.sexo like'%"+valor+"%'";
            break;
        }
        sql += " order by p.nome";
        
        return buscar(sql);
    }
    
    private static ArrayList<Vendedor> buscar(String sql){
        ArrayList<Vendedor> vendedores = null;
        
        try{
            PreparedStatement ps = Conexao.conectar().prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            rs.last();
            
            //se houver registro
            if(rs.getRow() > 0){
                vendedores = new ArrayList<Vendedor>();
                
                rs.first();
                
                do{
                    Vendedor v = new Vendedor(rs.getInt("id"), rs.getString("sexo"), 
                        rs.getString("nome"), rs.getString("numeroCarteira"),
                        rs.getString("serieCarteira"), rs.getString("dataContrato"),
                        rs.getDouble("salarioBase"), rs.getDouble("comissao"));
                        
                        //adicionando os novos vendedores ao ArrayList
                        vendedores.add(v);
                
                //enquanto houver um próximo
                }while(rs.next());
            }
            
            rs.close();
            ps.close();
            Conexao.desconectar();
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
        return vendedores;
    }
    
    public static boolean isVazia(int filtro, String valor){
        String sql = "select * from pessoa p inner join vendedor v "+
                "on p.id = v.id ";
        switch (filtro){
            //filtro por nome (like)
            case 1:
                sql += "where nome like '%"+valor+"%'";
            break;
            //filtro por idade (>)
            case 2:
                sql += "where sexo like'%"+valor+"%'";
            break;
        }
        sql += "limit 1";
        return buscar(sql) == null;
    }
    
    public static ArrayList<String> getCampos(){
        ArrayList<String> campos = null;
        
        try{
            String sql = "select numeroCarteira from vendedor limit 1";
            
            PreparedStatement ps = Conexao.conectar().prepareStatement(sql);
            
            campos = new ArrayList<String>();
            
            campos = PessoaBD.getCampos();
            
            ResultSetMetaData rsmt = ps.getMetaData();
            
            System.out.println("Colunas: "+rsmt.getColumnCount());
            
            for(int aux = 1; aux <= rsmt.getColumnCount(); aux++){
                campos.add(rsmt.getColumnName(aux).toUpperCase());
                System.out.println(rsmt.getColumnName(aux).toUpperCase());
            }
            ps.close();
            Conexao.desconectar();
        }catch(SQLException se){
            System.err.println(se.getMessage());
        }
        return campos;
    }
}
