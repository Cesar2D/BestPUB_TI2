package dao;

import java.sql.*;
import model.Reserva;

public class ReservaDAO {
    private static Connection conexao;

    public ReservaDAO() {
        conexao = null;
    }

    public boolean conectar() {
        String driverName = "org.postgresql.Driver";
        String serverName = "localhost";
        String mydatabase = "BestPUB";
        int porta = 5432;
        String url = "jbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
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
            System.err.println("Conexao NAO efetuada com postgress -- " + e.getMessage());
        }

        return status;
    } // end conectar()

    public static boolean insertReserva(Reserva res) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate(
                    "INSERT INTO reserva(numeronotafiscal, pessoas, mesas, horario) VALUES( '"
                            + res.getNumeroNotaFiscal() + "', '" + res.getPesssoas() + "', '" + res.getMesas() + "', '"
                            + res.getHorario() + "');");
            st.close();
            status = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    } // end insertReserva()

    public static boolean updateReserva(Reserva res) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE reserva SET " + "numeronotafiscal = '" + res.getNumeroNotaFiscal() + "', pessoas = '"
                    + res.getPesssoas() + "', mesas = '" + res.getMesas() + "', horario = '" + res.getHorario() + "';";
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    } // end updateReserva()

    public static boolean removeReserva(int numNotaFiscal) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM reserva WHERE numeronotafiscal = '" + numNotaFiscal + "';");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return status;
    } // end removeReserva()

    public static Reserva getReserva(int numNotaFiscal) {
        Reserva res = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM reserva WHERE numeronotafiscal = '" + numNotaFiscal + "';");
            if (rs.next()) {
                rs.last();
                res = new Reserva(rs.getInt("numeronotafiscal"), rs.getInt("pessoas"), rs.getString("horario"),
                        rs.getInt("mesas"));
                rs.beforeFirst();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return res;
    } // end getReserva()

    public static Reserva[] listarReservas() {
        Reserva[] reses = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM reserva");

            if (rs.next()) {
                rs.last();
                reses = new Reserva[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    reses[i] = new Reserva(rs.getInt("numeronotafiscal"), rs.getInt("pessoas"), rs.getString("horario"),
                            rs.getInt("mesas"));
                }
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return reses;
    } // end listarReservas()

} // end ReservaDAO
