package lotto.ExceptionTest;

import lotto.Domain.Lottery.Lotto;
import lotto.Domain.Lottery.WinningLotto;
import lotto.Util.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusNumberValidateExceptionTest {
    private final String ERROR_HEADER = "[ERROR] ";
    private Lotto winningNumbers;

    @BeforeEach
    void setUp() {
        this.winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    }

    /**
     * 보너스 번호 관련 예외
     **/
    @Test
    @DisplayName("보너스 번호가 1-45를 벗어나는 경우")
    void BonusNumberRangeException() {
        int bonusNumber = 46;//보너스 번호

        // 범위 에러 메세지 불러오기
        ErrorMessage expectedError = ErrorMessage.LOTTO_NUMBER_RANGE_ERROR;
        String errorMessage = ERROR_HEADER + expectedError.getMessage();

        assertThatThrownBy(() -> new WinningLotto(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage); // 예외 메시지 검증
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복인 경우")
    void BonusNumberDuplicateWithWinningNumberException() {
        int bonusNumber = 6;//보너스 번호 (6이 중복)

        // 중복 에러 메세지 불러오기
        ErrorMessage expectedError = ErrorMessage.BONUS_NUMBER_DUPLICATE;
        String errorMessage = ERROR_HEADER + expectedError.getMessage();

        assertThatThrownBy(() -> new WinningLotto(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage); // 예외 메시지 검증
    }
}
