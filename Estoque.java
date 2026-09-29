import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Estoque {
    private List<Product> produtos;

    public Estoque() {
        this.produtos = new ArrayList<>();
    }

    public void adicionarProduto(Product p) {
        if (p != null) {
            produtos.add(p);
        }
    }

    public void venderProduto(int indice, int quantidade) throws ProdutoIndisponivelException {
        if (indice < 0 || indice >= produtos.size()) {
            throw new ProdutoIndisponivelException(
                "Indice invalido no estoque: " + indice + ". Total de itens cadastrados: " + produtos.size()
            );
        }
        produtos.get(indice).vender(quantidade);
    }

    public double calcularValorTotalEstoque() {
        double total = 0.0;
        for (Product p : produtos) {
            total += p.calcularValorTotal();
        }
        return total;
    }

    public List<Product> getProdutos() {
        return Collections.unmodifiableList(produtos);
    }

    public int getQuantidadeProdutos() {
        return produtos.size();
    }
}
