public class Jogo {
    private String nome;
    private Cliente cliente;
    private Empregado empregado;
    private double valor;

    public Jogo(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empregado getEmpregado() {
        return empregado;
    }

    public void setEmpregado(Empregado empregado) {
        this.empregado = empregado;
    }

    public double getValor() {
        return valor;
    }

    public void calcularValor() {
        valor *= 1.1; // Acréscimo de 10%
    }

    public void calcularValor(double taxa) {
        valor *= (1 + (taxa / 100)); // Acréscimo baseado na taxa fornecida
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
