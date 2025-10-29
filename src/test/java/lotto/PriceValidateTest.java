package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PriceValidateTest {
    private static String ERROR_HEADER = "[ERROR] ";

    /**
     * 구입 금액 관련 예외
     **/
    @Test
    @DisplayName("로또 구입 금액이 1000으로 나누어 떨어지지 않는 경우")
    void PriceDivException() {
        assertThatThrownBy(() -> priceInputValidator.validate("19900"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_HEADER + "구입 금액은 1000단위여야 합니다."); // 예외 메시지 검증
    }
}
