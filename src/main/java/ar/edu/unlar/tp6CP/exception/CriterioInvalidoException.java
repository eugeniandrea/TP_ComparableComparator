package ar.edu.unlar.tp6CP.exception;

public class CriterioInvalidoException extends RuntimeException {
    private final String criterioRecibido;

    public CriterioInvalidoException(String criterioRecibido) {
        this.criterioRecibido = criterioRecibido;
    }

    public String getCriterioRecibido() { return criterioRecibido; }
}