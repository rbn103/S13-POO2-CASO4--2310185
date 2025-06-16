package Gui;

import javax.swing.*;
import java.awt.*;

public class WeatherORamaView extends JFrame {

    private final static int MAIN_FRAME_WIDTH = 1080;
    private final static int MAIN_FRAME_HEIGHT = 600;
    private final static int MAIN_FRAME_X = 100;
    private final static int MAIN_FRAME_Y = 100;

    // Componentes
    JLabel lbl_Controles = new JLabel("Controles");
    JLabel lbl_Temperatura = new JLabel("Temperatura (°C): ");
    JLabel lbl_Humedad = new JLabel("Humedad (%): ");
    JLabel lbl_Presion = new JLabel("Presión (hPa): ");
    JLabel lbl_CalidadAire = new JLabel("Calidad Aire (AQI): ");
    JSpinner sp_temperatura, sp_humedad, sp_presion, sp_calidadAire;
    JButton btn_ActualizarDatos = new JButton("Actualizar Datos");

    JLabel lbl_Visualizaciones = new JLabel("Visualizaciones ");
    JLabel lbl_V_Temperatura = new JLabel("Temperatura: ");
    JLabel lbl_V_Humedad = new JLabel("Humedad: ");
    JLabel lbl_V_ResultadoEstadisticas = new JLabel();
    JLabel lbl_V_ResultadoPronostico = new JLabel();
    JLabel lbl_V_ResultadoCalidadAire = new JLabel();

    JLabel lbl_SistemaAlertas = new JLabel("Sistema de Alertas");
    JLabel lbl_ResultadoSistemaAlertas = new JLabel();

    JButton btn_CondicionesActuales = new JButton("Agregar Condiciones Actuales");
    JButton btn_Estadisticas = new JButton("Agregar Estadísticas");
    JButton btn_Pronostico = new JButton("Agregar Pronóstico");
    JButton btn_CalidadAire = new JButton("Agregar Calidad Aire");

    public WeatherORamaView() {
        setTitle("Estación Meteorológica - Observer Pattern");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(MAIN_FRAME_X, MAIN_FRAME_Y, MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT));

        JPanel ControlsPanel = new JPanel(null);
        ControlsPanel.setPreferredSize(new Dimension(MAIN_FRAME_WIDTH, 160));
        make_WeatherControls(ControlsPanel);
        add(ControlsPanel, BorderLayout.NORTH);

