package application;

import model.entities.Aluguel;
import model.exceptions.DomainException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Aluguel> alugueis = new ArrayList<>();

        System.out.print("Quantos alugueis deseja cadastrar?");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            try {
                System.out.println("Analisando o #" + i + " cadastro de aluguel: ");
                System.out.print("Entre com o modelo do veículo: ");
                sc.nextLine();
                String nome = sc.nextLine();

                System.out.print("Entre com a data de início do aluguel: ");
                LocalDate dataInicio = LocalDate.parse(sc.next(), dtf);

                System.out.print("Entre com a data de término do aluguel: ");
                LocalDate dataFinal = LocalDate.parse(sc.next(), dtf);

                System.out.print("Digite o valor da diária do veículo: ");
                double precoPorDia = sc.nextDouble();

                Aluguel aluguel = new Aluguel(nome, dataInicio, dataFinal, precoPorDia);
                alugueis.add(aluguel);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida");
            } catch (DomainException e) {
                System.out.println("Erro " + e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("Erro inesperado");
            }
        }

        System.out.println("LISTA DE ALUGUEIS: ");

        double total = 0;
        for (Aluguel a : alugueis) {
            System.out.println(a);
            total += a.precoTotal();
        }

        System.out.println("O faturamento total é de: " + "R$" + String.format("%.2f", total));

        sc.close();
    }
}
