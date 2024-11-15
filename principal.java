
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import javax.swing.*;

public class principal {

    private static final int TEMP_MIN = 28;
    private static final int TEMP_MAX = 45;
    private static final int INTERVALO_TEMPO = 1000; // 1 segundo representa 3 dias

    private JFrame frame;
    private GaugePanel gaugePanel;
    private JLabel temperaturaLabel, tituloLabel;
    private Queue<Dados> historicoTemperatura;
    private Random random;
    private Timer timer;
    private int diaAtual;

    public principal() {
        historicoTemperatura = new LinkedList<>();
        random = new Random();
        diaAtual = 1;
        iniciarInterfaceGrafica();
        iniciarSimulacao();
    }

    private void iniciarInterfaceGrafica() {

        frame = new JFrame("Simulação de Temperatura - RJ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setResizable(false);

        // Título
        tituloLabel = new JLabel("Variação de Temperatura - Janeiro", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 14));
        tituloLabel.setForeground(new Color(70, 130, 180));

        // Gauge personalizado
        gaugePanel = new GaugePanel(TEMP_MIN, TEMP_MAX);

        // Label de Temperatura
        temperaturaLabel = new JLabel("Temperatura Atual: " + TEMP_MIN + "°C", SwingConstants.CENTER);
        temperaturaLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout(5, 5));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelPrincipal.add(tituloLabel, BorderLayout.NORTH);
        painelPrincipal.add(gaugePanel, BorderLayout.CENTER);
        painelPrincipal.add(temperaturaLabel, BorderLayout.SOUTH);

        frame.add(painelPrincipal, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void iniciarSimulacao() {
        timer = new Timer(INTERVALO_TEMPO, e -> atualizarTemperatura());
        timer.start();
    }

    private void atualizarTemperatura() {
        if (diaAtual > 31) {
            timer.stop(); // Parar a simulação após 31 dias
            return;
        }

        int novaTemperatura = TEMP_MIN + random.nextInt(TEMP_MAX - TEMP_MIN + 1);

        Dados temperaturaAtual = new Dados(novaTemperatura);
        historicoTemperatura.offer(temperaturaAtual);

        if (historicoTemperatura.size() > 11) {  // Manter apenas os últimos 11 valores
            historicoTemperatura.poll();
        }

        // Atualizar o painel de gauge e a label com a nova temperatura
        gaugePanel.setValue(novaTemperatura);
        temperaturaLabel.setText("Dia " + diaAtual + " - Temperatura Atual: " + novaTemperatura + "°C");

        System.out.println("Dia " + diaAtual + " - Temperatura: " + novaTemperatura + "°C");
        diaAtual += 3; // Avançar 3 dias
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(principal::new);
    }
}
