package controller;

import model.*;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Cliente c1 = new Cliente(
                "Vicent", "López",
                LocalDate.of(1973, 2, 14),
                "123456Z",
                Sexo.MASCULINO, 178
        );

        c1.pesar(82.3, LocalDate.of(2024,10,15));
        c1.pesar(80.3, LocalDate.of(2024,11,15));
        c1.pesar(81.3, LocalDate.of(2024,12,15));

        Cliente c2 = new Cliente(
                "Pepa", "Navarro",
                LocalDate.of(2001,3,19),
                "918251X",
                Sexo.FEMENINO, 188
        );

        c2.pesar(85.4, LocalDate.of(2024,10,15));

        System.out.println(c1);
        System.out.println("""
                
                """);
        System.out.println(c1.imprimirHistorico());
        System.out.println("""
                
                """);
        System.out.println(c2);
        System.out.println("""
                
                """);
        System.out.println(c2.imprimirHistorico());
    }
}
