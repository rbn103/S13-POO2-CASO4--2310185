package DisplayElement;

import Observer.Observer;
import Subject.ElementoVisual;
import Model.DatosClima;

public class ThirdPartyDisplay implements Observer, ElementoVisual {
    private float temperatura;
    private float humedad;
    private float presion;
    private float calidadAire;

    public ThirdPartyDisplay(DatosClima datosClima) {
        datosClima.registerObserver(this);
    }

    @Override
    public void update(float temperatura, float humedad, float presion, float calidadAire) {
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.presion = presion;
        this.calidadAire = calidadAire;
        mostrar();
    }

    @Override
    public void mostrar() {
        System.out.println("📡 Visualización de Terceros:");
        System.out.println("Temperatura: " + temperatura + "°C");
        System.out.println("Humedad: " + humedad + "%");
        System.out.println("Presión: " + presion + " hPa");
        System.out.println("Calidad del Aire (AQI): " + calidadAire);
        System.out.println("---------------------------\n");
    }
}
