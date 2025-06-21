package Observer;

@FunctionalInterface
public interface Observer {
    void update(float temperature, float humidity, float pressure, float airQuality);
}
