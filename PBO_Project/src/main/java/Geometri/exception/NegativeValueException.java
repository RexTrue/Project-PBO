package Geometri.exception;

/**
 * Exception untuk nilai negatif atau nol
 */
public class NegativeValueException extends ValidationException {
    public NegativeValueException(String parameterName, double value) {
        super(String.format("Nilai %s tidak boleh negatif atau nol: %.2f", parameterName, value),
              String.valueOf(value), "positive number");
    }
    
    public NegativeValueException(String parameterName, double value, Throwable cause) {
        super(String.format("Nilai %s tidak boleh negatif atau nol: %.2f", parameterName, value),
              String.valueOf(value), "positive number", cause);
    }
} 