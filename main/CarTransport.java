package main;

import java.awt.*;
import java.util.Stack;

public abstract class CarTransport extends Truck implements Loadable {
    private Stack<Car> loadedCars = new Stack<Car>();
    private boolean ramp;




    public CarTransport(int nrDoors, double enginePower, Color color, String modelName, int x, int y) {
        super(nrDoors, enginePower, color, modelName, x, y);
        this.ramp = true;
    }

    public void lowerRamp(){
        if (getCurrentSpeed() == 0){
            ramp = false;
        }
        else {
            throw new IllegalArgumentException("Can't lower ramp while moving");
        }

    }

    public void raiseRamp(){
        ramp = true;
    }

    public boolean getRamp(){
        return ramp;
    }


    public void loadOn(Car car) {
        if ((loadedCars.size() < 5) && (getCurrentSpeed() == 0) && relativeDistance(car) <= 100 && !getRamp()){
            loadedCars.push(car);
        }
        else {
            throw new IllegalArgumentException("Car transporter is full");
        }
    }

    public Car loadOff() {
        if (!getRamp()) {
            loadedCars.pop();
        }
        else {
            throw new IllegalArgumentException("Can't unload car while ramp is up");
        }
    }


    @Override
    public void gas(double amount){
        if (getRamp()) {
            super.gas(amount);
        }
        else {
            throw new IllegalArgumentException("Can't move while ramp is down.");
        }
    }



}
