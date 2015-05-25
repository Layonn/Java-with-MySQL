import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Conexao { //abstract = não pode ser instanciado
    private static String usuario; //static = aquilo pertence a classe e não ao objeto
    private static String senha;
    private static String banco;
    private static String driver;
    private static Connection conn;
    
    public static Connection conectar(){
        usuario = "root";
        senha = "";
        banco = "prjLayonnLuis";
        driver = "com.mysql.jdbc.Driver";
        conn = null;
        
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection("jdbc:mysql://localhost/"+
                    banco, usuario, senha);
        }catch(ClassNotFoundException cnf){
            System.out.println(cnf.getMessage());
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
        
        return conn;
    }
    
    public static void desconectar(){
        try{
            conn.close();
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
    }
}
