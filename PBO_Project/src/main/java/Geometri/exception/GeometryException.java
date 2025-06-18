package Geometri.exception;

/**
 * Base exception untuk semua error geometri
 */
public class GeometryException extends Exception {
    protected final String errorCode;
    protected final long timestamp;
    protected final String context;
    
    public GeometryException(String message, String errorCode, String context) {
        super(message);
        this.errorCode = errorCode;
        this.timestamp = System.currentTimeMillis();
        this.context = context;
    }
    
    public GeometryException(String message, String errorCode, String context, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.timestamp = System.currentTimeMillis();
        this.context = context;
    }
    
    public String getDetailedMessage() {
        return String.format("[%s] %s - %s (Context: %s, Time: %d)", 
                           getClass().getSimpleName(), errorCode, getMessage(), context, timestamp);
    }
    
    public String getErrorCode() { return errorCode; }
    public long getTimestamp() { return timestamp; }
    public String getContext() { return context; }
} 