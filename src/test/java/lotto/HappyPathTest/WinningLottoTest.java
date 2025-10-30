package lotto.HappyPathTest;

import lotto.Domain.Lottery.Lotto;
import lotto.Domain.Lottery.WinningLotto;
import lotto.Domain.Rank.LottoRank;
import lotto.Domain.Rule.LottoRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WinningLottoTest {
    private WinningLotto winningLotto;
    private List<Lotto> purchased;

    @BeforeEach
    void setUpLotto(){
        purchased = List.of(
                new Lotto(List.of(8, 21, 23, 41, 42, 43)),
                new Lotto(List.of(3, 5, 11, 16, 32, 38)),
                new Lotto(List.of(7, 11, 16, 35, 36, 44)),
                new Lotto(List.of(1, 8, 11, 31, 41, 42)),
                new Lotto(List.of(13, 14, 16, 38, 42, 45)),
                new Lotto(List.of(7, 11, 30, 40, 42, 43)),
                new Lotto(List.of(2, 13, 22, 32, 38, 45)),
                new Lotto(List.of(1, 3, 5, 14, 22, 45))
        );
    }

    @BeforeEach
    void setUpWinningLotto() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 45;
        winningLotto = new WinningLotto(winningNumbers,bonusNumber);
    }

    @Test
    @DisplayName("당첨 통계 테스트")
    public void WinningStatsTest(){
        LottoRule lottoRule = new LottoRule();
        List<LottoRank> ranks = purchased.stream()
                .map(l -> lottoRule.judge(winningLotto, l))
                .toList();

        // 샘플 시나리오: 3개 일치 하나(FIFTH 1개), 나머지 NONE
        Long fifthCount = ranks.stream()
                .filter(r -> r == LottoRank.FIFTH)
                .count();
        Long others    = ranks.stream()
                .filter(r -> r != LottoRank.FIFTH && r != LottoRank.NONE)
                .count();

        assertThat(fifthCount).isEqualTo(1L);
        assertThat(others).isZero();
        assertThat(ranks.stream().filter(r -> r == LottoRank.NONE).count()).isEqualTo(7);
    }
}
