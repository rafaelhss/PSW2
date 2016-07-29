package DesingnPatterns;


import java.sql.Connection;
import java.sql.DriverManager;

public class ConSGBD {
    
    private static ConSGBD instancia = null;
    
    private ConSGBD(){
        
    }
    
    public static ConSGBD getConSGBD(){
        if(instancia == null){
            instancia = new ConSGBD();
        }
        return instancia;
    }  

    private static Connection connection;
    
    private static final String dsn = "jdbc:mysql://localhost:3306/dbastral";
    private static final String username = "root";
    private static final String password = "291220";  
    
    public static Connection getConnection() {
	if (connection == null) {
            try {
                //Carrega o driver do MySQL para memória + banco + Transação
                Class.forName("com.mysql.jdbc.Driver");
                connection =  DriverManager.getConnection(dsn,username,password);  
            } catch(Exception e) {
                System.out.println("Houve um erro ao conectar com o Banco de Dados.");
            }
        }
	return connection;
    }
}