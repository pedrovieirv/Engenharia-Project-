package funcionario;

import java.util.ArrayList;
import java.util.List;
import util.FuncionarioDAO;

public class FuncionarioController {
    private FuncionarioDAO funcionarioDAO;

    public FuncionarioController() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

    // Criar funcionário
    public boolean criarFuncionario(Funcionario funcionario) {
        try {
            funcionario.setIdUsuario(funcionarioDAO.proximoId());
            return funcionarioDAO.adicionar(funcionario);
        } catch (Exception e) {
            System.err.println("Erro ao criar funcionário: " + e.getMessage());
            return false;
        }
    }

    // Listar todos os funcionários
    public List<Funcionario> listarFuncionarios() {
        try {
            return funcionarioDAO.listarTodos();
        } catch (Exception e) {
            System.err.println("Erro ao listar funcionários: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Atualizar funcionário
    public boolean atualizarFuncionario(Funcionario funcionario) {
        try {
            return funcionarioDAO.atualizar(funcionario);
        } catch (Exception e) {
            System.err.println("Erro ao atualizar funcionário: " + e.getMessage());
            return false;
        }
    }

    // Remover funcionário
    public boolean removerFuncionario(int idFuncionario) {
        try {
            return funcionarioDAO.remover(idFuncionario);
        } catch (Exception e) {
            System.err.println("Erro ao remover funcionário: " + e.getMessage());
            return false;
        }
    }

    // Buscar funcionário por ID
    public Funcionario buscarFuncionarioPorId(int id) {
        try {
            return funcionarioDAO.buscarPorId(id);
        } catch (Exception e) {
            System.err.println("Erro ao buscar funcionário por ID: " + e.getMessage());
            return null;
        }
    }

    // Buscar funcionário por login
    public Funcionario buscarFuncionarioPorLogin(String login) {
        try {
            return funcionarioDAO.buscarPorLogin(login);
        } catch (Exception e) {
            System.err.println("Erro ao buscar funcionário por login: " + e.getMessage());
            return null;
        }
    }

    // Listar apenas gerentes
    public List<Funcionario> listarGerentes() {
        try {
            return funcionarioDAO.listarGerentes();
        } catch (Exception e) {
            System.err.println("Erro ao listar gerentes: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Listar apenas operacionais
    public List<Funcionario> listarOperacionais() {
        try {
            return funcionarioDAO.listarOperacionais();
        } catch (Exception e) {
            System.err.println("Erro ao listar operacionais: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Validar login
    public Funcionario validarLogin(String login, String senha) {
        try {
            return funcionarioDAO.autenticar(login, senha);
        } catch (Exception e) {
            System.err.println("Erro ao validar login: " + e.getMessage());
            return null;
        }
    }

    // Verificar se funcionário existe
    public boolean funcionarioExiste(int idFuncionario) {
        return buscarFuncionarioPorId(idFuncionario) != null;
    }

    // Obter quantidade de funcionários
    public int getTotalFuncionarios() {
        try {
            return funcionarioDAO.tamanho();
        } catch (Exception e) {
            System.err.println("Erro ao obter total de funcionários: " + e.getMessage());
            return 0;
        }
    }
}
