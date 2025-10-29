package lotto;

import lotto.Util.ErrorMessage;
import lotto.Util.InputParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputValidateTest {
    private final String ERROR_HEADER = "[ERROR] ";
    private final InputParser parser = new InputParser();
    /*
     본래는 도메인 별로 분리를 하려고 했음
     하지만, 그러면 String->Integer를 하는 책임도 도메인의 책임이 됨
     그래서 Input값을 원하는 타입으로 바꾸는 것은 전부 분리함.
    */

    @Test
    @DisplayName("로또 구입 금액으로 수가 아닌 다른 값이 들어오는 경우")
    void BadPriceException() {
        ErrorMessage errorMessage = ErrorMessage.PRICE_BAD_INPUT;
        String error = ERROR_HEADER + errorMessage.getMessage();

        assertThatThrownBy(() -> parser.parsePrice("2milion"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(error); // 예외 메시지 검증
    }

    @Test
    @DisplayName("당첨 번호를 쉼표로 수를 구분하지 않은 경우")
    void WinningNumbersSplitException() {
        ErrorMessage errorMessage = ErrorMessage.WINNING_NUMBER_BAD_FORMAT;
        String error = ERROR_HEADER + errorMessage.getMessage();

        assertThatThrownBy(() -> parser.parseWinningNumbers("1 2 3 4 5 6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(error); // 예외 메시지 검증
    }

    @Test
    @DisplayName("당첨 번호로 수가 아닌 다른 값이 들어오는 경우")
    void BadWinningNumbersException() {
        ErrorMessage errorMessage = ErrorMessage.WINNING_NUMBER_BAD_FORMAT;
        String error = ERROR_HEADER + errorMessage.getMessage();

        assertThatThrownBy(() -> parser.parseWinningNumbers("1,two,3,four,5,6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(error); // 예외 메시지 검증
    }

    @Test
    @DisplayName("보너스 번호로 수가 아닌 다른 값이 들어오는 경우")
    void BadBonusNumberException() {
        ErrorMessage errorMessage = ErrorMessage.BONUS_NUMBER_BAD_INPUT;
        String error = ERROR_HEADER + errorMessage.getMessage();

        assertThatThrownBy(() -> parser.parseBonusNumber("1,five"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(error); // 예외 메시지 검증
    }
}
