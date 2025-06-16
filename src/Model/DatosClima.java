package Model;

import Observer.Observer;
import Subject.Sujeto;

import java.util.ArrayList;
import java.util.List;

public class DatosClima implements Sujeto {
    private List<Observer> observers;
    private float temperatura;
    private float humedad;
    private float presion;
    private float calidadAire;

    public DatosClima() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperatura, humedad, presion, calidadAire);
        }
    }

    public void setMediciones(float temperatura, float humedad, float presion, float calidadAire) {
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.presion = presion;
        this.calidadAire = calidadAire;
        notifyObservers();
    }
}
