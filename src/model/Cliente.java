package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cliente {

    private String dni;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private Sexo sexo;
    private int altura; // cm
    private ArrayList<Peso> historico;

    // Alta básica
    public Cliente(String nombre, String apellidos, LocalDate fechaNacimiento,
                   String dni, Sexo sexo) {
        this(nombre, apellidos, fechaNacimiento, dni, sexo, 0);
    }

    // Alta completa
    public Cliente(String nombre, String apellidos, LocalDate fechaNacimiento,
                   String dni, Sexo sexo, int altura) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
        this.sexo = sexo;
        this.altura = altura;
        this.historico = new ArrayList<>();
    }

    // Pesar hoy
    public void pesar(double peso) {
        historico.add(new Peso(this, peso, LocalDate.now()));
    }

    // Pesar en una fecha concreta
    public void pesar(double peso, LocalDate fecha) {
        historico.add(new Peso(this, peso, fecha));
    }

    public Peso getUltimoPeso() {
        if (historico.isEmpty()) return null;
        return historico.get(historico.size() - 1);
    }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public Sexo getSexo() { return sexo; }
    public int getAltura() { return altura; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cliente)) return false;
        Cliente c = (Cliente) o;
        return dni.equals(c.dni);
    }

    @Override
    public String toString() {
        String texto = "Cliente con DNI: " + dni +
                " nombre completo: " + nombre + " " + apellidos +
                " con fecha de nacimiento: " + fechaNacimiento +
                " sexo " + sexo + " y altura: " + altura + "\n";

        if (historico.isEmpty())
            texto += "último peso: sin peso registrado aún";
        else
            texto += "último peso: " + getUltimoPeso();

        return texto;
    }

    public String imprimirHistorico() {
        return toString() + " pesos: " + historico;
    }
}
