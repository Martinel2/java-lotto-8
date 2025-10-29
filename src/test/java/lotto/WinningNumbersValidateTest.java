package lotto;

import lotto.Validator.WinningNumbersInputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumbersValidateTest {
    private static String ERROR_HEADER = "[ERROR] ";
    private final WinningNumbersInputValidator validator = new WinningNumbersInputValidator();

    /**
     당첨 번호 관련 예외
     **/
    @Test
    @DisplayName("당첨 번호를 쉼표로 수를 구분하지 않은 경우")
    void WinningNumbersSplitException(){
        assertThatThrownBy(() -> validator.parseWinningNumbers("1 2 3 4 5 6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_HEADER + "당첨 번호는 각각을 쉼표로 구분해야 합니다."); // 예외 메시지 검증
    }

    @Test
    @DisplayName("당첨 번호로 수가 아닌 다른 값이 들어오는 경우")
    void BadWinningNumbersException(){
        assertThatThrownBy(() -> validator.parseWinningNumbers("1,two,3,four,5,6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_HEADER + "당첨 번호로 정수만 입력해야 합니다."); // 예외 메시지 검증
    }

    @Test
    @DisplayName("당첨 번호가 1-45를 초과하는 경우")
    void WinningNumbersRangeOverException(){
        assertThatThrownBy(() -> validator.parseWinningNumbers("1,2,3,5,19,46"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_HEADER + "당첨 번호는 1-45의 범위여야 합니다."); // 예외 메시지 검증
    }
    @Test
    @DisplayName("당첨 번호가 1-45 미만인 수가 존재하는 경우")
    void WinningNumbersRangeUnderException(){
        assertThatThrownBy(() -> validator.parseWinningNumbers("0,1,3,4,5,6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_HEADER + "당첨 번호는 1-45의 범위여야 합니다."); // 예외 메시지 검증
    }

    @Test
    @DisplayName("당첨 번호가 서로 중복되는 경우")
    void WinningNumbersDuplicateException(){
        assertThatThrownBy(() -> validator.parseWinningNumbers("1,2,3,3,5,6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_HEADER + "당첨 번호는 서로 중복되지 않아야 합니다."); // 예외 메시지 검증
    }
}
