package br.ufrn.imd.projetobancario.BancoGCM.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException() { }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
