package lotto.ViewTest;

import lotto.View.InputView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InputViewTest {
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        camp.nextstep.edu.missionutils.Console.close();
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    @DisplayName("구입 금액을 정상적으로 입력받는다")
    void readPriceTest() {
        String fakeInput = "5000\n";
        System.setIn(new ByteArrayInputStream(fakeInput.getBytes()));

        Integer price = InputView.readPrice();

        assertThat(price).isEqualTo(5000);
        assertThat(outContent.toString()).contains(InputView.PRICE_INPUT_MESSAGE);
    }

    @Test
    @DisplayName("당첨 번호를 정상적으로 입력받는다")
    void readWinningNumberTest() {
        String fakeInput = "1,2,3,4,5,6\n";
        System.setIn(new ByteArrayInputStream(fakeInput.getBytes()));

        List<Integer> numbers = InputView.readWinningNumber();

        assertThat(numbers).containsExactly(1,2,3,4,5,6);
        assertThat(outContent.toString()).contains(InputView.WINNING_NUMBERS_INPUT_MESSAGE);
    }

    @Test
    @DisplayName("보너스 번호를 정상적으로 입력받는다")
    void readBonusNumberTest() {
        String fakeInput = "7\n";
        System.setIn(new ByteArrayInputStream(fakeInput.getBytes()));

        int bonus = InputView.readBonusNumber();

        assertThat(bonus).isEqualTo(7);
        assertThat(outContent.toString()).contains(InputView.BONUS_NUMBER_INPUT_MESSAGE);
    }
}
