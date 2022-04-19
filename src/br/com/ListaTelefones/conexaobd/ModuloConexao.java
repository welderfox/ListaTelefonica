package br.com.ListaTelefones.conexaobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ModuloConexao {
    public static Connection conector() {

        Connection conexao;
        // chamando o driver de conexão
       String driver = "com.mysql.jdbc.Driver";
        
        // informações do banco
        String url = "jdbc:mysql://127.0.0.1:3306/dbtelefones";
        String user = "root";
        String password = "123456";
        
        //conectando com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
           
        } catch (ClassNotFoundException | SQLException erro) {
            System.out.println(erro);
            return null;
        }
    }
}
