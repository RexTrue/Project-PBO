package Geometri.exception;

// import java.util.concurrent.TimeoutException;

abstract class BaseGeometryException extends Exception {
    protected final String errorCode;
    protected final long timestamp;
    protected final String context;
    
    public BaseGeometryException(String message, String errorCode, String context) {
        super(message);
        this.errorCode = errorCode;
        this.timestamp = System.currentTimeMillis();
        this.context = context;
    }
    
    public BaseGeometryException(String message, String errorCode, String context, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.timestamp = System.currentTimeMillis();
        this.context = context;
    }
    
    public abstract String getExceptionType();
    
    public final String getDetailedMessage() {
        return String.format("[%s] %s - %s (Context: %s, Time: %d)", 
                           getExceptionType(), errorCode, getMessage(), context, timestamp);
    }
    
    // Getters - ENCAPSULATION
    public String getErrorCode() { return errorCode; }
    public long getTimestamp() { return timestamp; }
    public String getContext() { return context; }
}

class GeometryValidationException extends BaseGeometryException {
    private final String invalidValue;
    private final String expectedFormat;
    
    public GeometryValidationException(String message, String invalidValue, String expectedFormat) {
        super(message, "VAL_001", "Input Validation");
        this.invalidValue = invalidValue;
        this.expectedFormat = expectedFormat;
    }
    
    public GeometryValidationException(String message, String invalidValue, String expectedFormat, Throwable cause) {
        super(message, "VAL_001", "Input Validation", cause);
        this.invalidValue = invalidValue;
        this.expectedFormat = expectedFormat;
    }
    
    @Override
    public String getExceptionType() {
        return "VALIDATION_EXCEPTION";
    }
    
    public String getValidationDetails() {
        return String.format("Invalid: %s, Expected: %s", invalidValue, expectedFormat);
    }
    
    public String getInvalidValue() { return invalidValue; }
    public String getExpectedFormat() { return expectedFormat; }
}

class NegativeValueException extends GeometryValidationException {
    public NegativeValueException(String parameterName, double value) {
        super(String.format("Nilai %s tidak boleh negatif atau nol: %.2f", parameterName, value),
              String.valueOf(value), "positive number");
    }
    
    public NegativeValueException(String parameterName, double value, Throwable cause) {
        super(String.format("Nilai %s tidak boleh negatif atau nol: %.2f", parameterName, value),
              String.valueOf(value), "positive number", cause);
    }
    
    @Override
    public String getExceptionType() {
        return "NEGATIVE_VALUE_EXCEPTION";
    }
}
class InvalidAngleException extends GeometryValidationException {
    public InvalidAngleException(double angle) {
        super(String.format("Sudut harus antara 0° dan 360°: %.2f°", angle),
              String.valueOf(angle), "0° to 360°");
    }
    
    public InvalidAngleException(double angle, Throwable cause) {
        super(String.format("Sudut harus antara 0° dan 360°: %.2f°", angle),
              String.valueOf(angle), "0° to 360°", cause);
    }
    
    @Override
    public String getExceptionType() {
        return "INVALID_ANGLE_EXCEPTION";
    }
}
class GeometryCalculationException extends BaseGeometryException {
    private final String operation;
    private final String formula;
    
    public GeometryCalculationException(String message, String operation, String formula) {
        super(message, "CALC_001", "Calculation Error");
        this.operation = operation;
        this.formula = formula;
    }
    
    public GeometryCalculationException(String message, String operation, String formula, Throwable cause) {
        super(message, "CALC_001", "Calculation Error", cause);
        this.operation = operation;
        this.formula = formula;
    }
    
    @Override
    public String getExceptionType() {
        return "CALCULATION_EXCEPTION";
    }
    
    public String getOperation() { return operation; }
    public String getFormula() { return formula; }
}
class CalculationOverflowException extends GeometryCalculationException {
    public CalculationOverflowException(String operation, String formula) {
        super("Hasil perhitungan terlalu besar (overflow)", operation, formula);
    }
    
    public CalculationOverflowException(String operation, String formula, Throwable cause) {
        super("Hasil perhitungan terlalu besar (overflow)", operation, formula, cause);
    }
    
    @Override
    public String getExceptionType() {
        return "OVERFLOW_EXCEPTION";
    }
}
class ThreadingException extends BaseGeometryException {
    private final String threadName;
    private final Thread.State threadState;
    
