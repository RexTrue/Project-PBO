package Geometri.exception;

/**
 * Utility class untuk exception handling
 */
public class ExceptionHandler {
    
    /**
     * Handle exception dengan logging
     */
    public static void handleException(GeometryException exception) {
        System.err.println("ERROR: " + exception.getDetailedMessage());
        if (exception.getCause() != null) {
            System.err.println("CAUSE: " + exception.getCause().getMessage());
        }
    }
    
    /**
     * Handle exception dengan custom message
     */
    public static void handleException(GeometryException exception, String customMessage) {
        System.err.println(customMessage + ": " + exception.getDetailedMessage());
    }
    
    /**
     * Safe calculation dengan exception handling
     */
    public static double safeCalculation(CalculationFunction function, String operation, String formula) throws CalculationException {
        try {
            return function.calculate();
        } catch (Exception e) {
            throw new CalculationException("Error dalam perhitungan " + operation, operation, formula, e);
        }
    }
    
    @FunctionalInterface
    public interface CalculationFunction {
        double calculate() throws Exception;
    }
} 