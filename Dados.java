
public class Dados {

    private int temperatura;
    private int dia;

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }
    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public String toString() {
        return ("Dia " + this.dia + " - " + "Temperatura = " + this.temperatura + "°C");
    }
}
