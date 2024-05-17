package service;

import dao.PagamentoDAO;
import model.Pagamento;
import spark.Request;
import spark.Response;


public class PagamentoService {
    private PagamentoDAO pagamentoDAO;
    
    public PagamentoService(){
        pagamentoDAO = new PagamentoDAO();
        pagamentoDAO.conectar();
    }

    public Object insert(Request request, Response response){
        String id = request.queryParams("id");
        String nomeTitular = request.queryParams("nomeTitular");
        String formaPagamento = request.queryParams("formaPagamento");
        String cvv = request.queryParams("cvv");
        String numeroCartao = request.queryParams("numeroCartao");
        String validade = request.queryParams("validade");
        String dataHora = request.queryParams("dataHora");

        Pagamento pag = new Pagamento(id, nomeTitular, formaPagamento, Integer.parseInt(cvv), numeroCartao,
        validade, dataHora);

        pagamentoDAO.insertPagamento(pag);
        response.status(201);

        return id;
    }

    public String get(Request request, Response response){
        String id = request.params("id");

        Pagamento pag = (Pagamento) pagamentoDAO.getPagamento(Integer.parseInt(id));

        if(pag != null){
            return (pag.toString());
        } else {
            response.status(404);
            return "Nao encontrado";
        }
    }

    public String update(Request request, Response response){
        int id = Integer.parseInt(request.params("id"));
        Pagamento pag = (Pagamento) pagamentoDAO.getPagamento(id);

        if(pag != null){
            pag.setId(request.queryParams("id"));
            pag.setNomeTitular(request.queryParams("nomeTitular"));
            pag.setFormaPagamento(request.queryParams("formaPagamento"));
            pag.setCvv(Integer.parseInt(request.queryParams("cvv")));
            pag.setNumeroCartao(request.queryParams("numeroCartao"));
            pag.setValidade(request.queryParams("validade"));
            pag.setDataHora(request.queryParams("dataHora"));

            pagamentoDAO.updatePagamento(pag);
            return pag.toString();
        } else {
            response.status(404);
            return "Nao encontrado";
        }
    }

    public String delete(Request request, Response response){
        int id = Integer.parseInt(request.params("id"));

        Pagamento pag = (Pagamento) pagamentoDAO.getPagamento(id);

        if(pag != null){
            pagamentoDAO.removePagamento(id);
            response.status(200);
            return "" + id;
        } else {
            response.status(404);
            return "Nao encontrado";
        }
    }

    public String getAll(Request request, Response response){
        Pagamento[] pags = pagamentoDAO.listarPagamentos();
        String resp = "";

        if(pags != null){
            for(int i = 0; i < pags.length; i++){
                resp = resp + "\n" + pags[i].toString();
            }
        }

        return resp;
    }

}
