package lotto.ViewTest;

import lotto.DTO.LottoResult;
import lotto.Domain.Rank.LottoRank;
import lotto.View.OutputView;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class OutputViewTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("로또 통계와 수익률이 올바르게 출력된다")
    void printResultTest() {
        // given
        Map<LottoRank, Long> statistics = new EnumMap<>(LottoRank.class);
        statistics.put(LottoRank.FIFTH, 1L);
        statistics.put(LottoRank.FOURTH, 0L);
        statistics.put(LottoRank.THIRD, 0L);
        statistics.put(LottoRank.SECOND, 0L);
        statistics.put(LottoRank.FIRST, 0L);

        LottoResult fakeResult = new LottoResult(statistics, 62.5);

        // when
        OutputView.printResult(fakeResult);

        // then
        String output = outContent.toString();

        assertThat(output).contains("당첨 통계");
        assertThat(output).contains("3개 일치 (5,000원) - 1개");
        assertThat(output).contains("총 수익률은 62.5%입니다.");
    }

    @Test
    @DisplayName("로또 구매 개수를 출력한다")
    void printPurchasedCountTest() {
        OutputView.printPurchasedCount(5);
        assertThat(outContent.toString()).contains("5개를 구매했습니다.");
    }
}

