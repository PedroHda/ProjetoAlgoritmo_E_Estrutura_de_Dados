public class Predição {

    // Método para calcular a regressão linear usando a sua classe de fila
    public static double[] CalcularRegressaoLinear(fila dadosFila, int n) {
        double somaX = 0, somaY = 0, somaXY = 0, somaXQuadrado = 0;

        fila filaTemporaria = new fila(); // Fila temporária para preservar os dados

        for (int i = 0; i < n; i++) {
            Dados dados = (Dados) dadosFila.remover();
            somaX += dados.getNumeroAparelhos_x();
            somaY += dados.getConsumoEnergia_y();
            somaXY += dados.getNumeroAparelhos_x() * dados.getConsumoEnergia_y();
            somaXQuadrado += Math.pow(dados.getNumeroAparelhos_x(), 2);
            filaTemporaria.Insere(dados); // Armazena na fila temporária
        }

        // Devolver os dados à fila original
        while (!filaTemporaria.pEmpty()) {
            dadosFila.Insere(filaTemporaria.remover());
        }

        double a = (n * somaXY - somaX * somaY) / (n * somaXQuadrado - Math.pow(somaX, 2));
        double b = (somaY - a * somaX) / n;

        return new double[] { a, b };
    }

    // Método para prever o consumo de energia
    public static double preverConsumoEnergia(int numeroAparelhos, double a, double b) {
        return a * numeroAparelhos + b;
    }

    // Método para calcular o coeficiente de determinação (R^2)
    public static double calcularCoeficienteDeterminacao(fila dadosFila, double a, double b, int n) {
        double mediaY = 0;
        double somaQuadradosTotais = 0, somaQuadradosResiduais = 0;

        fila filaTemporaria = new fila(); // Fila temporária para preservar os dados

        for (int i = 0; i < n; i++) {
            Dados dados = (Dados) dadosFila.remover();
            mediaY += dados.getConsumoEnergia_y();
            filaTemporaria.Insere(dados); // Armazena na fila temporária
        }
        mediaY /= n;

        for (int i = 0; i < n; i++) {
            Dados dados = (Dados) filaTemporaria.remover();
            double yPrevisto = a * dados.getNumeroAparelhos_x() + b;
            somaQuadradosTotais += Math.pow(dados.getConsumoEnergia_y() - mediaY, 2);
            somaQuadradosResiduais += Math.pow(dados.getConsumoEnergia_y() - yPrevisto, 2);
            dadosFila.Insere(dados); // Retorna os dados para a fila original
        }

        return 1 - (somaQuadradosResiduais / somaQuadradosTotais);
    }
}
