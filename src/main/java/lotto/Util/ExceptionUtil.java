package lotto.Util;

public final class ExceptionUtil {

    private static final String ERROR_HEADER = "[ERROR] ";

    private ExceptionUtil() {
    }

    public static void throwInvalidValue(ErrorMessage error) {
        throw new IllegalArgumentException(ERROR_HEADER + error.getMessage());
    }
}