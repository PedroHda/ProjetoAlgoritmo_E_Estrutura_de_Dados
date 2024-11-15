
public class fila {

    Vetor lista = new Vetor();

    public void Insere(Object objeto) {
        lista.adiciona(objeto);
    }

    public Object remover() {
        if (!pEmpty()) {
            Object objeto = lista.pega(0);
            lista.remover(0);
            return objeto;
        }
        return null;
    }

    public boolean pEmpty() {
        return lista.vazia();
    }

    public Object mostrar() {
        if (!pEmpty()) {
            return lista.pega(0);
        }
        return null;
    }
}
