public class EstoqueApp {
    public static void processarOperacaoEstoque(Estoque estoque, int cenario) throws EstoqueException {
        switch (cenario) {
            case 1:
                new ProdutoComum("Item Invalido", -10.0, 5);
                break;
            case 2:
                estoque.venderProduto(0, 99999);
                break;
            case 3:
                throw new EstoqueException("Operacao generica nao suportada pelo estoque.");
            default:
                break;
        }
    }
    public static void demonstrarSobrecargaDesconto() {
        try {
            Product p = new ProdutoComum("Item Teste", 100.0, 10);
            p.aplicarDesconto(10.0); // 10% de desconto -> preco vira 90.00
            p.aplicarDesconto(50.0, 15.0); // 50% limitado a R$ 15.00 -> preco vira 75.00
        } catch (QuantidadeInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE ESTOQUE ===\n");

        Estoque estoque = new Estoque();

        // 1. Tentativa de cadastro com quantidade negativa
        System.out.println("1. Tentando cadastrar produto com quantidade negativa:");
        try {
            Product produtoInvalido = new ProdutoComum("Produto Invalido", 150.00, -5);
            estoque.adicionarProduto(produtoInvalido);
        } catch (QuantidadeInvalidaException e) {
            System.out.println("Excecao capturada: " + e.getMessage());
        }

        System.out.println();

        // 2. Cadastro e exibicao dos produtos no estoque
        System.out.println("2. Produtos cadastrados no estoque:");
        try {
            Product caderno = new ProdutoComum("Caderno", 25.00, 10);
            Product caneta = new ProdutoComum("Caneta", 3.50, 50);
            Product iogurte = new ProdutoPerecivel("Iogurte", 5.00, 20, 2);
            Product queijo = new ProdutoPerecivel("Queijo", 30.00, 5, 10);

            estoque.adicionarProduto(caderno);
            estoque.adicionarProduto(caneta);
            estoque.adicionarProduto(iogurte);
            estoque.adicionarProduto(queijo);

            for (Product p : estoque.getProdutos()) {
                System.out.println(" - " + p.getDescricao());
            }
        } catch (QuantidadeInvalidaException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }

        System.out.println();

        // 3. Venda valida com atualizacao de estoque
        System.out.println("3. Realizando venda valida:");
        try {
            int indiceCaderno = 0;
            int quantidadeVenda = 3;
            Product p = estoque.getProdutos().get(indiceCaderno);
            estoque.venderProduto(indiceCaderno, quantidadeVenda);
            System.out.println("Venda de " + quantidadeVenda + " unidades de '" + p.getNome()
                    + "' realizada com sucesso! Estoque atual: " + p.getQuantidade());
        } catch (ProdutoIndisponivelException e) {
            System.out.println("Excecao capturada: " + e.getMessage());
        }

        System.out.println();

        // 4. Tentativa de venda alem do disponivel
        System.out.println("4. Tentando vender mais do que o disponivel:");
        try {
            int indiceCaderno = 0;
            estoque.venderProduto(indiceCaderno, 50);
        } catch (ProdutoIndisponivelException e) {
            System.out.println("Excecao capturada: " + e.getMessage());
        }

        System.out.println();

        // 5. Calculo do valor total patrimonial do estoque
        System.out.println("5. Valor total do estoque:");
        double total = estoque.calcularValorTotalEstoque();
        System.out.printf(java.util.Locale.forLanguageTag("pt-BR"), "Total: R$ %.2f\n", total);
    }
}
