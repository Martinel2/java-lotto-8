package lotto;

import lotto.Domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputValidateTest {
    private static String ERROR_HEADER = "[ERROR] ";
    private final InputValidator validator = new InputValidator();

    /**
     구입 금액 관련 예외
     **/
    @Test
    @DisplayName("로또 구입 금액이 1000으로 나누어 떨어지지 않는 경우")
    void PriceDivException(){
        assertThatThrownBy(() -> validator.parsePurchaseAmount("19900"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_HEADER + "구입 금액은 1000단위여야 합니다."); // 예외 메시지 검증
    }

    @Test
    @DisplayName("로또 구입 금액으로 수가 아닌 다른 값이 들어오는 경우")
    void BadPriceException() {
        assertThatThrownBy(() -> validator.parsePurchaseAmount("2milion"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_HEADER + "구입 금액은 정수로만 입력해야 합니다."); // 예외 메시지 검증
    }


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
