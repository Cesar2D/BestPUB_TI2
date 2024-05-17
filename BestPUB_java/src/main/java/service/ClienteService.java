package service;

import java.math.BigInteger;
import dao.ClienteDAO;
import model.Cliente;
import spark.Request;
import spark.Response;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ClienteService {

	private ClienteDAO clienteDAO;

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

	public ClienteService() {
		clienteDAO = new ClienteDAO();
		clienteDAO.conectar();
	}

	public Object insert(Request request, Response response) {
		String nome = request.queryParams("nome");
		String cpf = request.queryParams("cpf");
		String ddn = request.queryParams("ddn");
		// int idade = Integer.parseInt()
		String email = request.queryParams("email");
		String senha = request.queryParams("senha");

		Cliente cliente = new Cliente(cpf, nome, ddn, 0, email, criptoSenha(senha));

		ClienteDAO.insertCliente(cliente);
		response.status(201);
		return nome;
	}

	public String get(Request request, Response response) {
		String cpf = request.params(":cpf");

		Cliente cliente = (Cliente) ClienteDAO.getCliente(cpf);
		if (cliente != null) {
			return (cliente.toString());
		} else {
			response.status(404);
			return "Nao encontrado";
		}
	}

	public String update(Request request, Response response) {
		String cpf = request.params(":cpf");
		String senha = request.queryParams("senha");
		Cliente cliente = (Cliente) ClienteDAO.getCliente(cpf);
		if (cliente != null) {
			cliente.setNome(request.queryParams("nome"));
			cliente.setCpf(request.queryParams("cpf"));
			cliente.setDdn(request.queryParams("dnn"));
			// int idade = Integer.parseInt()
			cliente.setEmail(request.queryParams("email"));
			cliente.setSenha(criptoSenha(senha));

			ClienteDAO.updateCliente(cliente);
			return cliente.toString();
		} else {
			response.status(404);
			return "Nao encontrado";
		}
	}

	public String delete(Request request, Response response) {
		String cpf = request.params(":cpf");
		Cliente cliente = (Cliente) ClienteDAO.getCliente(cpf);
		if (cliente != null) {
			ClienteDAO.removeCliente(cpf);
			response.status(200);
			return cpf;
		} else {
			response.status(404);
			return "Nao encontrado";
		}
	}

	public String getAll(Request request, Response response) {
		Cliente[] clientes = ClienteDAO.listarClientes();
		String ret = "";
		{
			for (int i = 0; i < clientes.length; i++) {
				ret = ret + "\n" + clientes[i].toString();
			}
		}
		return (ret);
	}

}
