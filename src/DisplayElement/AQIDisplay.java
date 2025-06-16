package DisplayElement;

import Gui.WeatherORamaView;
import Model.DatosClima;
import Observer.Observer;
import Subject.ElementoVisual;

public class AQIDisplay implements Observer, ElementoVisual {
    private float aqi;
    private String clasificacion;

    private final WeatherORamaView vista;

    public AQIDisplay(DatosClima datosClima, WeatherORamaView vista) {
        this.vista = vista;
        datosClima.registerObserver(this);
    }

    @Override
    public void update(float temperatura, float humedad, float presion, float calidadAire) {
        this.aqi = calidadAire;

        if (aqi <= 50) {
            clasificacion = "Buena";
        } else if (aqi <= 100) {
            clasificacion = "Moderada";
        } else if (aqi <= 150) {
            clasificacion = "Poco saludable";
        } else if (aqi <= 200) {
            clasificacion = "Insalubre";
        } else if (aqi <= 300) {
            clasificacion = "Muy insalubre";
        } else {
            clasificacion = "Peligrosa";
        }

        mostrar();
    }

    @Override
    public void mostrar() {
        String colorHTML = obtenerColorHTML(clasificacion);
        String textoHTML = "<html>🌫️ <b>AQI: " + (int) aqi + "</b> – " +
                "<span style='color:" + colorHTML + ";'>" + clasificacion + "</span></html>";

        vista.setCalidadAire(textoHTML);
    }

    private String obtenerColorHTML(String clasificacion) {
        return switch (clasificacion.toLowerCase()) {
            case "buena" -> "green";
            case "moderada" -> "#ffcc00";       // Amarillo
            case "poco saludable" -> "orange";
            case "insalubre" -> "red";
            case "muy insalubre" -> "purple";
            case "peligrosa" -> "#8B0000";      // Rojo oscuro
            default -> "gray";
        };
    }
}
