package lotto;

import lotto.Validator.WinningNumbersInputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumbersValidateTest {
    private static String ERROR_HEADER = "[ERROR] ";
    private final WinningNumbersInputValidator winningNumbersInputValidator = new WinningNumbersInputValidator();

    /**
     * 당첨 번호 관련 예외
     **/

    @Test
    @DisplayName("당첨 번호가 1-45를 초과하는 경우")
    void WinningNumbersRangeOverException() {
        assertThatThrownBy(() -> winningNumbersInputValidator.validate("1,2,3,5,19,46"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_HEADER + "번호는 1-45의 범위여야 합니다."); // 예외 메시지 검증
    }

    @Test
    @DisplayName("당첨 번호가 1-45 미만인 수가 존재하는 경우")
    void WinningNumbersRangeUnderException() {
        assertThatThrownBy(() -> winningNumbersInputValidator.validate("0,1,3,4,5,6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_HEADER + "번호는 1-45의 범위여야 합니다."); // 예외 메시지 검증
    }

    @Test
    @DisplayName("당첨 번호가 서로 중복되는 경우")
    void WinningNumbersDuplicateException() {
        assertThatThrownBy(() -> winningNumbersInputValidator.validate("1,2,3,3,5,6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_HEADER + "당첨 번호는 서로 중복되지 않아야 합니다."); // 예외 메시지 검증
    }
}
