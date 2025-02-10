package main;

public interface Loadable<T>  {
        T loadOff();
        void loadOn(T instance);
}