        JPanel VisualizationsPanel = new JPanel(null);
        VisualizationsPanel.setPreferredSize(new Dimension(MAIN_FRAME_WIDTH, 600));
        make_Visualizations(VisualizationsPanel);
        JScrollPane scrollPane = new JScrollPane(VisualizationsPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel AlertSystemPanel = new JPanel(null);
        AlertSystemPanel.setPreferredSize(new Dimension(MAIN_FRAME_WIDTH, 120));
        make_AlertSystem(AlertSystemPanel);
        add(AlertSystemPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void make_WeatherControls(JPanel controlsPanel) {
        lbl_Controles.setBounds(10, 10, 100, 20);
        controlsPanel.add(lbl_Controles);

        lbl_Temperatura.setBounds(10, 40, 130, 20);
        controlsPanel.add(lbl_Temperatura);
        sp_temperatura = new JSpinner(new SpinnerNumberModel(36, -50, 100, 1));
        sp_temperatura.setBounds(150, 40, 100, 20);
        controlsPanel.add(sp_temperatura);

        lbl_Humedad.setBounds(10, 70, 130, 20);
        controlsPanel.add(lbl_Humedad);
        sp_humedad = new JSpinner(new SpinnerNumberModel(95, 0, 100, 1));
        sp_humedad.setBounds(150, 70, 100, 20);
        controlsPanel.add(sp_humedad);

        lbl_Presion.setBounds(10, 100, 130, 20);
        controlsPanel.add(lbl_Presion);
        sp_presion = new JSpinner(new SpinnerNumberModel(1013.0, 800.0, 1200.0, 0.1));
        sp_presion.setBounds(150, 100, 100, 20);
        controlsPanel.add(sp_presion);

        lbl_CalidadAire.setBounds(10, 130, 150, 20);
        controlsPanel.add(lbl_CalidadAire);
        sp_calidadAire = new JSpinner(new SpinnerNumberModel(200, 0, 500, 1));
        sp_calidadAire.setBounds(150, 130, 100, 20);
        controlsPanel.add(sp_calidadAire);

        btn_ActualizarDatos.setBounds(300, 80, 180, 30);
        controlsPanel.add(btn_ActualizarDatos);
    }

    private void make_Visualizations(JPanel visualizationsPanel) {
        visualizationsPanel.setLayout(null);

        lbl_Visualizaciones.setBounds(10, 10, 200, 20);
        visualizationsPanel.add(lbl_Visualizaciones);

        lbl_V_Temperatura.setBounds(10, 40, 400, 20);
        visualizationsPanel.add(lbl_V_Temperatura);

        lbl_V_Humedad.setBounds(10, 70, 400, 20);
        visualizationsPanel.add(lbl_V_Humedad);

        lbl_V_ResultadoEstadisticas.setBounds(10, 100, 400, 20);
        visualizationsPanel.add(lbl_V_ResultadoEstadisticas);

        lbl_V_ResultadoPronostico.setBounds(10, 130, 400, 20);
        visualizationsPanel.add(lbl_V_ResultadoPronostico);

        lbl_V_ResultadoCalidadAire.setBounds(10, 160, 400, 20);
        visualizationsPanel.add(lbl_V_ResultadoCalidadAire);

        // Botones a la derecha
        btn_CondicionesActuales.setBounds(800, 40, 250, 30);
        visualizationsPanel.add(btn_CondicionesActuales);

        btn_Estadisticas.setBounds(800, 80, 250, 30);
        visualizationsPanel.add(btn_Estadisticas);

        btn_Pronostico.setBounds(800, 120, 250, 30);
        visualizationsPanel.add(btn_Pronostico);

        btn_CalidadAire.setBounds(800, 160, 250, 30);
        visualizationsPanel.add(btn_CalidadAire);
    }

    private void make_AlertSystem(JPanel alertSystemPanel) {
        lbl_SistemaAlertas.setBounds(10, 10, 200, 20);
        alertSystemPanel.add(lbl_SistemaAlertas);

        lbl_ResultadoSistemaAlertas.setForeground(Color.RED);
        lbl_ResultadoSistemaAlertas.setVerticalAlignment(SwingConstants.TOP);
        lbl_ResultadoSistemaAlertas.setBounds(10, 40, 1000, 60);
        lbl_ResultadoSistemaAlertas.setText("No hay alertas aún.");
        lbl_ResultadoSistemaAlertas.setFont(new Font("Arial", Font.PLAIN, 14));
        alertSystemPanel.add(lbl_ResultadoSistemaAlertas);
    }

    public JButton getBtnActualizarDatos() { return btn_ActualizarDatos; }
    public JButton getBtnCondicionesActuales() { return btn_CondicionesActuales; }
    public JButton getBtnEstadisticas() { return btn_Estadisticas; }
    public JButton getBtnPronostico() { return btn_Pronostico; }
    public JButton getBtnCalidadAire() { return btn_CalidadAire; }

    public int getTemperatura() { return (int) sp_temperatura.getValue(); }
    public int getHumedad() { return (int) sp_humedad.getValue(); }
    public double getPresion() { return (double) sp_presion.getValue(); }
    public int getCalidadAire() { return (int) sp_calidadAire.getValue(); }

    // Métodos para actualizar etiquetas desde los observers
    public void setCondicionesActuales(Object temp, Object hum) {
        lbl_V_Temperatura.setText("Temperatura: " + temp + " °C");
        lbl_V_Humedad.setText("Humedad: " + hum + " %");
    }

    public void setEstadisticas(float prom, float max, float min) {
        lbl_V_ResultadoEstadisticas.setText("Prom: " + prom + " °C, Máx: " + max + " °C, Mín: " + min + " °C");
    }

    public void setPronostico(String texto) {
        lbl_V_ResultadoPronostico.setText("Pronóstico: " + texto);
    }

    public void setCalidadAire(String texto) {
        lbl_V_ResultadoCalidadAire.setText("Calidad del aire: " + texto);
    }

    public void setAlertText(String texto) {
        lbl_ResultadoSistemaAlertas.setText(texto);
    }
}
