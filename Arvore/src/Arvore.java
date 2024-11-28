public class Arvore {
    static String nomeAquivo = "clientes.txt";

    private static class No {
        Cliente reg;
        No esq, dir;

        No(Cliente reg) {
            this.reg = reg;
            this.dir = null;
            this.esq = null;
        }
    }

    private No raiz;

    public Arvore() {
        this.raiz = null;
    }

    public Cliente pesquisa(Cliente reg) {
        return pesquisa(reg, this.raiz);
    }

    private Cliente pesquisa(Cliente reg, No no) {
        if (no == null){
            return null;
        }else if (reg.getCpf() < no.reg.getCpf()){
            return pesquisa(reg, no.esq);
        }else if (reg.getCpf() > no.reg.getCpf()){
            return pesquisa(reg, no.dir);
        }
        else{
            return no.reg;
        }
    }

    public void insere(Cliente cliente) {
        No novo = new No(cliente); // cria um novo Nó
        novo.reg = cliente; // atribui o valor recebido ao item de dados do Nó
        novo.dir = null;
        novo.esq = null;

        if (raiz == null)
            raiz = novo;
        else { // se nao for a raiz
            No atual = raiz;
            No anterior;
            while (true) {
                anterior = atual;
                if (cliente.getNome().compareTo(atual.reg.getNome()) < 0) { // ir para esquerda
                    atual = atual.esq;
                    if (atual == null) {
                        anterior.esq = novo;
                        return;
                    }
                } // fim da condição ir a esquerda
                else { // ir para direita
                    atual = atual.dir;
                    if (atual == null) {
                        anterior.dir = novo;
                        return;
                    }
                } // fim da condição ir a direita
            } // fim do laço while
        } // fim do else não raiz
    }

    public void retira(Cliente reg) {
        this.raiz = retira(reg, this.raiz);
    }

    private No retira(Cliente reg, No p) {
        if (p == null) {
            System.out.println("Erro: Registro já existente");
            return null;
        } else if (reg.compareTo(p.reg) < 0)
            p.esq = retira(reg, p.esq);
        else if (reg.compareTo(p.reg) > 0)
            p.dir = retira(reg, p.dir);
        else {
            if (p.dir == null)
                p = p.esq;
            else if (p.esq == null)
                p = p.dir;
            else
                p.esq = antecessor(p, p.esq);
        }
        return p;
    }

    public No antecessor(No q, No r) {
        if (r.dir != null)
            r.dir = antecessor(q, r.dir);
        else {
            q.reg = r.reg;
            r = r.esq;
        }
        return r;
    }

    public void caminhar() {
        System.out.print("\n Exibindo em ordem: ");
        inOrder(raiz);
        System.out.print("\n Exibindo em pos-ordem: ");
        posOrder(raiz);
        System.out.print("\n Exibindo em pre-ordem: ");
        preOrder(raiz);
    }

    public void inOrder(No atual) {
        if (atual != null) {
            inOrder(atual.esq);
            System.out.print(atual.reg + " ");
            inOrder(atual.dir);
        }
    }

    public void preOrder(No atual) {
        if (atual != null) {
            System.out.print(atual.reg + " ");
            preOrder(atual.esq);
            preOrder(atual.dir);
        }
    }

    public void posOrder(No atual) {
        if (atual != null) {
            posOrder(atual.esq);
            posOrder(atual.dir);
            System.out.print(atual.reg + " ");
        }
    }

}

