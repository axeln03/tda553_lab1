package main;
import java.awt.*;

public class Workshop<T extends Car> implements Loadable<T> {
    private int maxCapacity;
    String name;
    private Storage<Car> storage;

    public Workshop(int maxCapacity, String name){
        this.maxCapacity = maxCapacity;
        this.name = name;
        this.storage = new Storage<Car>(maxCapacity);
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public String getName() {
        return name;
    }

    public Storage<Car> getStorage() {
        return storage;
    }

    public void loadOn(T car) {
        if (storage.getCurrentSize() < getMaxCapacity()) {
            storage.load(car);
        }
        else {
            throw new RuntimeException("The workshop is full!");
        }
    }

    public T loadOff(){
        return (T) storage.deLoad();
    }

}
