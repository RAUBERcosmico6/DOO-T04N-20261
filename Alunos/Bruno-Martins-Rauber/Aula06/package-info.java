package metodos;

 class Vendedor {
    // Atributos
    String nome, loja, cidade, bairro, rua;
    int idade;
    double salarioBase;
    double[] salarioRecebido = {2100, 2500, 2800}; // Valores iniciais

    // MÉTODO: apresentarse
    public void apresentarse() {
        System.out.println("Olá! Me chamo " + nome + ", tenho " + idade + " anos e trabalho na loja " + loja);
    }

    // MÉTODO: calcularMedia (A lógica fica armazenada aqui!)
    public double calcularMedia() {
        double soma = 0;
        for (int i = 0; i < salarioRecebido.length; i++) {
            soma += salarioRecebido[i];
        }
        return soma / salarioRecebido.length;
    }

    // MÉTODO: calcularBonus
    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}class Cliente {
    String nome, cidade, bairro, rua;
    int idade;

    public void apresentarse() {
        System.out.println("Cliente: " + nome + " | Idade: " + idade);
    }
}class Loja {
    String nomeFantasia, razaoSocial, cnpj, cidade, bairro, rua;
    Vendedor[] vendedores;
    Cliente[] clientes;

    public void contarClientes() {
        int qtd = (clientes != null) ? clientes.length : 0;
        System.out.println("Quantidade de clientes: " + qtd);
    }

    public void contarVendedores() {
        int qtd = (vendedores != null) ? vendedores.length : 0;
        System.out.println("Quantidade de vendedores: " + qtd);
    }

    public void apresentarse() {
        System.out.println("Loja: " + nomeFantasia + " | CNPJ: " + cnpj);
        System.out.println("Endereço: " + rua + ", " + bairro + " - " + cidade);
    }
}
 