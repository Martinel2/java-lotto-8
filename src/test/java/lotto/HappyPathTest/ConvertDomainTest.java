package lotto.HappyPathTest;

import lotto.Domain.Lottery.Lotto;
import lotto.Domain.Lottery.PriceAmount;
import lotto.Domain.Lottery.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ConvertDomainTest {
    /*
    Domain이 정상적으로 생성되는지 확인하는 테스트 클래스

    given, when, then으로 테스트 코드 짜기
    사용자 스토리 작성에서 했던 것 실제로 사용해보기
     */

    @Test
    @DisplayName("정상적인 로또 구입 금액 테스트")
    void parsePriceAmountCorrectly() {
        Integer price = 19000; //given
        PriceAmount priceAmount = new PriceAmount(price); // when
        assertThat(priceAmount)
                .isInstanceOf(PriceAmount.class); //then
    }

    @Test
    @DisplayName("정상적인 로또 번호 테스트")
    void parseLottoNumbersCorrectly() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6); //given
        Lotto lotto = new Lotto(numbers); //when
        assertThat(lotto)
                .isInstanceOf(Lotto.class); //then
    }

    @Test
    @DisplayName("정상적인 당첨 번호 테스트")
    void parseWinningNumbersCorrectly() {
        //given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Integer bonusNumber = 25;
        // when
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);
        //then
        assertThat(winningLotto)
                .isInstanceOf(WinningLotto.class);
    }

}
