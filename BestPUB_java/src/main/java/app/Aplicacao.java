package app;

import static spark.Spark.*;

import service.ClienteService;
import service.EstabelecimentoService;
import service.PagamentoService;
import service.ReservaService;

public class Aplicacao {

	private static ClienteService clienteService = new ClienteService();

	private static EstabelecimentoService estabelecimentoService = new EstabelecimentoService();

	private static PagamentoService pagamentoService = new PagamentoService();

	private static ReservaService reservaService = new ReservaService();

	public static void main(String[] args) {
		port(4567);

		post("/registrocliente", (request, response) -> clienteService.insert(request, response));
		get("/registrocliente/:cpf", (request, response) -> clienteService.get(request, response));
		get("/registrocliente/update/:cpf", (request, response) -> clienteService.update(request, response));
		get("/registrocliente/delete/:cpf", (request, response) -> clienteService.delete(request, response));
		get("/registrocliente", (request, response) -> clienteService.getAll(request, response));

		post("/estabelecimento", (request, response) -> estabelecimentoService.insert(request, response));
		get("/estabelecimento/:id", (request, response) -> estabelecimentoService.get(request, response));
		get("/estabelecimento/update/:id", (request, response) -> estabelecimentoService.update(request, response));
		get("/estabelecimento/delete/:id", (request, response) -> estabelecimentoService.delete(request, response));
		get("/estabelecimento", (request, response) -> estabelecimentoService.getAll(request, response));

		post("/registro_de_pagamento", (request, response) -> pagamentoService.insert(request, response));
		get("/registro_de_pagamento/:id", (request, response) -> pagamentoService.get(request, response));
		get("/registro_de_pagamento/update/:id", (request, response) -> pagamentoService.update(request, response));
		get("/registro_de_pagamento/delete/:id", (request, response) -> pagamentoService.delete(request, response));
		get("/registro_de_pagamento", (request, response) -> pagamentoService.getAll(request, response));

		post("/reserva", (request, response) -> reservaService.insert(request, response));
		get("/reserva/:numero_da_nota_fiscal", (request, response) -> reservaService.get(request, response));
		get("/reserva/update/:numero_da_nota_fiscal", (request, response) -> reservaService.update(request, response));
		get("/reserva/delete/:numero_da_nota_fiscal", (request, response) -> reservaService.delete(request, response));
		get("/reserva", (request, response) -> reservaService.getAll(request, response));

		
	}
}
