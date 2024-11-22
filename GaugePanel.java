import java.awt.*;
import javax.swing.*;

class GaugePanel extends JPanel {

    private int value = 28; // Valor inicial
    private final int min;
    private final int max;

    public GaugePanel(int min, int max) {
        this.min = min;
        this.max = max;
        setPreferredSize(new Dimension(300, 150));
    }

    public void setValue(int value) {
        this.value = Math.min(Math.max(value, min), max); // Garante que o valor fique dentro dos limites
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height - 60; // Ajustar a posição vertical do centro do círculo
        int radius = Math.min(width, height) - 100; // Ajustar o raio para manter o círculo proporcional

        // Desenhar os setores coloridos do medidor
        g2d.setStroke(new BasicStroke(10));
        int startAngle = 123;
        int arcAngle = 190 / 3;

        // Setor verde (temperaturas baixas)
        g2d.setColor(Color.GREEN);
        g2d.drawArc(centerX - radius / 2, centerY - radius / 2, radius, radius, startAngle, arcAngle);

        // Setor amarelo (temperaturas médias)
        g2d.setColor(Color.YELLOW);
        g2d.drawArc(centerX - radius / 2, centerY - radius / 2, radius, radius, startAngle - arcAngle, arcAngle);

        // Setor vermelho (temperaturas altas)
        g2d.setColor(Color.RED);
        g2d.drawArc(centerX - radius / 2, centerY - radius / 2, radius, radius, startAngle - 2 * arcAngle, arcAngle);

        // Desenhar a agulha
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        double angle = Math.toRadians(180 - (value - min) * 190.0 / (max - min)); // Ajustar para o arco completo
        int needleX = (int) (centerX + (radius / 2 - 20) * Math.cos(angle));
        int needleY = (int) (centerY - (radius / 2 - 20) * Math.sin(angle));
        g2d.drawLine(centerX, centerY, needleX, needleY);

        // Desenhar o centro do medidor
        g2d.fillOval(centerX - 5, centerY - 5, 10, 10);
    }
}
