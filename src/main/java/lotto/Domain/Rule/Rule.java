package lotto.Domain.Rule;

import lotto.Domain.Lottery.Lotto;
import lotto.Domain.Rank.PrizeTier;
import lotto.Domain.Lottery.WinningLotto;

public interface Rule {
    PrizeTier judge(WinningLotto winningLotto, Lotto lotto);
}
