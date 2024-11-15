
public class pilha {

    Vetor lista = new Vetor();

    public void push(Object objeto) {
        lista.adiciona(objeto);
    }

    public boolean pEmpty() {
        return lista.vazia();
    }

    public Object pop() {
        if (!pEmpty()) {
            Object Ob = lista.pega(lista.Tamanho() - 1);
            lista.remover(lista.Tamanho() - 1);
            return Ob;
        }
        return null;
    }
}
