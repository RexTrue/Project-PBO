package Geometri.exception;

/**
 * Exception untuk sudut yang tidak valid
 */
public class InvalidAngleException extends ValidationException {
    public InvalidAngleException(double angle) {
        super(String.format("Sudut harus antara 0° dan 360°: %.2f°", angle),
              String.valueOf(angle), "0° to 360°");
    }
    
    public InvalidAngleException(double angle, Throwable cause) {
        super(String.format("Sudut harus antara 0° dan 360°: %.2f°", angle),
              String.valueOf(angle), "0° to 360°", cause);
    }
} 