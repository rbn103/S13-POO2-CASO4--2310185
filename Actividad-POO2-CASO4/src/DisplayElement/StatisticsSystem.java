package DisplayElement;
import Observer.Observer;
import Subject.ElementoVisual;
import Model.DatosClima;

public class StatisticsSystem implements Observer, ElementoVisual {
    private float sumaTemperatura = 0;
    private float maxTemperatura = Float.MIN_VALUE;
    private float minTemperatura = Float.MAX_VALUE;
    private int numLecturas = 0;

    public StatisticsSystem(DatosClima datosClima) {
        datosClima.registerObserver(this);
    }

    @Override
    public void update(float temperatura, float humedad, float presion, float calidadAire) {
        sumaTemperatura += temperatura;
        numLecturas++;

        if (temperatura > maxTemperatura) maxTemperatura = temperatura;
        if (temperatura < minTemperatura) minTemperatura = temperatura;

        mostrar();
    }


    @Override
    public void mostrar() {
        float promedio = sumaTemperatura / numLecturas;
        System.out.println("Estadísticas de temperatura:");
        System.out.println("Promedio: " + promedio + "°C");
        System.out.println("Máxima: " + maxTemperatura + "°C");
        System.out.println("Mínima: " + minTemperatura + "°C");
        System.out.println("---------------------------\n");
    }
}
