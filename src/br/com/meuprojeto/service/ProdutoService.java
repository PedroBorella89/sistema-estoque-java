package br.com.meuprojeto.service;

import br.com.meuprojeto.model.Produto;

import java.util.ArrayList;
import java.util.Scanner;

// Regras do Sistema
public class ProdutoService {

    // Cadastrar produtos
    public static Produto cadastrarProduto(Scanner sc) {

        System.out.print("Digite o nome do produto: ");
        String nome = sc.nextLine();

        System.out.print("Digite o preço: ");
        double preco = sc.nextDouble();

        System.out.print("Digite a quantidade: ");
        int quantidade = sc.nextInt();

        sc.nextLine(); // limpeza do buffer

        return new Produto(nome, preco, quantidade);
    }

    // Exibir lista de produtos
    public static void listarProdutos(ArrayList<Produto> produtos) {

        for (Produto p : produtos) {
            System.out.printf(
                    "Nome: %-25s | Preço: R$ %8.2f | Quantidade: %4d | Total: R$ %8.2f%n",
                    p.getNome(),
                    p.getPreco(),
                    p.getQuantidade(),
                    p.calcularTotal()
            );
        }
    }

    // Buscar produto pelo nome
    public static Produto buscarProdutoPorNome(ArrayList<Produto> produtos, String nomeBusca) {

        for (Produto p : produtos) {
            if (p.getNome().toLowerCase().contains(nomeBusca.toLowerCase())) {
                return p;
            }
        }
        return null;
    }

    // Atualizar produto
    public static boolean atualizarProduto(
            ArrayList<Produto> produtos,
            String nomeBusca,
            Double novoPreco,
            Integer novaQuantidade // Double e Integer aceitam null, double e int não aceitam
    ) {

        Produto produto = buscarProdutoPorNome(produtos, nomeBusca);

        if (produto == null) {
            return false;
        }

        if (novoPreco != null) {
            produto.setPreco(novoPreco);
        }

        if (novaQuantidade != null) {
            produto.setQuantidade(novaQuantidade);
        }

        return true;
    }

    // Remover produto
    public static boolean removerProduto(ArrayList<Produto> produtos, String nomeBusca) {

        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nomeBusca)) {
                produtos.remove(p);
                return true;
            }
        }
        return false;
    }

    // Valor total em estoque
    public static double somarValorTotal(ArrayList<Produto> produtos) {
        double total = 0;

        for (Produto p : produtos) {
            total += p.calcularTotal();
        }
        return total;
    }
}