    public ThreadingException(String message, String threadName, Thread.State threadState) {
        super(message, "THREAD_001", "Threading Error");
        this.threadName = threadName;
        this.threadState = threadState;
    }
    
    public ThreadingException(String message, String threadName, Thread.State threadState, Throwable cause) {
        super(message, "THREAD_001", "Threading Error", cause);
        this.threadName = threadName;
        this.threadState = threadState;
    }
    
    @Override
    public String getExceptionType() {
        return "THREADING_EXCEPTION";
    }
    
    public String getThreadName() { return threadName; }
    public Thread.State getThreadState() { return threadState; }
}

class ThreadTimeoutException extends ThreadingException {
    private final long timeout;
    
    public ThreadTimeoutException(String threadName, long timeout) {
        super(String.format("Thread %s timeout setelah %d ms", threadName, timeout),
              threadName, Thread.State.TIMED_WAITING);
        this.timeout = timeout;
    }
    
    public ThreadTimeoutException(String threadName, long timeout, Throwable cause) {
        super(String.format("Thread %s timeout setelah %d ms", threadName, timeout),
              threadName, Thread.State.TIMED_WAITING, cause);
        this.timeout = timeout;
    }
    
    @Override
    public String getExceptionType() {
        return "THREAD_TIMEOUT_EXCEPTION";
    }
    
    public long getTimeout() { return timeout; }
}
interface ExceptionHandlingStrategy {
    boolean handleException(BaseGeometryException exception);
    String getStrategyName();
    int getPriority();
}
class LoggingExceptionStrategy implements ExceptionHandlingStrategy {
    @Override
    public boolean handleException(BaseGeometryException exception) {
        System.err.println("LOGGING: " + exception.getDetailedMessage());
        return true;
    }
    
    @Override
    public String getStrategyName() {
        return "Logging Strategy";
    }
    
    @Override
    public int getPriority() {
        return 1;
    }
}
class RecoveryExceptionStrategy implements ExceptionHandlingStrategy {
    @Override
    public boolean handleException(BaseGeometryException exception) {
        if (exception instanceof NegativeValueException) {
            System.out.println("RECOVERY: Menggunakan nilai default untuk " + 
                             ((NegativeValueException) exception).getInvalidValue());
            return true;
        }
        return false;
    }
    
    @Override
    public String getStrategyName() {
        return "Recovery Strategy";
    }
    
    @Override
    public int getPriority() {
        return 2;
    }
}
class GeometryExceptionFactory {
    public static GeometryValidationException createValidationException(String message, String value, String format) {
        return new GeometryValidationException(message, value, format);
    }
    
    public static GeometryValidationException createValidationException(String message, String value, String format, Throwable cause) {
        return new GeometryValidationException(message, value, format, cause);
    }
    public static NegativeValueException createNegativeValueException(String parameterName, double value) {
        return new NegativeValueException(parameterName, value);
    }
    public static GeometryCalculationException createCalculationException(String message, String operation, String formula) {
        return new GeometryCalculationException(message, operation, formula);
    }
    public static ThreadingException createThreadingException(String message, String threadName, Thread.State state) {
        return new ThreadingException(message, threadName, state);
    }
}
class ExceptionHandlerManager {
    private static ExceptionHandlerManager instance;
    private final java.util.List<ExceptionHandlingStrategy> strategies;
    
    private ExceptionHandlerManager() {
        this.strategies = new java.util.ArrayList<>();
        // Default strategies
        addStrategy(new LoggingExceptionStrategy());
        addStrategy(new RecoveryExceptionStrategy());
    }
    
    public static ExceptionHandlerManager getInstance() {
        if (instance == null) {
            instance = new ExceptionHandlerManager();
        }
        return instance;
    }
    
    public void addStrategy(ExceptionHandlingStrategy strategy) {
        strategies.add(strategy);
        // Sort by priority
        strategies.sort((s1, s2) -> Integer.compare(s2.getPriority(), s1.getPriority()));
    }
    
    public boolean handleException(BaseGeometryException exception) {
        for (ExceptionHandlingStrategy strategy : strategies) {
            if (strategy.handleException(exception)) {
                return true;
            }
        }
        return false;
    }
}
public class ExceptionHandling {
    
    // Constants untuk error codes
    public static final String ERROR_CODE_VALIDATION = "VAL_001";
    public static final String ERROR_CODE_CALCULATION = "CALC_001";
    public static final String ERROR_CODE_THREADING = "THREAD_001";
} 