
public class Vetor {

    private Object[] dados = new Object[100];
    private int total = 0;
    StringBuilder builder;

    public void adiciona(Object elem) {
        this.dados[this.total] = elem;
        this.total++;
    }

    private boolean posicaoValida(int posicao) {
        return posicao >= 0 && posicao <= total;
    }

    public void adiciona(int posicao, Object elem) {
        if (!posicaoValida(posicao)) {
            throw new IllegalArgumentException("Posicao inválida");
        }
        for (int indice = this.total - 1; indice >= posicao; indice--) {
            dados[indice + 1] = dados[indice];
        }
        dados[posicao] = elem;
        total++;
    }

    public void remover(int posicao) {
        if (!posicaoOcupada(posicao)) {
            throw new IllegalArgumentException("Posicao inválida");
        }
        for (int i = posicao; i < total - 1; i++) {
            dados[i] = dados[i + 1];
        }
        this.total--;
    }

    public int Tamanho() {
        return this.total;
    }

    public Object pegarDados(int posicao) {
        return this.dados[posicao];
    }

    public boolean contem(String dados) {
        this.toString();
        return builder.indexOf(dados) >= 0 ? true : false;
    }

    public boolean vazia() {
        return this.total == 0;
    }

    private boolean posicaoOcupada(int posicao) {
        return (posicao >= 0 && posicao < this.total);
    }

    public Object pega(int posicao) {
        if (!posicaoOcupada(posicao)) {
            throw new IllegalArgumentException("******** Posicao Invalida ********");
        }
        return dados[posicao];
    }

    public String toString() {
        if (this.total == 0) {
            return "[]";
        }
        builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < this.total - 1; i++) {
            builder.append(this.dados[i]);
            builder.append(", ");
        }
        builder.append(this.dados[this.total - 1]);
        builder.append("]");
        return builder.toString();
    }
}
