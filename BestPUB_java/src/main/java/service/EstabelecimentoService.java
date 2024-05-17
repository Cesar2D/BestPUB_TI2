package service;

import java.math.BigInteger;
import dao.EstabelecimentoDAO;
import model.Estabelecimento;
import spark.Request;
import spark.Response;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EstabelecimentoService {
	private EstabelecimentoDAO estabelecimentoDAO;

	private String criptoSenha(String senha) {
		String newSenha = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(senha.getBytes());
			byte[] digest = md.digest(senha.getBytes());
			BigInteger no = new BigInteger(1, digest);
			newSenha = no.toString(16);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return newSenha;
	}

	public EstabelecimentoService() {
		estabelecimentoDAO = new EstabelecimentoDAO();
		estabelecimentoDAO.conectar();
	}

	public Object insert(Request request, Response response) {
		String nome = request.queryParams("nome");
		String id = request.queryParams("id");
		String preco = request.queryParams("preco");
		// int idade = Integer.parseInt()
		String tiposComida = request.queryParams("comida");
		String senha = request.queryParams("senha");
		String email = request.queryParams("email");
		String cep = request.queryParams("cep");
		String rua = request.queryParams("rua");
		String tipoEstabelecimento = request.queryParams("tipoEst");
		String codigoVestimenta = request.queryParams("codVest");
		String tematica = request.queryParams("tema");

		Estabelecimento est = new Estabelecimento(id, nome, preco, tiposComida, criptoSenha(senha), email, 0, cep, rua,
				tipoEstabelecimento, codigoVestimenta, tematica);

		EstabelecimentoDAO.insertEstabelecimento(est);
		response.status(201);
		return nome;
	}

	public String get(Request request, Response response) {
		String id = request.params(":id");

		Estabelecimento est = (Estabelecimento) EstabelecimentoDAO.getEstabelecimento(id);
		if (est != null) {
			return (est.toString());
		} else {
			response.status(404);
			return "Nao encontrado";
		}
	}

	public String update(Request request, Response response) {
		String id = request.params(":id");
		String senha = request.queryParams("senha");
		Estabelecimento est = (Estabelecimento) EstabelecimentoDAO.getEstabelecimento(id);
		if (est != null) {
			est.setNome(request.queryParams("nome"));
			est.setId(request.queryParams("id"));
			est.setPreco(request.queryParams("preco"));
			// int idade = Integer.parseInt()
			est.setEmail(request.queryParams("email"));
			est.setSenha(criptoSenha(senha));
			est.setTipoComida(request.queryParams("comida"));
			est.setCep(request.queryParams("cep"));
			est.setRua(request.queryParams("rua"));
			est.setTipoEstabelecimento(request.queryParams("tipoEst"));
			est.setCodigoVestimenta(request.queryParams("codVest"));
			est.setTematica(request.queryParams("tema"));

			EstabelecimentoDAO.updateEstabelecimento(est);
			return est.toString();
		} else {
			response.status(404);
			return "Nao encontrado";
		}
	}

	public String delete(Request request, Response response) {
		String id = request.params(":id");
		Estabelecimento est = (Estabelecimento) EstabelecimentoDAO.getEstabelecimento(id);
		if (est != null) {
			EstabelecimentoDAO.removeEstabelecimento(id);
			response.status(200);
			return id;
		} else {
			response.status(404);
			return "Nao encontrado";
		}
	}

	public String getAll(Request request, Response response) {
		Estabelecimento[] ests = EstabelecimentoDAO.listarEstabelecimentos();
		String ret = "";
		{
			for (int i = 0; i < ests.length; i++) {
				ret = ret + "\n" + ests[i].toString();
			}
		}
		return (ret);
	}

}
