package Geometri.exception;

/**
 * Exception untuk validasi input
 */
public class ValidationException extends GeometryException {
    private final String invalidValue;
    private final String expectedFormat;
    
    public ValidationException(String message, String invalidValue, String expectedFormat) {
        super(message, "VAL_001", "Input Validation");
        this.invalidValue = invalidValue;
        this.expectedFormat = expectedFormat;
    }
    
    public ValidationException(String message, String invalidValue, String expectedFormat, Throwable cause) {
        super(message, "VAL_001", "Input Validation", cause);
        this.invalidValue = invalidValue;
        this.expectedFormat = expectedFormat;
    }
    
    public String getInvalidValue() { return invalidValue; }
    public String getExpectedFormat() { return expectedFormat; }
} 