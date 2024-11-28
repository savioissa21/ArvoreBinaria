import java.util.Scanner;

public class CadastroClientesArquivo {
    static String nomeArquivo = "clientes.txt";
    static Arvore arvore = new Arvore();
    static Cliente cliente;

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        teclado.useDelimiter(System.getProperty("line.separator"));
        carregaDados();

        int opcao;

        do {
            montaMenu();
            opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("-- Inserindo clientes no arquivo e na árvore --------");
                    System.out.println("Informe o Nome: ");
                    String nome = teclado.next();

                    System.out.println("Informe o CPF: ");
                    long cpf = teclado.nextLong();

                    System.out.println("email: ");
                    String email = teclado.next();

                    System.out.println("Telefone: ");
                    String telefone = teclado.next();

                    System.out.println("Endereço: ");
                    String endereco = teclado.next();

                    Cliente cliente = new Cliente(nome, cpf, email, telefone, endereco);

                    // Grava dados no arquivo e insere na árvore
                    grava(cliente);
                    arvore.insere(cliente);

                    System.out.println("-----------------------------------------");
                    break;
                case 2:
                    System.out.println("-- Listando clientes do arquivo ---------");
                    lista();
                    System.out.println("-----------------------------------------");
                    arvore.caminhar();
                    break;
                case 0:
                    System.out.println("-- Apagando cliente do arquivo ----------");
                    System.out.println("CPF do cliente: ");
                    String cpfRemover = teclado.next();
                    remove(cpfRemover);
                    System.out.println("-----------------------------------------");
                    break;
                case 3:
                    System.out.println("-- Finalizando programa -----------------");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (opcao != 3);
    }

    static void montaMenu() {
        System.out.println("");
        System.out.println("0 - Apagar cliente");
        System.out.println("1 - Novo cliente");
        System.out.println("2 - Lista clientes");
        System.out.println("3 - Finalizar");
        System.out.println("Digite a opcao desejada: ");
        System.out.println("");
    }

    static void grava(Cliente cliente) {
        Arquivo a = Arquivo.abrirEscrita(nomeArquivo, "append");
        Arquivo.escrever(a, cliente.getNome() + "|" + cliente.getCpf() + "|" + cliente.getEmail() + "|"
                + cliente.getTelefone() + "|" + cliente.getEndereço());
        Arquivo.fechar(a);
    }

    static void lista() {
        Arquivo a = Arquivo.abrirLeitura(nomeArquivo);
        String linha;
        while ((linha = Arquivo.lerLinha(a)) != null) {
            String[] dados = Arquivo.separaTokens(linha, "|");
            String cpf = dados[1];
            String nome = dados[0];
            System.out.println("Cliente: " + nome + ", CPF: " + cpf);
        }
        Arquivo.fechar(a);
    }

    static void remove(String cpfRemover) {
        Arquivo.renomear(nomeArquivo, "clientes.temp");
        Arquivo original = Arquivo.abrirLeitura("clientes.temp");
        Arquivo novo = Arquivo.abrirEscrita(nomeArquivo, "new");
        String linha;
        boolean removeu = false;

        while ((linha = Arquivo.lerLinha(original)) != null) {
            String[] dados = Arquivo.separaTokens(linha, "|");
            String cpf = dados[0];
            if (cpf.equals(cpfRemover)) {
                removeu = true;
            } else {
                Arquivo.escrever(novo, linha);
            }
        }
        if (removeu) {
            System.out.println("Removeu o cliente com cpf " + cpfRemover);
        }

        Arquivo.fechar(original);
        Arquivo.fechar(novo);

        Arquivo.apagar("clientes.temp");
        arvore.retira(cliente);

    }

    static void carregaDados() {
        Arquivo a = Arquivo.abrirLeitura(nomeArquivo); 
        String linha;
        while ((linha = Arquivo.lerLinha(a)) != null) {
            String[] dados = Arquivo.separaTokens(linha, "|");
            String nome = dados[0]; 
            long cpf = Long.parseLong(dados[1]); // Conversão correta para long
            String email = dados[2];
            String telefone = dados[3];
            String endereco = dados[4];
            
            Cliente cliente = new Cliente(nome, cpf, email, telefone, endereco);
            arvore.insere(cliente); // Insere o cliente na árvore
        }
        Arquivo.fechar(a);
    }
    
}
