package app.entities.interfaces;

import app.entities.exceptions.WrongInputException;

public interface Containable<T> {
    T content() throws WrongInputException;
}
