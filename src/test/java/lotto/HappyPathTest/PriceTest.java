package lotto.HappyPathTest;

import lotto.Domain.Lottery.PriceAmount;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PriceTest {
    private static PriceAmount priceAmount;

    @BeforeAll
    static void setUpBeforeClass(){ //given
        priceAmount = new PriceAmount(10000);
    }

    @Test
    @DisplayName("구입 금액만큼 로또를 구매하는지 테스트")
    public void testCountLotto() {
        Integer count = priceAmount.countLotto(); //when
        assertThat(count).isEqualTo(10);
    }
}
