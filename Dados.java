
public class Dados {

    private int temperatura;

    public Dados(int temperaturaInicial) {
        this.temperatura = temperaturaInicial;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public String toString() {
        return ("" + this.temperatura + "°C");
    }
}
