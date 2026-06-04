package funcionario;

import java.io.Serializable;
import java.util.Date;
import usuario.Usuario;

public class Funcionario extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Date dataAdmissao;
    private boolean ehGerente;

    // Construtor padrão
    public Funcionario() {
        super();
    }

    // Construtor completo
    public Funcionario(int idUsuario, String nome, String cpf, String email, 
                       String senha, Date dataAdmissao, boolean ehGerente) {
        super(idUsuario, nome, cpf, email, senha);
        this.dataAdmissao = dataAdmissao;
        this.ehGerente = ehGerente;
    }

    // Construtor alternativo (sem ID)
    public Funcionario(String nome, String cpf, String email, String senha, 
                       Date dataAdmissao, boolean ehGerente) {
        super(nome, cpf, email, senha);
        this.dataAdmissao = dataAdmissao;
        this.ehGerente = ehGerente;
    }

    // Getters e Setters
    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public boolean isEhGerente() {
        return ehGerente;
    }

    public void setEhGerente(boolean ehGerente) {
        this.ehGerente = ehGerente;
    }

    // Métodos da classe
    public void verFuncionario() {
        System.out.println("=== Dados do Funcionário ===");
        System.out.println("ID: " + getIdUsuario());
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCpf());
        System.out.println("Email: " + getEmail());
        System.out.println("Data de Admissão: " + dataAdmissao);
        System.out.println("É Gerente: " + (ehGerente ? "Sim" : "Não"));
    }

    public void editarFuncionario(String nome, String cpf, String email, String senha, 
                                  Date dataAdmissao, boolean ehGerente) {
        setNome(nome);
        setCpf(cpf);
        setEmail(email);
        setSenha(senha);
        this.dataAdmissao = dataAdmissao;
        this.ehGerente = ehGerente;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "idFuncionario=" + getIdUsuario() +
                ", nome='" + getNome() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", dataAdmissao=" + dataAdmissao +
                ", ehGerente=" + ehGerente +
                '}';
    }
}