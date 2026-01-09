package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Peso {

    private double peso;
    private LocalDate fecha;
    private double imc;
    private String rangoIMC;
    private double grasa;
    private String rangoGrasa;
    private double tmb;
    private double pesoAjustado;

    public Peso(Cliente c, double peso, LocalDate fecha) {
        this.peso = peso;
        this.fecha = fecha;
        calcularDatos(c);
    }

    private void calcularDatos(Cliente c) {
        int edad = (int) ChronoUnit.YEARS.between(
                c.getFechaNacimiento(), fecha);

        double alturaM = c.getAltura() / 100.0;

        // IMC
        imc = peso / (alturaM * alturaM);
        if (imc < 18.5) rangoIMC = "Bajo peso";
        else if (imc < 25) rangoIMC = "Rango normal";
        else if (imc < 30) rangoIMC = "Sobrepeso";
        else rangoIMC = "Obeso";

        int sexoNum = (c.getSexo() == Sexo.MASCULINO) ? 0 : 1;

        // Grasa corporal
        grasa = -44.988 + (0.503 * edad) + (10.689 * sexoNum)
                + (3.172 * imc) - (0.026 * imc * imc)
                + (0.181 * imc * sexoNum) - (0.02 * imc * edad)
                - (0.005 * imc * imc * sexoNum)
                + (0.00021 * imc * imc * edad);

        if (c.getSexo() == Sexo.MASCULINO)
            rangoGrasa = grasa < 25 ? "Grasa en promedio" : "Grasa en nivel obeso";
        else
            rangoGrasa = grasa < 32 ? "Grasa en promedio" : "Grasa en nivel obeso";

        // TMB
        tmb = 10 * peso + 6.25 * c.getAltura()
                - 5 * edad + (c.getSexo() == Sexo.MASCULINO ? 5 : -161);

        // Peso ajustado
        double pi = (c.getSexo() == Sexo.MASCULINO)
                ? 52 + 0.75 * (c.getAltura() - 152)
                : 49 + 0.67 * (c.getAltura() - 152);

        pesoAjustado = pi + 0.4 * (peso - pi);
    }

    @Override
    public String toString() {
        return "En el dia: " + fecha +
                " tiene peso: " + peso +
                " el peso ajustado es: " + pesoAjustado +
                " su IMC está en el rango de " + rangoIMC +
                " y su rango de grasa corporal en " + rangoGrasa +
                ", finalmente su tasa metabólica basal es de " + tmb + "kcal/dia";
    }
}
