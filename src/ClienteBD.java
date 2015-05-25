import java.util.*;
import java.sql.*;

public abstract class ClienteBD {
    public static boolean cadastrar(Cliente c){
        boolean status = true;
        
        try{
            status = PessoaBD.cadastrar(c);
            
            System.out.println("Cad. Pessoa: "+status);
            
            if(status){
                c.setId(PessoaBD.recuperarId(c));
                
                String sqlCliente = "insert into cliente(id, dataCadastro, renda,"
                        + "limite) values(?,?,?,?)";
                
                PreparedStatement ps = Conexao.conectar().prepareStatement(sqlCliente);
                ps.setInt(1, c.getId());
                ps.setString(2, c.getDataCadastro());
                ps.setDouble(3, c.getRenda());
                ps.setDouble(4, c.getLimite());
                
                ps.execute();
                
                status = ps.getUpdateCount() > 0;
                
                System.out.println("Cad. Cliente: "+status);
                
                if(!status){
                    PessoaBD.deletarPessoa(c.getId());
                }
                
                ps.close();
            }
            Conexao.desconectar();
        }catch(SQLException se){
            System.out.println(se.getMessage()+"Falha ao cadastrar cliente no banco");
        }
        return status;
    }
    
    public static ArrayList<Cliente> buscar(int filtro, String valor){
        String sql = "select p.id, p.nome, p.sexo, c.dataCadastro, c.renda,"
                + "c.limite from pessoa p inner join cliente c on p.id = c.id ";
        
        switch(filtro){
            //filtro por nome (like)
            case 1:
                sql += " where p.nome like '%"+valor+"%' ";
            break;    
            //filtro por sexo
            case 2:
                sql += " where p.sexo like '%"+valor+"%' ";
            break;
        }
        sql += " order by p.nome";
        
        return buscar(sql);
    }
    
    private static ArrayList<Cliente> buscar (String sql){
        ArrayList<Cliente> clientes = null;
        
        try{
            PreparedStatement ps = Conexao.conectar().prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            rs.last();
            
            if(rs.getRow() > 0){
                clientes = new ArrayList<Cliente>();
                
                rs.first();
                
                do{
                    Cliente c = new Cliente(rs.getInt("id"), rs.getString("sexo"),
                        rs.getString("nome"), rs.getString("dataCadastro"), 
                        rs.getDouble("renda"), rs.getDouble("limite"));
                    
                        clientes.add(c);
                
                }while(rs.next());
            }
            
            rs.close();
            ps.close();
            Conexao.desconectar();
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
        return clientes;
    }
    
    public static boolean isVazia(int filtro, String valor){
        String sql = "select * from pessoa p inner join cliente c on p.id = c.id ";
        
        switch(filtro){
            //filtro por nome (like)
            case 1:
                sql += " where nome like '%"+valor+"%' ";
            break;    
            //filtro por sexo
            case 2:
                sql += " where sexo like '%"+valor+"%' ";
            break;
        }
        sql += " limit 1";
        
        return buscar(sql) == null;
    }
    
    public static ArrayList<String> getCampos(){
        ArrayList<String> campos = null;
        
        try{
            String sql = "select dataCadastro from cliente limit 1 ";
            
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
