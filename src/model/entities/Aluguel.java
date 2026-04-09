package model.entities;

import model.exceptions.DomainException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Aluguel {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String modelo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private double precoPorDia;

    public Aluguel(String modelo, LocalDate dataInicio, LocalDate dataFim, double precoPorDia) {
        LocalDate agora = LocalDate.now();
        if(dataInicio.isBefore(agora) || dataFim.isBefore(agora)) {
            throw new DomainException("Alugueis disponíveis apenas para datas futuras");
        }
        if(!dataFim.isAfter(dataInicio)) {
            throw new DomainException("A data de fim deve ser após a data inicial");
        }
        if(precoPorDia <= 0) {
            throw new DomainException("Esse valor não pode ser atribuído para o aluguel do veículo");
        }

        this.modelo = modelo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.precoPorDia = precoPorDia;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public double getPrecoPorDia() {
        return precoPorDia;
    }

    public double precoTotal() {
        long diasDeUso = ChronoUnit.DAYS.between(dataInicio, dataFim);
        return precoPorDia * diasDeUso;
    }

    @Override
    public String toString() {
        return "Modelo:" + modelo + " | " +
                "Início: " + dataInicio.format(dtf) +  " | " +
                "Fim: " + dataFim.format(dtf) + " | " + "Total: " + String.format("%.2f", precoTotal());
    }
}
