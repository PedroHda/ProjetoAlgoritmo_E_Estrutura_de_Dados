
public class Dados {

    private int numeroAparelhos_x;
    private int consumoEnergia_y;

    public int getConsumoEnergia_y() {
        return consumoEnergia_y;
    }
    public void setConsumoEnergia_y(int consumoEnergia_y) {
        this.consumoEnergia_y = consumoEnergia_y;
    }
    public int getNumeroAparelhos_x() {
        return numeroAparelhos_x;
    }
    public void setNumeroAparelhos_x(int numeroAparelhos_x) {
        this.numeroAparelhos_x = numeroAparelhos_x;
    }


    public String toString() {
        return ("Consumo X " + this.numeroAparelhos_x + " | " + "Consumo Y = " + this.consumoEnergia_y + " ");
    }
}
