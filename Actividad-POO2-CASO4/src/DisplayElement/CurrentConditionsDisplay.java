package DisplayElement;

import Observer.Observer;
import Subject.ElementoVisual;
import Model.DatosClima;

public class CurrentConditionsDisplay implements Observer, ElementoVisual {
    private float temperatura;
    private float humedad;

    public CurrentConditionsDisplay(DatosClima datosClima) {
        datosClima.registerObserver(this);
    }

    @Override
    public void update(float temperatura, float humedad, float presion, float airQuality) {
        this.temperatura = temperatura;
        this.humedad = humedad;
        mostrar();
    }


    @Override
    public void mostrar() {
        System.out.println("🌡️ Condiciones actuales:");
        System.out.println("Temperatura: " + temperatura + "°C");
        System.out.println("Humedad: " + humedad + "%");
        System.out.println("---------------------------\n");
    }
}
