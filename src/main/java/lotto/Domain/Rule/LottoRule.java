package lotto.Domain.Rule;

import lotto.DTO.MatchResult;
import lotto.Domain.Lottery.Lotto;
import lotto.Domain.Lottery.WinningLotto;
import lotto.Domain.Rank.LottoRank;

public final class LottoRule implements Rule {

    /*
    당첨 여부 판단 로직 구현
    AI와 대화하여 다른 규칙이 올 수도 있다는 관점을 얻음.
    따라서, rule을 인터페이스로 선언하여 이를 받는 메소드로 구현
    rule에 따라 판단하도록하여 수정이 일어나도 이 코드는 수정이 없도록 함.
     */
    @Override
    public LottoRank judge(WinningLotto winningLotto, Lotto lotto) {
        MatchResult matchResult = winningLotto.matchLotto(lotto);
        return LottoRank.rankOf(matchResult);
    }

}
