

import java.sql.*;
import model.Cliente;

public class ClienteDAO 
{
    private Connection conexao;

    public ClienteDAO()
    {
        conexao = null;
    }

    public boolean conectar()
    {
        String driverName = "org.postgresql.Driver";
        String serverName = "localhost";
        String mydatabase = "BestPUB";
        int porta = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
        String username = "postgres";
        String password = "ti@cc";
        boolean status = false;

        try
        {
            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, username, password);
            status = (conexao == null);
            System.out.println("Conexao efetuada!");
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("Conexao NAO efetuada com o postgress -- Driver nao encontrado -- " + e.getMessage());
        }
        catch (SQLException e)
        {
            System.err.println("Conexao NAO efetuada com o postgress --" + e.getMessage());
        }
        return status;
    }


    
    // Conferir metodo de salvar ddn e idade
    public boolean insertCliente (Cliente cliente)
    {
        boolean status = false;
        try
        {
            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO cliente (cpf,nome,ddn,senha,email) " + 
            				 "VALUES ("+ cliente.getCpf() + ", '" + cliente.getNome() + "','" + cliente.getDdn()+ "','" + cliente.getSenha() + "','" + cliente.getEmail() + "');");
            st.close();
            status = true;
        }
        catch (SQLException u)
        {
            throw new RuntimeException(u);
        }
        return status;
    }


    

    public boolean updateCliente(Cliente cliente)
    {
        boolean status = false;
        try
        {
            Statement st = conexao.createStatement();
            String sql = "UPDATE cliente SET nome = '" + cliente.getNome() + "', ddn = '" + cliente.getDdn() + "', senha = '" + cliente.getSenha() + "','" + cliente.getEmail() + "'" + "WHERE cpf = " + cliente.getCpf();
            st.executeUpdate(sql);
            st.close();
            status = true;
        }
        catch (SQLException u)
        {
            throw new RuntimeException (u);
        }
        return status;
    }


    
    public boolean removeCliente (String cpf)
    {
        boolean status = false;
        try
        {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM cliente WHERE cpf = '" + cpf + "'");
            st.close();
            status = true;
        }
        catch (SQLException u)
        {
            throw new RuntimeException(u);
        }
        return status;
    }



    public Cliente getCliente(String cpf)
    {
        Cliente cliente = null;

        try
        {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery ("SELECT * FROM cliente WHERE cpf = '" + cpf + "'");
            if(rs.next())
            {
                rs.last();
                cliente = new Cliente(rs.getString("cpf"), rs.getString("nome"), rs.getString("ddn"), rs.getInt("idade"), rs.getString("senha"), rs.getString("email"));
                rs.beforeFirst();
            }
        }
        catch (Exception e)
        {
             System.err.println(e.getMessage());   
        }
        return cliente;
    }
    
    
    public Cliente[] listarClientes()
    {
        Cliente[] clientes = null;

        try
        {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery ("SELECT * FROM cliente");
            if(rs.next())
            {
                rs.last();
                clientes = new Cliente[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++)
                {
                    clientes[i] = new Cliente(rs.getString("cpf"), rs.getString("nome"), rs.getString("ddn"), rs.getInt("idade"), rs.getString("senha"), rs.getString("email"));
                }
            }
            st.close();
        }
        catch (Exception e)
        {
             System.err.println(e.getMessage());   
        }
        return clientes;
    }







    
}