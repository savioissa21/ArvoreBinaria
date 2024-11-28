public class Cliente implements Comparable<Cliente>{

    private String nome;
    private long cpf;
    private String email;
    private String telefone;
    private String endereço;


    public Cliente(String nome, long cpf, String email, String telefone, String endereço) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereço = endereço;
    }

    public Cliente() {
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public long getCpf() {
        return cpf;
    }
    public void setCpf(long cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEndereço() {
        return endereço;
    }
    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    @Override
    public String toString() {
        return "Cliente " + "\nnome :" + nome + "\ncpf :" + cpf  +  "\nemail :" + email + "\ntelefone :"
                + telefone + "\nendereço :" + endereço;
    }

    @Override
    public int compareTo(Cliente other) {
        return nome.compareTo(other.getNome());
    }

   
}

