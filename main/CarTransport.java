package main;

import java.awt.*;
import java.util.Stack;

public class CarTransport extends Truck implements Loadable<Car> {
    private Storage<Car> storage = new Storage<Car>(5);
    private boolean ramp;


    public CarTransport(int nrDoors, double enginePower, Color color, String modelName, int x, int y) {
        super(nrDoors, enginePower, color, modelName, x, y);
        this.ramp = true;
    }

    public void lowerRamp() {
        if (getCurrentSpeed() == 0) {
            ramp = false;
        } else {
            throw new IllegalArgumentException("Can't lower ramp while moving");
        }

    }

    public void raiseRamp() {
        ramp = true;
    }

    public boolean getRamp() {
        return ramp;
    }

    public Stack<Car> getStorage() {
        return storage.getCurrentStorage();
    }


    public <T extends Car> void loadOn(T car) {
        if ((storage.getCurrentSize() < storage.getMaxSize()) && (getCurrentSpeed() == 0) && relativeDistance(car) <= 100 && !getRamp()) {
            storage.load(car);

            car.setX(this.getX());
            car.setY(this.getY());

            setLoaded(true);

        } else {
            throw new IllegalArgumentException("Car transporter is full");
        }
    }

    public <T extends Car> T loadOff() {
        if (!getRamp()) {
            T removed = storage.deLoad();
            removed.setX(this.getX() - 5);
            removed.setY(this.getY() - 5);
            removed.setLoaded(false);

            return removed;
        } else {
            throw new IllegalArgumentException("Can't unload car while ramp is up");
        }
    }


    @Override
    public void gas(double amount) {
        if (getRamp()) {
            super.gas(amount);
        } else {
            throw new IllegalArgumentException("Can't move while ramp is down.");
        }
    }


}
