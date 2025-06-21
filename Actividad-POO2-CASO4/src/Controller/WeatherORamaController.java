package Controller;

import Gui.WeatherORamaView;
import Model.DatosClima;
import Observer.Observer;
import DisplayElement.AlertSystem;
import DisplayElement.CurrentConditionsDisplay;
import DisplayElement.StatisticsSystem;
import DisplayElement.ForecastDisplay;
import DisplayElement.AQIDisplay;

public class WeatherORamaController {
     DatosClima datosClima;
     WeatherORamaView vista;
     AlertSystem alertSystem;

    private float sumaTemperatura = 0;
    private float maxTemperatura = Float.MIN_VALUE;
    private float minTemperatura = Float.MAX_VALUE;
    private int numLecturas = 0;

    private float ultimaPresion = 1013.0f;
    private float presionActual = 1013.0f;

    public WeatherORamaController() {
        datosClima = new DatosClima();
        vista = new WeatherORamaView();
        alertSystem = new AlertSystem(datosClima);
        configurarListeners();
    }
        private void configurarListeners () {
            vista.getBtnCondicionesActuales().addActionListener(e -> {
                new CurrentConditionsDisplay(datosClima);
                vista.seccionCurrentConditions();
            });

            vista.getBtnEstadisticas().addActionListener(e -> {
                new StatisticsSystem(datosClima);
                vista.seccionStatistics();
            });

            vista.getBtnPronostico().addActionListener(e -> {
                new ForecastDisplay(datosClima);
                vista.seccionForecast();
            });

            vista.getBtnCalidadAire().addActionListener(e -> {
                new AQIDisplay(datosClima, vista);
                vista.seccionAQI();
            });

            // Observador anónimo
            datosClima.registerObserver(new Observer() {
                @Override
                public void update(float temp, float hum, float pres, float aqi) {
                    // Condiciones actuales
                    vista.setCondicionesActuales(temp, hum);

                    // Estadísticas
                    sumaTemperatura += temp;
                    numLecturas++;
                    if (temp > maxTemperatura) maxTemperatura = temp;
                    if (temp < minTemperatura) minTemperatura = temp;
                    float promedio = sumaTemperatura / numLecturas;
                    vista.setEstadisticas(promedio, maxTemperatura, minTemperatura);

                    // Pronóstico basado en presión
                    ultimaPresion = presionActual;
                    presionActual = pres;
                    String pronostico = "Sin cambios";
                    if (presionActual > ultimaPresion) pronostico = "Mejorando el tiempo";
                    else if (presionActual < ultimaPresion) pronostico = "Empeorando el tiempo";
                    vista.setPronostico(pronostico);

                    // Calidad del aire
                    String calidad;
                    if (aqi <= 50) calidad = "Buena";
                    else if (aqi <= 100) calidad = "Moderada";
                    else if (aqi <= 150) calidad = "Poco saludable";
                    else if (aqi <= 200) calidad = "Insalubre";
                    else if (aqi <= 300) calidad = "Muy insalubre";
                    else calidad = "Peligrosa";
                    vista.setCalidadAire("AQI: " + (int) aqi + " (" + calidad + ")");

                    // Sistema de alertas
                    vista.setAlertText("<html>" + alertSystem.getAlertSummary().replace("\n", "<br>") + "</html>");
                }
            });

            // Acción para botón de actualizar datos
            vista.getBtnActualizarDatos().addActionListener(e -> actualizarDatos());
        }

        private void actualizarDatos () {
            float temp = vista.getTemperatura();
            float hum = vista.getHumedad();
            float pres = (float) vista.getPresion();
            float aqi = vista.getCalidadAire();

            datosClima.setMediciones(temp, hum, pres, aqi);
        }
    }

