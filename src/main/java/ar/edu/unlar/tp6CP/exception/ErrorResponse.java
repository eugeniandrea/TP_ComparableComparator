package ar.edu.unlar.tp6CP.exception;
import java.util.List;

public class ErrorResponse {
    private String error;
    private String criterioRecibido;
    private List<String> criteriosAceptados;

    public ErrorResponse(String error, String criterioRecibido, List<String> criteriosAceptados) {
        this.error = error;
        this.criterioRecibido = criterioRecibido;
        this.criteriosAceptados = criteriosAceptados;
    }

    // Getters
    public String getError() { return error; }
    public String getCriterioRecibido() { return criterioRecibido; }
    public List<String> getCriteriosAceptados() { return criteriosAceptados; }
}
