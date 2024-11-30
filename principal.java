import java.util.Scanner;

public class principal {
    public static void main(String[] args) {
        fila dadosFila = new fila();
        Scanner scanner = new Scanner(System.in);

        // Solicitar número de dados coletados
        System.out.print("Número de dados coletados: ");
        int n = scanner.nextInt();

        // Solicitar número de aparelhos para prever consumo
        System.out.print("Número de aparelhos que deseja estimar o consumo de energia: ");
        int numeroAparelhos = scanner.nextInt();

        // Entrada dos dados de aparelhos e consumo
        System.out.println("Número de aparelhos e Consumo de Energia:");
        for (int i = 0; i < n; i++) {
            int numeroAparelhosX = scanner.nextInt(); // Número de aparelhos (x)
            int consumoEnergiaY = scanner.nextInt();  // Consumo de energia (y)

            Dados dados = new Dados();
            dados.setNumeroAparelhos_x(numeroAparelhosX);
            dados.setConsumoEnergia_y(consumoEnergiaY);

            dadosFila.Insere(dados);
        }

        // Calcular a regressão linear
        double[] coeficientes = Predição.CalcularRegressaoLinear(dadosFila, n);
        double a = coeficientes[0]; // Coeficiente angular
        double b = coeficientes[1]; // Intercepto

        // Prever o consumo de energia para o número de aparelhos fornecido
        double consumoPrevisto = Predição.preverConsumoEnergia(numeroAparelhos, a, b);

        // Calcular o coeficiente de determinação (R²)
        double r2 = Predição.calcularCoeficienteDeterminacao(dadosFila, a, b, n);

        // Exibir resultados
        System.out.printf("Equação da Reta de Regressão Linear: y = %.2fx + %.2f%n", a, b);
        System.out.printf("Estimativa de Consumo para %d aparelhos: %.2f%n", numeroAparelhos, consumoPrevisto);
        System.out.printf("Coeficiente de Determinação (R²): %.2f%n", r2);

        scanner.close();
    }
}
