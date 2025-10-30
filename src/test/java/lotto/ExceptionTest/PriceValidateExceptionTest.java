package lotto.ExceptionTest;

import lotto.Domain.Lottery.PriceAmount;
import lotto.Util.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PriceValidateExceptionTest {
    private final String ERROR_HEADER = "[ERROR] ";

    /**
     * 구입 금액 관련 예외
     **/
    @Test
    @DisplayName("로또 구입 금액이 1000으로 나누어 떨어지지 않는 경우")
    void PriceDivException() {
        ErrorMessage expectedError = ErrorMessage.PRICE_NOT_DIV_THOUSAND;
        String errorMessage = ERROR_HEADER + expectedError.getMessage();

        assertThatThrownBy(() -> new PriceAmount(19900))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage); // 예외 메시지 검증
    }

    @Test
    @DisplayName("로또 구입 금액이 0인 경우")
    void PriceZeroException() {
        ErrorMessage expectedError = ErrorMessage.PRICE_BAD_INTEGER;
        String errorMessage = ERROR_HEADER + expectedError.getMessage();

        assertThatThrownBy(() -> new PriceAmount(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage); // 예외 메시지 검증
    }

    @Test
    @DisplayName("로또 구입 금액이 음수인 경우")
    void PriceNegativeException() {
        ErrorMessage expectedError = ErrorMessage.PRICE_BAD_INTEGER;
        String errorMessage = ERROR_HEADER + expectedError.getMessage();

        assertThatThrownBy(() -> new PriceAmount(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage); // 예외 메시지 검증
    }
}
