//calculadora plantas da gabriela v4 - com registro de vendas por data
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class CalculadoraPlantas {
    
    // Classe interna para armazenar dados de venda
    static class Venda {
        int quantidade;
        double precoUnitario;
        double total;
        double desconto;
        LocalDate data;
        
        Venda(int quantidade, double precoUnitario, double total, double desconto, LocalDate data) {
            this.quantidade = quantidade;
            this.precoUnitario = precoUnitario;
            this.total = total;
            this.desconto = desconto;
            this.data = data;
        }
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Venda> vendas = new ArrayList<>();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        System.out.println("=== CALCULADORA DA DONA GABRIELA ===");
        System.out.println("Data de hoje: " + LocalDate.now().format(formatador));
        System.out.println("=====================================\n");

        int opcao;

        do {
            System.out.println("Escolha uma opção:");
            System.out.println("[1] Calcular total");
            System.out.println("[2] Calcular troco");
            System.out.println("[3] Ver registro de vendas");
            System.out.println("[4] Buscar vendas por dia");
            System.out.println("[5] Buscar vendas por mês");
            System.out.println("[6] Sair");
            System.out.print("Opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\n--- Calcular Total ---");
                    System.out.print("Insira a quantidade de plantas: ");
                    int qntd = sc.nextInt();
                    System.out.print("Insira o valor da unidade: ");
                    double prcouni = sc.nextDouble();
                    double ttl = qntd * prcouni;
                    double desc = 0;
                    
                    if(qntd > 10){
                        desc = ttl * 0.05;
                        ttl = ttl - desc;
                        System.out.println("Desconto especial aplicado: R$ " + String.format("%.2f", desc));
                    }
                    
                    System.out.println("Preço total da compra: R$ " + String.format("%.2f", ttl));
                    
                    // Salvar venda com data atual
                    Venda venda = new Venda(qntd, prcouni, ttl, desc, LocalDate.now());
                    vendas.add(venda);
                    System.out.println("✅ Venda salva em: " + venda.data.format(formatador));
                    break;

                case 2:
                    System.out.println("\n--- Calcular Troco ---");
                    System.out.print("Valor pago: ");
                    double recebido = sc.nextDouble();
                    System.out.print("Insira valor da compra: ");
                    double vlrcmpr = sc.nextDouble();
                    double trc = recebido - vlrcmpr;
                    
                    if (trc < 0){
                        System.out.println("Valor insuficiente! Faltam R$ " + String.format("%.2f", (-trc)));
                    } else {
                        System.out.println("Troco a ser devolvido: R$ " + String.format("%.2f", trc));
                    }
                    break;
                    
                case 3:
                    exibirRegistroVendas(vendas, formatador);
                    break;
                    
                case 4:
                    buscarVendasPorDia(sc, vendas, formatador);
                    break;
                    
                case 5:
                    buscarVendasPorMes(sc, vendas);
                    break;
                    
                case 6:
                    System.out.println("\nObrigado por utilizar! Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente!");
            }
        } while (opcao != 6);
        
        sc.close();
    }
    
    // Método para exibir todas as vendas registradas
    static void exibirRegistroVendas(ArrayList<Venda> vendas, DateTimeFormatter formatador) {
        System.out.println("\n--- Registro de Vendas ---");
        if(vendas.isEmpty()){
            System.out.println("Nenhuma venda até agora...");
        } else {
            for (int i = 0; i < vendas.size(); i++){
                Venda v = vendas.get(i);
                System.out.println("Venda " + (i+1) + 
                    " | Data: " + v.data.format(formatador) +
                    " | Quantidade: " + v.quantidade + 
                    " | Total: R$ " + String.format("%.2f", v.total) + 
                    " | Desconto: R$ " + String.format("%.2f", v.desconto));
            }
        }
    }
    
    // Método para buscar vendas por dia específico
    static void buscarVendasPorDia(Scanner sc, ArrayList<Venda> vendas, DateTimeFormatter formatador) {
        System.out.println("\n--- Buscar Vendas por Dia ---");
        System.out.print("Insira o dia (1-31): ");
        int dia = sc.nextInt();
        System.out.print("Insira o mês (1-12): ");
        int mes = sc.nextInt();
        System.out.print("Insira o ano (ex: 2026): ");
        int ano = sc.nextInt();
        
        LocalDate dataBusca = LocalDate.of(ano, mes, dia);
        ArrayList<Venda> vendasDoDia = new ArrayList<>();
        double totalDoDia = 0;
        
        for (Venda v : vendas) {
            if (v.data.equals(dataBusca)) {
                vendasDoDia.add(v);
                totalDoDia += v.total;
            }
        }
        
        if (vendasDoDia.isEmpty()) {
            System.out.println("📅 Nenhuma venda encontrada em " + dataBusca.format(formatador));
        } else {
            System.out.println("\n📊 Vendas de " + dataBusca.format(formatador) + ":");
            for (int i = 0; i < vendasDoDia.size(); i++) {
                Venda v = vendasDoDia.get(i);
                System.out.println("  • Venda " + (i+1) + 
                    " | Quantidade: " + v.quantidade + 
                    " | Total: R$ " + String.format("%.2f", v.total) + 
                    " | Desconto: R$ " + String.format("%.2f", v.desconto));
            }
            System.out.println("\n📈 Resumo do dia:");
            System.out.println("   Total de vendas: " + vendasDoDia.size());
            System.out.println("   Faturamento total: R$ " + String.format("%.2f", totalDoDia));
        }
    }
    
    // Método para buscar vendas por mês
    static void buscarVendasPorMes(Scanner sc, ArrayList<Venda> vendas) {
        System.out.println("\n--- Buscar Vendas por Mês ---");
        System.out.print("Insira o mês (1-12): ");
        int mes = sc.nextInt();
        System.out.print("Insira o ano (ex: 2026): ");
        int ano = sc.nextInt();
        
        YearMonth mesAno = YearMonth.of(ano, mes);
        ArrayList<Venda> vendasDoMes = new ArrayList<>();
        double totalDoMes = 0;
        
        for (Venda v : vendas) {
            YearMonth mesdaVenda = YearMonth.from(v.data);
            if (mesdaVenda.equals(mesAno)) {
                vendasDoMes.add(v);
                totalDoMes += v.total;
            }
        }
        
        if (vendasDoMes.isEmpty()) {
            System.out.println("📅 Nenhuma venda encontrada em " + mesAno);
        } else {
            System.out.println("\n📊 Vendas de " + mesAno + ":");
            System.out.println("   Total de vendas: " + vendasDoMes.size());
            System.out.println("   Faturamento total: R$ " + String.format("%.2f", totalDoMes));
            
            // Agrupar por dia
            System.out.println("\n   📅 Detalhamento por dia:");
            for (int dia = 1; dia <= 31; dia++) {
                double totalDia = 0;
                int quantidadeDia = 0;
                for (Venda v : vendasDoMes) {
                    if (v.data.getDayOfMonth() == dia) {
                        totalDia += v.total;
                        quantidadeDia++;
                    }
                }
                if (quantidadeDia > 0) {
                    System.out.println("      Dia " + String.format("%02d", dia) + 
                        ": " + quantidadeDia + " venda(s) | R$ " + String.format("%.2f", totalDia));
                }
            }
        }
    }
}