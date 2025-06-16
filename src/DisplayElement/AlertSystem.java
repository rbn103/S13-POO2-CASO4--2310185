package DisplayElement;

import Observer.Observer;
import Subject.ElementoVisual;
import Subject.Sujeto;

import java.util.ArrayList;
import java.util.List;

public class AlertSystem implements Observer, ElementoVisual {
    private float temperature;
    private float humidity;
    private float pressure;
    private float airQuality;
    private boolean alertActive;
    private List<String> triggeredAlerts = new ArrayList<>();

    private final Sujeto weatherData;

    public AlertSystem(Sujeto weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure, float airQuality) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.airQuality = airQuality;
        activateAlarm();
    }



    @Override
    public void mostrar() {
        System.out.println("🚨 Sistema de Alertas:");
        if (alertActive) {
            for (String alerta : triggeredAlerts) {
                System.out.println("⚠️ " + alerta);
            }
        } else {
            System.out.println("✔️ Todo en orden. No hay alertas activas.");
        }
        System.out.println("---------------------------\n");
    }

    private void activateAlarm() {
        List<String> nuevasAlertas = new ArrayList<>();

        if (temperature < 15 || temperature > 35) {
            nuevasAlertas.add("Temperatura fuera de rango: " + temperature + "°C");
        }
        if (humidity < 30 || humidity > 70) {
            nuevasAlertas.add("Humedad fuera de rango: " + humidity + "%");
        }
        if (pressure < 980 || pressure > 1050) {
            nuevasAlertas.add("Presión fuera de rango: " + pressure + " hPa");
        }
        if (airQuality > 100) {
            nuevasAlertas.add("Calidad de aire mala: AQI = " + airQuality);
        }

        triggeredAlerts = nuevasAlertas;
        alertActive = !nuevasAlertas.isEmpty();
        mostrar();
    }

    public boolean isAlertActive() {
        return alertActive;
    }

    public List<String> getTriggeredAlerts() {
        return triggeredAlerts;
    }

    public String getAlertSummary() {
        return "";
    }

}
