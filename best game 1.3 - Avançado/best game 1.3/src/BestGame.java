import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// vazio 
public class BestGame {
    public BestGame() {
    }
    private static Empregado[] empregados;
    private static Cliente[] clientes;
    private static Jogo[] jogos;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        exibirMenuPrincipal();
    }

    private static void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("---- BEST GAME ----");
            System.out.println("1. Cadastrar dados");
            System.out.println("2. Exibir dados");
            System.out.println("3. Atualizar Valor");
            System.out.println("4. Alterar dados");
            System.out.println("5. Sair");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrar();
                    break;
                case 2:
                    exibirMenuExibicao();
                    break;
                case 3:
                    exibirMenuAtualizar();
                    break;
                case 4:
                    alterarDados();
                    break;
                case 5:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 5);
    }

    private static void cadastrar() {
        List<Empregado> listaEmpregados = new ArrayList<>();
        List<Jogo> listaJogos = new ArrayList<>();

        //  clientes predefinidos
        Cliente cliente1 = new Cliente("Valter");
        Cliente cliente2 = new Cliente("Carlos");
        clientes = new Cliente[]{cliente1, cliente2};

        boolean continuarCadastro = true;
        while (continuarCadastro) {
            System.out.println("---- CADASTRO ----");

            System.out.println("Escolha o empregado:");
            System.out.println("1. Maria");
            System.out.println("2. José");
            System.out.print("Opção: ");
            int opcaoEmpregado = scanner.nextInt();
            scanner.nextLine();

            String nomeEmpregado;
            if (opcaoEmpregado == 1) {
                nomeEmpregado = "Maria";
            } else if (opcaoEmpregado == 2) {
                nomeEmpregado = "José";
            } else {
                System.out.println("Opção inválida. Tente novamente.");
                continue;
            }

            System.out.print("Escolha o cliente (0 para Valter, 2 para Carlos): ");
            int opcaoCliente = scanner.nextInt();
            scanner.nextLine();

            Cliente cliente;
            if (opcaoCliente == 0 || opcaoCliente == 1) {
                cliente = clientes[opcaoCliente];
            } else {
                System.out.println("Opção inválida. Tente novamente.");
                continue;
            }

            System.out.print("Digite o nome do jogo: ");
            String nomeJogo = scanner.nextLine();
            System.out.print("Digite o valor do jogo: ");
            double valorJogo = scanner.nextDouble();
            scanner.nextLine(); // Limpar o buffer do scanner

            // Criação de objetos
            Empregado empregado = new Empregado(nomeEmpregado);
            listaEmpregados.add(empregado);

            Jogo jogo = new Jogo(nomeJogo, valorJogo);
            jogo.setEmpregado(empregado);
            jogo.setCliente(cliente);
            listaJogos.add(jogo);

            System.out.print("Deseja realizar outro cadastro? (S/N): ");
            String opcao = scanner.nextLine();
            continuarCadastro = opcao.equalsIgnoreCase("S");
        }

        // Converter as listas para arrays
        empregados = listaEmpregados.toArray(new Empregado[0]);
        jogos = listaJogos.toArray(new Jogo[0]);
    }

    private static void exibirMenuExibicao() {
        if (jogos == null || jogos.length == 0) {
            System.out.println("Não há jogos cadastrados.");
            return;
        }
    
        System.out.println("---- MENU DE EXIBIÇÃO ----");
        for (int i = 0; i < jogos.length; i++) {
            Jogo jogo = jogos[i];
            Cliente cliente = jogo.getCliente();
            Empregado empregado = jogo.getEmpregado();
            System.out.println("Índice do jogo: " + i);
            System.out.println("Nome do jogo: " + jogo.getNome());
            if (cliente != null) {
                System.out.println("Cliente: " + cliente.getNome());
            } else {
                System.out.println("Cliente: Não informado");
            }
            if (empregado != null) {
                System.out.println("Empregado: " + empregado.getNome());
            } else {
                System.out.println("Empregado: Não informado");
            }
            System.out.println("Valor do jogo: " + jogo.getValor());
            System.out.println("-----------------------------");
        }
    }
    

    private static void exibirMenuAtualizar() {
        if (jogos == null || jogos.length == 0) {
            System.out.println("Não há jogos cadastrados.");
            return;
        }
    
        System.out.println("---- MENU ATUALIZAR ----");
        for (int i = 0; i < jogos.length; i++) {
            Jogo jogo = jogos[i];
            System.out.println("Índice do jogo: " + i);
            System.out.println("Nome do jogo: " + jogo.getNome());
            System.out.println("Valor do jogo: " + jogo.getValor());
            System.out.println("-----------------------------");
        }
    
        System.out.print("Escolha o índice do jogo a ser atualizado: ");
        int indice = scanner.nextInt();
        scanner.nextLine();
    
        if (indice >= 0 && indice < jogos.length) {
            Jogo jogo = jogos[indice];
    
            System.out.println("---- Opções de atualização ----");
            System.out.println("1. Atualizar com taxa fixa (10%)");
            System.out.println("2. Atualizar com nova taxa");
    
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
    
            if (opcao == 1) {
                jogo.calcularValor();
                System.out.println("Valor do jogo atualizado: " + jogo.getValor());
            } else if (opcao == 2) {
                System.out.print("Digite a nova taxa (em %): ");
                double taxa = scanner.nextDouble();
                scanner.nextLine();
                jogo.calcularValor(taxa);
                System.out.println("Valor do jogo atualizado: " + jogo.getValor());
            } else {
                System.out.println("Opção inválida.");
            }
        } else {
            System.out.println("Índice inválido.");
        }
    }
    
    private static void alterarDados() {
        if (jogos == null || jogos.length == 0) {
            System.out.println("Não há jogos cadastrados.");
            return;
        }
    
        System.out.println("---- ALTERAÇÃO DE DADOS ----");
        for (int i = 0; i < jogos.length; i++) {
            Jogo jogo = jogos[i];
            System.out.println("Índice do jogo: " + i);
            System.out.println("Nome do jogo: " + jogo.getNome());
            System.out.println("-----------------------------");
        }
    
        System.out.print("Digite o índice do jogo que deseja alterar: ");
        int indiceJogo = scanner.nextInt();
        scanner.nextLine();
    
        if (indiceJogo >= 0 && indiceJogo < jogos.length) {
            Jogo jogoSelecionado = jogos[indiceJogo];
    
            System.out.println("1. Alterar nome do jogo");
            System.out.println("2. Alterar cliente");
            System.out.println("3. Alterar empregado");
            System.out.println("4. Alterar valor do jogo");
            System.out.print("Escolha uma opção: ");
            int opcaoAlteracao = scanner.nextInt();
            scanner.nextLine();
    
            switch (opcaoAlteracao) {
                case 1:
                    System.out.print("Digite o novo nome do jogo: ");
                    String novoNomeJogo = scanner.nextLine();
                    jogoSelecionado.setNome(novoNomeJogo);
                    System.out.println("Nome do jogo alterado com sucesso!");
                    break;
                case 2:
                    System.out.println("Escolha o novo cliente (0 para Valter, 1 para Carlos): ");
                    int opcaoCliente = scanner.nextInt();
                    scanner.nextLine();
                    if (opcaoCliente >= 0 && opcaoCliente < clientes.length) {
                        Cliente novoCliente = clientes[opcaoCliente];
                        jogoSelecionado.setCliente(novoCliente);
                        System.out.println("Cliente alterado com sucesso!");
                    } else {
                        System.out.println("Índice inválido. O cliente não foi encontrado.");
                    }
                    break;
                case 3:
                    System.out.println("Escolha o novo empregado:");
                    System.out.println("1. Maria");
                    System.out.println("2. José");
                    System.out.print("Opção: ");
                    int opcaoEmpregado = scanner.nextInt();
                    scanner.nextLine();
    
                    String nomeEmpregado;
                    if (opcaoEmpregado == 1) {
                        nomeEmpregado = "Maria";
                    } else if (opcaoEmpregado == 2) {
                        nomeEmpregado = "José";
                    } else {
                        System.out.println("Opção inválida. Nenhuma alteração realizada.");
                        break;
                    }
    
                    Empregado novoEmpregado = new Empregado(nomeEmpregado);
                    jogoSelecionado.setEmpregado(novoEmpregado);
                    System.out.println("Empregado alterado com sucesso!");
                    break;
                case 4:
                    System.out.print("Digite o novo valor do jogo: ");
                    double novoValorJogo = scanner.nextDouble();
                    scanner.nextLine();
                    jogoSelecionado.setValor(novoValorJogo);
                    System.out.println("Valor do jogo alterado com sucesso!");
                    break;
                default:
                    System.out.println("Opção inválida. Nenhuma alteração realizada.");
                    break;
            }
        } else {
            System.out.println("Índice inválido. O jogo não foi encontrado.");
        }
    }
    
}
