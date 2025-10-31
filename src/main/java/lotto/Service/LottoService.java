package lotto.Service;

import lotto.DTO.EarningRateRequest;
import lotto.DTO.LottoRequest;
import lotto.Domain.Rank.LottoRank;
import lotto.Domain.Rule.LottoRule;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoService {
    private final LottoRule lottoRule = new LottoRule();

    /*
    게임을 위해 필요한 주요 기능들을 여기서 통합
     */

    public Map<LottoRank, Long> getStatisticsResult(LottoRequest lottoRequest) {
        return calculateStatistics(getRanks(lottoRequest));
    }

    public Double getEarningRate(EarningRateRequest earningRateRequest) {
        // 수익률 = 당첨 금액 / 구입 금액 * 100
        return earningRateRequest.priceAmount().getEarningRate(getEarning(earningRateRequest.statistics()));
    }

    private List<LottoRank> getRanks(LottoRequest lottoRequest) {
        return lottoRequest.purchased().stream()
                .map(l -> lottoRule.judge(lottoRequest.winningLotto(), l))
                .toList();
    }

    private Map<LottoRank, Long> calculateStatistics(List<LottoRank> results) {
        return results.stream()
                .collect(Collectors.groupingBy(rank -> rank, Collectors.counting()));
    }

    private Long getEarning(Map<LottoRank, Long> statistics) {
        return statistics.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }
}
