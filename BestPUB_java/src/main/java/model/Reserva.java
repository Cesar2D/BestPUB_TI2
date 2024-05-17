package model;

import java.time.LocalTime;

public class Reserva {
    private int notaFisc;
    private int pessoas;
    private LocalTime horario;
    private int mesas;

    public Reserva() {
        this(0, 0, "00:00", 0);
    }

    public Reserva(int notaFisc, int pessoas, String horario, int mesas) {
        this.notaFisc = notaFisc;
        this.pessoas = pessoas;
        this.horario = LocalTime.parse(horario);
        this.mesas = mesas;
    }

    public int getNumeroNotaFiscal() {
        return (this.notaFisc);
    }

    public void setNumeroNotaFical(int notaFiscal) {
        this.notaFisc = notaFiscal;
    }

    public int getPesssoas() {
        return (this.pessoas);
    }

    public void setPessoas(int pessoas) {
        this.pessoas = pessoas;
    }

    public String getHorario() {
        return (this.horario.toString());
    }

    public void setHorario(String horario) {
        this.horario = LocalTime.parse(horario);
    }

    public int getMesas() {
        return (this.mesas);
    }

    public void setMesas(int mesas) {
        this.mesas = mesas;
    }

    public String toString() {
        return ("Reserva [ numero nota fiscal = "+ notaFisc +"; pessoas = " + pessoas + "; horario = " + horario.toString() + "; mesas = " + mesas + ";]");
    }
}