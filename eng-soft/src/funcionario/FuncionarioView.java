package funcionario;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import usuario.Usuario;

public class FuncionarioView {
    private Scanner scanner;
    private FuncionarioController controller;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public FuncionarioView(FuncionarioController controller) {
        this.scanner = new Scanner(System.in);
        this.controller = controller;
    }

    public void exibirMenuPrincipal() {
        int opcao = 0;
        do {
            System.out.println("\n========== GERENCIAR FUNCIONÁRIOS ==========");
            System.out.println("1. Listar Funcionários");
            System.out.println("2. Criar Novo Funcionário");
            System.out.println("3. Buscar Funcionário por ID");
            System.out.println("4. Listar Gerentes");
            System.out.println("5. Atualizar Funcionário");
            System.out.println("6. Deletar Funcionário");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                
                switch (opcao) {
                    case 1:
                        listarFuncionarios();
                        break;
                    case 2:
                        criarFuncionario();
                        break;
                    case 3:
                        buscarFuncionarioPorId();
                        break;
                    case 4:
                        listarGerentes();
                        break;
                    case 5:
                        atualizarFuncionario();
                        break;
                    case 6:
                        deletarFuncionario();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("✗ Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Digite um número válido!");
            }
        } while (opcao != 0);
    }

    private void listarFuncionarios() {
        List<Funcionario> funcionarios = controller.listarFuncionarios();
        
        if (funcionarios.isEmpty()) {
            System.out.println("\n✗ Nenhum funcionário cadastrado.");
            return;
        }

        System.out.println("\n========== LISTA DE FUNCIONÁRIOS ==========");
        System.out.printf("%-5s | %-20s | %-20s | %-8s\n", "ID", "Nome", "Email", "Gerente");
        System.out.println("--------|------------------------|---------|---------");
        
        for (Funcionario func : funcionarios) {
            String gerente = func.isEhGerente() ? "Sim" : "Não";
            System.out.printf("%-5d | %-20s | %-20s | %-8s\n", 
                func.getIdUsuario(), 
                truncar(func.getNome(), 20),
                truncar(func.getEmail(), 20),
                gerente
            );
        }
    }

    private void criarFuncionario() {
        System.out.println("\n========== CRIAR NOVO FUNCIONÁRIO ==========");
        
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            System.out.print("Data de Admissão (dd/MM/yyyy): ");
            Date dataAdmissao;
            try {
                dataAdmissao = dateFormat.parse(scanner.nextLine());
            } catch (ParseException e) {
                System.out.println("Data inválida! Usando a data atual.");
                dataAdmissao = new Date();
            }

            System.out.print("É gerente? (s/n): ");
            boolean ehGerente = scanner.nextLine().equalsIgnoreCase("s");

            Funcionario funcionario = new Funcionario(nome, cpf, email, senha, dataAdmissao, ehGerente);
            if (controller.criarFuncionario(funcionario)) {
                System.out.println("✓ Funcionário criado com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("✗ Erro ao processar dados!");
        }
    }

    private void buscarFuncionarioPorId() {
        try {
            System.out.print("\nDigite o ID do funcionário: ");
            int id = Integer.parseInt(scanner.nextLine());

            Funcionario func = controller.buscarFuncionarioPorId(id);
            
            if (func != null) {
                exibirDetalhesFuncionario(func);
            } else {
                System.out.println("✗ Funcionário não encontrado!");
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ ID inválido!");
        }
    }

    private void listarGerentes() {
        List<Funcionario> gerentes = controller.listarGerentes();
        
        if (gerentes.isEmpty()) {
            System.out.println("\n✗ Nenhum gerente cadastrado.");
            return;
        }

        System.out.println("\n========== LISTA DE GERENTES ==========");
        System.out.printf("%-5s | %-20s | %-25s\n", "ID", "Nome", "Email");
        System.out.println("--------|------------------------|---------");
        
        for (Funcionario gerente : gerentes) {
            System.out.printf("%-5d | %-20s | %-25s\n", 
                gerente.getIdUsuario(), 
                truncar(gerente.getNome(), 20),
                truncar(gerente.getEmail(), 25)
            );
        }
    }

    private void atualizarFuncionario() {
        try {
            System.out.print("\nDigite o ID do funcionário a atualizar: ");
            int id = Integer.parseInt(scanner.nextLine());

            Funcionario func = controller.buscarFuncionarioPorId(id);
            
            if (func == null) {
                System.out.println("✗ Funcionário não encontrado!");
                return;
            }

            System.out.println("\n========== ATUALIZAR FUNCIONÁRIO ==========");
            System.out.print("Novo nome (deixe em branco para não alterar): ");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()) func.setNome(nome);

            System.out.print("Novo email: ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) func.setEmail(email);

            System.out.print("É gerente? (s/n, deixe em branco para manter): ");
            String ehGerenteStr = scanner.nextLine();
            if (!ehGerenteStr.isEmpty()) {
                func.setEhGerente(ehGerenteStr.equalsIgnoreCase("s"));
            }

            if (controller.atualizarFuncionario(func)) {
                System.out.println("✓ Funcionário atualizado com sucesso!");
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ Erro ao processar dados!");
        }
    }

    private void deletarFuncionario() {
        try {
            System.out.print("\nDigite o ID do funcionário a deletar: ");
            int id = Integer.parseInt(scanner.nextLine());

            if (controller.buscarFuncionarioPorId(id) == null) {
                System.out.println("✗ Funcionário não encontrado!");
                return;
            }

            System.out.print("Tem certeza? (s/n): ");
            String confirmacao = scanner.nextLine();
            
            if (confirmacao.equalsIgnoreCase("s")) {
                if (controller.removerFuncionario(id)) {
                    System.out.println("✓ Funcionário deletado com sucesso!");
                }
            } else {
                System.out.println("Operação cancelada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ ID inválido!");
        }
    }

    public void exibirDetalhesFuncionario(Funcionario func) {
        System.out.println("\n========== DETALHES DO FUNCIONÁRIO ==========");
        System.out.println("ID: " + func.getIdUsuario());
        System.out.println("Nome: " + func.getNome());
        System.out.println("CPF: " + func.getCpf());
        System.out.println("Email: " + func.getEmail());
        System.out.println("Data de Admissão: " + dateFormat.format(func.getDataAdmissao()));
        System.out.println("É Gerente: " + (func.isEhGerente() ? "Sim" : "Não"));
    }

    private String truncar(String str, int maxLen) {
        if (str.length() > maxLen) {
            return str.substring(0, maxLen - 3) + "...";
        }
        return str;
    }
}
