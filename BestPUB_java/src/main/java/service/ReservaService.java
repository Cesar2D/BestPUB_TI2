package service;

import dao.ReservaDAO;
import model.Reserva;
import spark.Request;
import spark.Response;

public class ReservaService {
    private ReservaDAO reservaDAO;

    /*
    * private String criptoSenha(String senha) {
    * String newSenha = "";
    * try {
    * MessageDigest md = MessageDigest.getInstance("MD5");
    * md.update(senha.getBytes());
    * byte[] digest = md.digest(senha.getBytes());
    * BigInteger no = new BigInteger(1, digest);
    * newSenha = no.toString(16);
    * } catch (NoSuchAlgorithmException e) {
    * throw new RuntimeException(e);
    * }
    * return newSenha;
    * }
    */

    public ReservaService() {
        reservaDAO = new ReservaDAO();
        reservaDAO.conectar();
    }

    public Object insert(Request request, Response response) {
        String numeroNotaFiscal = request.queryParams("numeronotafiscal");
        String pessoas = request.queryParams("pessoas");
        String horario = request.queryParams("horario");
        String mesas = request.queryParams("mesas");

        Reserva res = new Reserva(Integer.parseInt(numeroNotaFiscal), Integer.parseInt(pessoas), horario,
                Integer.parseInt(mesas));

        ReservaDAO.insertReserva(res);
        response.status(201);
        return numeroNotaFiscal;
    }

    public String get(Request request, Response response) {
        String numNotaFiscal = request.params("numeronotafiscal");

        Reserva reserva = (Reserva) ReservaDAO.getReserva(Integer.parseInt(numNotaFiscal));

        if (reserva != null) {
            return (reserva.toString());
        } else {
            response.status(404);
            return "Nao encontrado";
        }
    }

    public String update(Request request, Response response) {
        int numeroNotaFiscal = Integer.parseInt(request.params("numeronotafiscal"));
        Reserva reserva = (Reserva) ReservaDAO.getReserva(numeroNotaFiscal);

        if (reserva != null) {
            reserva.setNumeroNotaFical(Integer.parseInt(request.queryParams("numeronotafiscal")));
            reserva.setPessoas(Integer.parseInt(request.queryParams("pessoas")));
            reserva.setHorario(request.queryParams("horario"));
            reserva.setMesas(Integer.parseInt(request.queryParams("mesas")));

            ReservaDAO.updateReserva(reserva);
            return reserva.toString();
        } else {
            response.status(404);
            return "Nao encontrado";
        }
    }

    public String delete(Request request, Response response) {
        int numNotaFiscal = Integer.parseInt(request.params("numeronotafiscal"));
        Reserva reserva = (Reserva) ReservaDAO.getReserva(numNotaFiscal);

        if (reserva != null) {
            ReservaDAO.removeReserva(numNotaFiscal);
            response.status(200);
            return "" + numNotaFiscal;
        } else {
            response.status(404);
            return "Nao encontrado";
        }
    }

    public String getAll(Request request, Response response) {
        Reserva[] reservas = ReservaDAO.listarReservas();
        String resp = "";
        if (reservas != null) {
            for (int i = 0; i < reservas.length; i++) {
                resp = resp + "\n" + reservas[i].toString();
            }
        }

        return resp;
    }

}
