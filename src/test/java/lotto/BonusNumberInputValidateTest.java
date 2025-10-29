package lotto;

import lotto.Domain.Lotto;
import lotto.Validator.BonusNumberInputValidator;
import lotto.Validator.PriceInputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusNumberInputValidateTest {
    private static String ERROR_HEADER = "[ERROR] ";
    private final BonusNumberInputValidator validator = new BonusNumberInputValidator();

    /**
     보너스 번호 관련 예외
     **/
    @Test
    @DisplayName("보너스 번호로 수가 아닌 다른 값이 들어오는 경우")
    void BadBonusNumberException(){
        assertThatThrownBy(() -> validator.parseBonusNumbers("1,five"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_HEADER + "보너스 번호는 하나의 정수만 입력해야 합니다."); // 예외 메시지 검증
    }

    @Test
    @DisplayName("보너스 번호가 1-45를 벗어나는 경우")
    void BonusNumberRangeException() {
        assertThatThrownBy(() -> validator.parseBonusNumber("46"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_HEADER + "보너스 번호는 1-45의 범위여야 합니다."); // 예외 메시지 검증
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복인 경우")
    void BonusNumberDuplicateWithWinningNumberException(){
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6)); //당첨 번호
        int bonusNumber = 6;//보너스 번호 (6이 중복)
        assertThatThrownBy(() -> new WinningLotto(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_HEADER + "보너스 번호는 당첨 번호와 중복될 수 없습니다."); // 예외 메시지 검증
    }
}
