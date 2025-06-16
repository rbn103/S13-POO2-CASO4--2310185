package Subject;

import Observer.Observer;

public interface Sujeto {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
