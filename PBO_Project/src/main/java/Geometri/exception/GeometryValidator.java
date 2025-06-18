package Geometri.exception;
public class GeometryValidator {

    public static void validatePositive(String parameterName, double value) throws NegativeValueException {
        if (value <= 0) {
            throw new NegativeValueException(parameterName, value);
        }
    }

    public static void validateAngle(double angle) throws InvalidAngleException {
        if (angle <= 0 || angle > 360) {
            throw new InvalidAngleException(angle);
        }
    }

    public static void validateMultiple(String[] parameterNames, double[] values) throws NegativeValueException {
        for (int i = 0; i < parameterNames.length; i++) {
            validatePositive(parameterNames[i], values[i]);
        }
    }
} 