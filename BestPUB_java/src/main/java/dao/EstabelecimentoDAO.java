package dao;

import java.sql.*;
import model.Estabelecimento;

public class EstabelecimentoDAO {
    private static Connection conexao;

    public EstabelecimentoDAO() {
        conexao = null;
    }

    public boolean conectar() {
        String driverName = "org.postgresql.Driver";
        String serverName = "localhost";
        String mydatabase = "BestPUB";
        int porta = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
        String username = "postgres";
        String password = "ti@cc";
        boolean status = false;

        try {
            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, username, password);
            status = (conexao == null);
            System.out.println("Conexao efetuada!");
        } catch (ClassNotFoundException e) {
            System.err.println("Conexao NAO efetuada com o postgress -- Driver nao encontrado -- " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Conexao NAO efetuada com o postgress --" + e.getMessage());
        }
        return status;
    }

    // inserir registro no bd
    public static boolean insertEstabelecimento(Estabelecimento est) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate(
                    "INSERT INTO estabelecimento (id,nome,preco,tipos_de_comida, senha, email, cep, rua, tipo_de_estabelecimento, codigo_de_vestimenta, tematica) "
                            +
                            "VALUES (" + est.getId() + ", '" + est.getNome() + "','" + est.getTipoComida() + "','"
                            + est.getSenha() + "','" + est.getEmail() + "','" + est.getRua() + "','"
                            + est.getTipoEstabelecimento() + "','" + est.getCodigoVestimenta() + "','"
                            + est.getTematica() + "');");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    // editar registro do BD
    public static boolean updateEstabelecimento(Estabelecimento est) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE estabelecimento SET nome = '" + est.getNome() + "', preco = '" + est.getPreco()
                    + "', senha = '" + est.getSenha() + "', email = '" + est.getEmail() + "', rua = '" + est.getRua()
                    + "', tipo_de_estabelecimento = '" + est.getTipoEstabelecimento() + "', codigo_de_vestimenta = '"
                    + est.getCodigoVestimenta() + "', tematica = '" + est.getTematica() + "'" + "WHERE id = "
                    + est.getId();
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    // remover registro do BD
    public static boolean removeEstabelecimento(String id) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM estabelecimento WHERE id = '" + id + "'");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    // get dados salvos de um elemento especifico da tabela
    public static Estabelecimento getEstabelecimento(String id) {
        Estabelecimento est = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM estabelecimento WHERE id = '" + id + "'");
            if (rs.next()) {
                rs.last();
                est = new Estabelecimento(rs.getString("id"), rs.getString("nome"), rs.getString("preco"),
                        rs.getString("tipos_de_comida"), rs.getString("senha"), rs.getString("email"), 0,
                        rs.getString("cep"), rs.getString("rua"), rs.getString("tipo_de_estabelecimento"),
                        rs.getString("codigo_de_vestimenta"), rs.getString("tematica"));
                rs.beforeFirst();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return est;
    }

    // array de objetos estabelecimento contendo todos os dados do BD
    public static Estabelecimento[] listarEstabelecimentos() {
        Estabelecimento[] ests = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM estabelecimento");
            if (rs.next()) {
                rs.last();
                ests = new Estabelecimento[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    ests[i] = new Estabelecimento(rs.getString("id"), rs.getString("nome"), rs.getString("preco"),
                            rs.getString("tipos_de_comida"), rs.getString("senha"), rs.getString("email"), 0,
                            rs.getString("cep"), rs.getString("rua"), rs.getString("tipo_de_estabelecimento"),
                            rs.getString("codigo_de_vestimenta"), rs.getString("tematica"));
                }
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return ests;
    }
}