package Geometri.exception;

/**
 * Exception untuk error dalam perhitungan
 */
public class CalculationException extends GeometryException {
    private final String operation;
    private final String formula;
    
    public CalculationException(String message, String operation, String formula) {
        super(message, "CALC_001", "Calculation Error");
        this.operation = operation;
        this.formula = formula;
    }
    
    public CalculationException(String message, String operation, String formula, Throwable cause) {
        super(message, "CALC_001", "Calculation Error", cause);
        this.operation = operation;
        this.formula = formula;
    }
    
    public String getOperation() { return operation; }
    public String getFormula() { return formula; }
} 