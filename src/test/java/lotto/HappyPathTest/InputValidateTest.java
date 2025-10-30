package lotto.HappyPathTest;

import lotto.Util.InputParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InputValidateTest {
    private final InputParser parser = new InputParser();

    /*
    given, when, then으로 테스트 코드 짜기
    사용자 스토리 작성에서 했던 것 실제로 사용해보기
     */
    @Test
    @DisplayName("정상적인 로또 구입 금액 입력")
    void parsePriceAmountCorrectly() {
        String input = "19000"; //given
        Integer price = parser.parsePrice(input); //when
        assertThat(price)
                .isEqualTo(19000); //then
    }

    @Test
    @DisplayName("정상적인 당첨 번호 입력")
    void parseWinningNumbersCorrectly() {
        String input = "1,2,3,4,5,6"; //given
        List<Integer> result = parser.parseWinningNumbers(input); //when
        assertThat(result)
                .containsExactly(1, 2, 3, 4, 5, 6); //then
    }

    @Test
    @DisplayName("정상적인 보너스 번호 입력")
    void parseBonusNumberCorrectly() {
        String input = "20"; //given
        Integer result = parser.parseBonusNumber(input); //when
        assertThat(result)
                .isEqualTo(20); //then
    }

}
