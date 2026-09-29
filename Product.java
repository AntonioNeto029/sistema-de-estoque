public abstract class Product implements Vendavel {
    private String nome;
    private double preco;
    private int quantidade;

    public Product(String nome, double preco, int quantidade) throws QuantidadeInvalidaException {
        if (preco < 0 || quantidade < 0) {
            throw new QuantidadeInvalidaException(
                "Preco ou quantidade nao podem ser negativos! Informado -> Preco: R$ " 
                + String.format("%.2f", preco) + ", Quantidade: " + quantidade
            );
        }
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public abstract double calcularValorTotal();

    public String getDescricao() {
        return String.format("Produto: %-20s | Preco: R$ %8.2f | Quantidade: %3d", nome, preco, quantidade);
    }

    @Override
    public void vender(int quantidadeDesejada) throws ProdutoIndisponivelException {
        if (quantidadeDesejada <= 0) {
            throw new ProdutoIndisponivelException(
                "A quantidade para venda deve ser maior que zero! Solicitado: " + quantidadeDesejada
            );
        }
        if (quantidadeDesejada > this.quantidade) {
            throw new ProdutoIndisponivelException(
                "Estoque insuficiente para '" + this.nome + "'! Solicitado: " 
                + quantidadeDesejada + ", Disponivel em estoque: " + this.quantidade
            );
        }
        this.quantidade -= quantidadeDesejada;
    }

    public void aplicarDesconto(double percentual) {
        aplicarDesconto(percentual, Double.MAX_VALUE);
    }

    public void aplicarDesconto(double percentual, double descontoMaximo) {
        if (percentual <= 0 || descontoMaximo <= 0) {
            return;
        }
        double taxa = (percentual > 1.0) ? (percentual / 100.0) : percentual;
        double valorDesconto = this.preco * taxa;
        if (valorDesconto > descontoMaximo) {
            valorDesconto = descontoMaximo;
        }
        this.preco = Math.max(0.0, this.preco - valorDesconto);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) throws QuantidadeInvalidaException {
        if (preco < 0) {
            throw new QuantidadeInvalidaException("O preço não pode ser negativo: " + preco);
        }
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) throws QuantidadeInvalidaException {
        if (quantidade < 0) {
            throw new QuantidadeInvalidaException("A quantidade não pode ser negativa: " + quantidade);
        }
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return getDescricao();
    }
}
