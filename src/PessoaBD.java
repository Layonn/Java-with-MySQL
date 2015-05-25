import java.sql.*;
import java.util.ArrayList;

public abstract class PessoaBD {
    
    public static boolean cadastrar(Pessoa p){
        boolean status = true;
        
        try{
            String sqlPessoa = "insert into pessoa (nome, sexo) values(?,?)";
            
            PreparedStatement ps = Conexao.conectar().prepareStatement(sqlPessoa);
            
            //atribuir os valores que serão inclusos no comando sql
            ps.setString(1, p.getNome());
            ps.setString(2, p.getSexo());
            
            ps.execute();

            status = ps.getUpdateCount() > 0;
            
            System.out.println("Cad. Pessoa: "+status);
            
            ps.close();
            
            Conexao.desconectar();
        }catch(SQLException e){
            System.err.println(e.getMessage()+"\nFalha ao cadastrar registro no banco\n");
        }
        
        return status;
    }
    
    public static boolean deletarPessoa(int id){
        boolean status = true;
        
        try{
            String sqlDel = "delete from pessoa where id = ?";
            
            PreparedStatement ps = Conexao.conectar().prepareStatement(sqlDel);
            
            //especificar o valor que será usado no comando sql
            ps.setInt(1, id);
            
            ps.execute();
            
            status = ps.getUpdateCount() > 0;
        }catch(SQLException e){
            System.err.println(e.getMessage()+"\nFalha ao deletar registro do banco\n");
        }
        
        return status;
    }
    
    public static int recuperarId(Pessoa p){
        int id = 0;
        
        try{
            //recuperando o id da pessoa com o nome e sexo iguais aos passados na consulta sql
            String sqlId = "select max(id) as id from pessoa where nome = ? and sexo = ?";
            
            PreparedStatement ps = Conexao.conectar().prepareStatement(sqlId);
            
            ps.setString(1, p.getNome());
            ps.setString(2, p.getSexo());
            
            ResultSet rs = ps.executeQuery();
            
            //recuperando o último registro
            rs.last();
            
            id = rs.getInt("id");
            
            rs.close();
            ps.close();
            Conexao.desconectar();
        }catch(SQLException e){
            System.err.println(e.getMessage()+"\nErro ao recuperar ID\n");
        }
        
        return id;        
    }
    
    public static ArrayList<Pessoa> buscar(int filtro, String valor){
        String sql = "select * from pessoa ";
        
        switch(filtro){
            //filtro por nome (like)
            case 1:
                sql += " where nome like '%"+valor+"%' ";
            break;
            //filtro por sexo
            case 2:
                sql += " where sexo like '%"+valor+"%'";
            break;
        }   
        
        sql += " order by nome";
        
        return buscar(sql);
    }
    
    private static ArrayList<Pessoa> buscar(String sql){
        ArrayList<Pessoa> pessoas = null;
        
        try{
            PreparedStatement ps = Conexao.conectar().prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            rs.last();
            
            //se houver um registro
            if(rs.getRow() > 0){
                pessoas = new ArrayList<Pessoa>();
                
                rs.first();
                
                do{
                    Pessoa p = new Pessoa(rs.getInt("id"),
                            rs.getString("sexo"), rs.getString("nome"));
                    
                    //adicionando as pessoas ao ArrayList que será retornado
                    pessoas.add(p);
                //enquanto houver um próximo registro
                }while(rs.next());
            }
            
            rs.close();
            ps.close();
            Conexao.desconectar();
                
        }catch(SQLException e){
            System.err.println(e.getMessage()+"\nErro ao buscar\n");
        }
        
        return pessoas;
    }
    
    public static ArrayList<String> getCampos(){
        ArrayList<String> campos = null;
        
        try{
            String sql = "select * from pessoa limit 1";
            
            PreparedStatement ps = Conexao.conectar().prepareStatement(sql);
            
            campos = new ArrayList<String>();
            
            ResultSetMetaData rsmt = ps.getMetaData();
            
            System.out.println("Colunas: "+rsmt.getColumnCount());
            
            for(int aux = 1; aux <= rsmt.getColumnCount(); aux++){
                //adicionando as colunas baseado nos metadados
                campos.add(rsmt.getColumnName(aux).toUpperCase());
                System.out.println(rsmt.getColumnName(aux).toUpperCase());
            }
            
            ps.close();
            Conexao.desconectar();
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        
        return campos;
    }
    
    public static boolean isVazia(int filtro, String valor){
        String sql = "select * from pessoa ";
        
        switch(filtro){
            case 1:
                sql += " where nome like '%"+valor+"%' ";
            break;
                
            case 2:
                sql += " where sexo like "+valor+"%'";
            break;
        }
        
        sql += " limit 1";
        return buscar(sql) == null;
    }
}
