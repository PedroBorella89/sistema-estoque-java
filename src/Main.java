import br.com.meuprojeto.model.Produto;
import br.com.meuprojeto.service.ProdutoService;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void exibirMenu() {
        System.out.println("\n==== MENU ====");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Atualizar produto");
        System.out.println("4 - Remover produto");
        System.out.println("5 - Buscar produto por nome");
        System.out.println("6 - Exibir valor total em estoque");

        System.out.println("0 - Sair");
    }

    public static void main(String[] args) {

        // Dados iniciais (Banco de dados)
        ArrayList<Produto> produtos = new ArrayList<>();

        produtos.add(new Produto("Whey Concentrado 1kg", 129.90, 30));
        produtos.add(new Produto("Creatina", 79.90, 20));
        produtos.add(new Produto("Pre-treino", 99.90, 10));
        produtos.add(new Produto("Omega3", 79.90, 10 ));
        produtos.add(new Produto("Coqueteleira", 19.90, 50));

        // Criar o Scanner
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);

        // Criar o menu interativo
        int opcao;

        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpeza do buffer

            switch (opcao) {

                case 1:
                    Produto produto = ProdutoService.cadastrarProduto(sc); // cria um produto
                    produtos.add(produto); // adiciona na lista 'produtos'
                    System.out.println("Produto cadastrado com sucesso!");
                    break;

                case 2:
                    ProdutoService.listarProdutos(produtos);
                    break;

                case 3:
                    System.out.print("Digite o nome do produto para atualizar: ");
                    String nomeAtualizar = sc.nextLine();

                    Produto produtoEncontrado = ProdutoService.buscarProdutoPorNome(produtos, nomeAtualizar);

                    if (produtoEncontrado == null) {
                        System.out.println("Produto não encontrado.");
                        break;
                    }

                    Double novoPreco = null;
                    Integer novaQuantidade = null;

                    System.out.print("Deseja atualizar o preço? (s/n): ");
                    String alterarPreco = sc.nextLine();

                    if (alterarPreco.equalsIgnoreCase("s")) {
                        System.out.print("Digite o novo preço: ");
                        novoPreco = sc.nextDouble();
                        sc.nextLine();
                    }

                    System.out.print("Deseja alterar a quantidade? (s/n): ");
                    String alterarQuantidade = sc.nextLine();

                    if (alterarQuantidade.equalsIgnoreCase("s")) {
                        System.out.print("Digite a nova quantidade: ");
                        novaQuantidade = sc.nextInt();
                        sc.nextLine();
                    }

                    boolean atualizado = ProdutoService.atualizarProduto(
                            produtos,
                            nomeAtualizar,
                            novoPreco,
                            novaQuantidade
                    );

                    if (atualizado) {
                        System.out.println("Produto atualizado com sucesso!");
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Digite o nome do produto para remover: ");
                    String nomeRemover = sc.nextLine();

                    boolean removido = ProdutoService.removerProduto(produtos, nomeRemover);

                    if (removido) {
                        System.out.println("Produto removido com sucesso!");
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;

                case 5:
                    System.out.print("Digite o nome do produto: ");
                    String nomeBusca = sc.nextLine();

                    Produto produtoBuscado = ProdutoService.buscarProdutoPorNome(produtos, nomeBusca);

                    if (produtoBuscado != null) {
                        System.out.println("== Produto encontrado ==");
                        System.out.println("Nome: " + produtoBuscado.getNome());
                        System.out.printf("Preço: R$ %.2f%n", produtoBuscado.getPreco());
                        System.out.println("Quantidade: " + produtoBuscado.getQuantidade());
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;

                case 6:
                    double total = ProdutoService.somarValorTotal(produtos);
                    System.out.printf("O valor total em estoque é: %.2f%n", total);
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 0);

        sc.close();
    }
}