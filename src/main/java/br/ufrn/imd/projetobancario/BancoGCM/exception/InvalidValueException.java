package br.ufrn.imd.projetobancario.BancoGCM.exception;

public class InvalidValueException extends Exception{
    public InvalidValueException() { }

    public InvalidValueException(String message) {
        super(message);
    }
}
