package lotto.DTO;

import lotto.Domain.Lottery.PriceAmount;
import lotto.Domain.Rank.LottoRank;

import java.util.Map;

public record EarningRateRequest(
        Map<LottoRank,Long> statistics,
        PriceAmount priceAmount
) {
}
