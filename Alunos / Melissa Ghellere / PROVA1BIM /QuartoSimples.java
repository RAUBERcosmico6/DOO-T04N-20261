public class QuartoSimples extends Quarto {
    private boolean temVentilador;//quarto simples do hotel

    public QuartoSimples(int numero, double valorDiaria, boolean temVentilador) {
        super(numero, valorDiaria);
        this.temVentilador = temVentilador;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Quarto Simples #" + numero + " | Diária: R$" + valorDiaria + " | Ventilador: " + (temVentilador ? "Sim" : "Não"));
    }
}
