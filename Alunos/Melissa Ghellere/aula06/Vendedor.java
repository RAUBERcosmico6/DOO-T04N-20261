package Alunos.Melissa_Ghellere.aula06;

public class Vendedor {
    private String nome;
    private double salarioBase;

    public Vendedor(String nome, double salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    public void apresentarse() {
        System.out.println("Vendedor: " + nome + " | Salário: " + salarioBase);
    }
}
