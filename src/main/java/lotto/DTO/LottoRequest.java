package lotto.DTO;

import lotto.Domain.Lottery.Lotto;
import lotto.Domain.Lottery.PriceAmount;
import lotto.Domain.Lottery.WinningLotto;

import java.util.List;

public record LottoRequest(
        PriceAmount priceAmount,
        List<Lotto> purchased,
        WinningLotto winningLotto
) {
}
