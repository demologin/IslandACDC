package com.javarush.island.khasanov.exception;

public class SimulationException extends RuntimeException {
    public SimulationException() {
        super();
    }

    public SimulationException(String message) {
        super(message);
    }

    public SimulationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SimulationException(Throwable cause) {
        super(cause);
    }
}
