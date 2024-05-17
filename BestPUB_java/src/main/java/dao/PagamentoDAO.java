package dao;

import java.sql.*;
import model.Pagamento;

public class PagamentoDAO {
	private Connection conexao;

	public PagamentoDAO() {
		conexao = null;
	}

	public String conectar() {

		String driverName = "com.mysql.cj.jdbc.Driver";
		String serverName = "localhost";
		String databaseName = "pagamento";
		int porta = 3306;
		String url = "jdbc:mysql://" + serverName + ":" + porta + "/" + databaseName;

		String user = "root";
		String password = "ti2cc";

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, user, password);
			return "Conectado com sucesso ao banco de dados";
		} catch (ClassNotFoundException e) {
			return "Driver JDBC não encontrado: " + e.getMessage();
		} catch (SQLException e) {
			return "Erro ao conectar ao banco de dados: " + e.getMessage();
		}
	}

	public void desconectar() {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao desconectar: " + e.getMessage());
			}
		}
	}

	public Connection getConexao() {
		return conexao; // Fornece a conexão atual, se disponível
	}

	public Pagamento[] listarPagamentos() {
		Pagamento[] pagamentos = null;
		try {
			String sql = "SELECT * FROM transacao";
			PreparedStatement pstmt = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = pstmt.executeQuery();

			// Verifica se há resultados antes de continuar
			if (!rs.next()) {
				return null; // Retorna null se não houver pagamentos
			}

			// Move o cursor de volta para a primeira linha
			rs.beforeFirst();

			// Contagem do número de linhas no ResultSet
			rs.last();
			int rowCount = rs.getRow();
			rs.beforeFirst();

			// Inicialização do array de pagamentos com o tamanho correto
			pagamentos = new Pagamento[rowCount];

			// Preenchimento do array de pagamentos
			int i = 0;
			while (rs.next()) {
				Pagamento pagamento = new Pagamento();
				pagamento.setId(rs.getString("id"));
				pagamento.setNomeTitular(rs.getString("nomeTitular"));
				pagamento.setFormaPagamento(rs.getString("formaPagamento"));
				pagamento.setCvv(rs.getInt("cvv"));
				pagamento.setNumeroCartao(rs.getString("numeroCartao"));
				pagamento.setValidade(rs.getString("validade"));

				pagamentos[i] = pagamento;
				i++;
			}

			// Fechamento do Statement e do ResultSet
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Falha na consulta: " + e.getMessage());
		}
		return pagamentos;
	}

	public void deletePagamento(Pagamento pagamento) {

		try {
			String sql = "delete from transacao where id = ?";
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1, pagamento.getId());

		} catch (SQLException e) {
			System.out.println(" deletar falhou " + e.getMessage()); // Em caso de erro

		}
	}

	public void insertPagamento(Pagamento pagamento) {
		try {
			String sql = "INSERT INTO transacao (nomeTitular, formaPagamento, cvv, numeroCartao, validade, dataHora) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conexao.prepareStatement(sql);

			pstmt.setString(1, pagamento.getNomeTitular());
			pstmt.setString(2, pagamento.getFormaPagamento());
			pstmt.setInt(3, pagamento.getCvv());
			pstmt.setString(4, pagamento.getNumeroCartao().replaceAll("\\s+", "")); // Remover espaços em branco
			pstmt.setString(5, pagamento.getValidade());
			pstmt.setString(6, pagamento.getDataHora());

			pstmt.executeUpdate(); // Executa a inserção no banco de dados

			pstmt.close(); // Fecha o PreparedStatement após o uso
		} catch (SQLException e) {
			System.out.println("Falha ao inserir pagamento: " + e.getMessage());
		}
	}

	public void updatePagamento(Pagamento pagamento) {
		try {
			String sql = "UPDATE transacao SET nomeTitular = ?, formaPagamento = ?, cvv = ?, numeroCartao = ?, validade = ?, dataHora = ? WHERE id = ?";
			PreparedStatement pstmt = conexao.prepareStatement(sql);

			pstmt.setString(1, pagamento.getNomeTitular());
			pstmt.setString(2, pagamento.getFormaPagamento());
			pstmt.setInt(3, pagamento.getCvv());
			pstmt.setString(4, pagamento.getNumeroCartao());
			pstmt.setString(5, pagamento.getValidade());
			pstmt.setString(6, pagamento.getDataHora());
			pstmt.setString(7, pagamento.getId());

		} catch (SQLException e) {
			System.out.println("Falha ao atualizar pagamento: " + e.getMessage());
		}
	}

	public Pagamento getPagamento(int id) {
		Pagamento pagamento = null;
		try {
			String sql = "SELECT * FROM transacao WHERE id=?";
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				pagamento = new Pagamento();
				pagamento.setId(rs.getString("id"));
				pagamento.setNomeTitular(rs.getString("nomeTitular"));
				pagamento.setFormaPagamento(rs.getString("formaPagamento"));
				pagamento.setCvv(rs.getInt("cvv"));
				pagamento.setNumeroCartao(rs.getString("numeroCartao"));
				pagamento.setValidade(rs.getString("validade"));
				pagamento.setDataHora(rs.getString("dataHora"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Falha na consulta: " + e.getMessage());
		}
		return pagamento;
	}

	public boolean removePagamento(int id){
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM pagamento WHERE id = '" + id + "';");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return status;
	}

}
