
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import javax.swing.*;

public class principal {

    private static final int TEMP_MIN = 28;
    private static final int TEMP_MAX = 45;
    private static final int INTERVALO_TEMPO = 1000; // 1 segundo

    private JFrame frame;
    private JProgressBar gauge;
    private JLabel temperaturaLabel, tituloLabel;
    private Queue<Dados> historicoTemperatura;
    private Random random;
    private Timer timer;

    public principal() {
        historicoTemperatura = new LinkedList<>();
        random = new Random();
        iniciarInterfaceGrafica();
        iniciarSimulacao();
    }

    private void iniciarInterfaceGrafica() {
        frame = new JFrame("Simulação de Temperatura - RJ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(260, 150);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setResizable(false);

        // Título
        tituloLabel = new JLabel("Variação de Temperatura", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 14));
        tituloLabel.setForeground(new Color(70, 130, 180));

        // Gauge
        gauge = new JProgressBar(TEMP_MIN, TEMP_MAX);
        gauge.setStringPainted(true);
        gauge.setForeground(new Color(255, 69, 0)); // Cor mais quente para temperaturas mais altas

        // Label de Temperatura
        temperaturaLabel = new JLabel("Temperatura Atual: " + TEMP_MIN + "°C", SwingConstants.CENTER);
        temperaturaLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout(5, 5));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelPrincipal.add(tituloLabel, BorderLayout.NORTH);
        painelPrincipal.add(gauge, BorderLayout.CENTER);
        painelPrincipal.add(temperaturaLabel, BorderLayout.SOUTH);

        frame.add(painelPrincipal, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void iniciarSimulacao() {
        timer = new Timer(INTERVALO_TEMPO, e -> atualizarTemperatura());
        timer.start();
    }

    private void atualizarTemperatura() {
        int novaTemperatura = TEMP_MIN + random.nextInt(TEMP_MAX - TEMP_MIN + 1);

        Dados temperaturaAtual = new Dados(novaTemperatura);
        historicoTemperatura.offer(temperaturaAtual);

        if (historicoTemperatura.size() > 60) {  // Manter apenas os últimos 60 segundos
            historicoTemperatura.poll();
        }

        // Atualizar o gauge e a label com a nova temperatura
        gauge.setValue(novaTemperatura);
        gauge.setString(novaTemperatura + "°C");
        temperaturaLabel.setText("Temperatura Atual: " + temperaturaAtual);
        System.out.println("Temperatura Atual: " + temperaturaAtual);

        System.out.println("Histórico de Temperaturas: " + historicoTemperatura);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(principal::new);
        System.err.println("Simulação de Temperatura iniciada...");
    }
}
