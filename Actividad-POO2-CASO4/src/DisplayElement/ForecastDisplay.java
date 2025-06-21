package DisplayElement;

import Observer.Observer;
import Subject.ElementoVisual;
import Model.DatosClima;

public class ForecastDisplay implements Observer, ElementoVisual {
    private float ultimaPresion = 1013.0f;
    private float presionActual = 1013.0f;

    public ForecastDisplay(DatosClima datosClima) {
        datosClima.registerObserver(this);
    }

    @Override
    public void update(float temperatura, float humedad, float presion, float calidadAire) {
        ultimaPresion = presionActual;
        presionActual = presion;
        mostrar();
    }



    @Override
    public void mostrar() {
        System.out.println("🌤️ Pronóstico:");
        if (presionActual > ultimaPresion) {
            System.out.println("Mejorando el tiempo");
        } else if (presionActual == ultimaPresion) {
            System.out.println("Sin cambios");
        } else {
            System.out.println("Empeorando el tiempo");
        }
        System.out.println("---------------------------\n");
    }
}
