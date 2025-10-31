package lotto.DTO;

import lotto.Domain.Rank.LottoRank;

import java.util.Map;

public record LottoResult(
        Map<LottoRank, Long> lottoRankStatistics,
        Double earningRate
) {
}
