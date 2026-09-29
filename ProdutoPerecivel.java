public class ProdutoPerecivel extends Product {
    private int diasParaVencer;

    public ProdutoPerecivel(String nome, double preco, int quantidade, int diasParaVencer)
            throws QuantidadeInvalidaException {
        super(nome, preco, quantidade);
        this.diasParaVencer = diasParaVencer;
    }

    public int getDiasParaVencer() {
        return diasParaVencer;
    }

    public void setDiasParaVencer(int diasParaVencer) {
        this.diasParaVencer = diasParaVencer;
    }

    @Override
    public double calcularValorTotal() {
        double total = getPreco() * getQuantidade();
        if (diasParaVencer <= 3) {
            // Aplica 20% de desconto automático se estiver próximo do vencimento (<= 3 dias)
            return total * 0.80;
        }
        return total;
    }

    @Override
    public String getDescricao() {
        String infoValidade = (diasParaVencer <= 3)
                ? String.format(" | Validade: %2d dia(s) [Vencimento proximo! Desconto de 20%% ativo]", diasParaVencer)
                : String.format(" | Validade: %2d dia(s)", diasParaVencer);
        return super.getDescricao() + infoValidade;
    }
}
