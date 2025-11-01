package lotto.View;

import lotto.DTO.LottoResult;
import lotto.Domain.Lottery.Lotto;
import lotto.Domain.Rank.LottoRank;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String LOTTO_COUNT_RESULT_MESSAGE = "\n%d개를 구매했습니다.";
    private static final String LOTTO_STATISTICS_HEADER = """
            
            당첨 통계
            ---
            """;
    private static final String FIFTH_MESSAGE = "3개 일치 (5,000원) - %d개";
    private static final String FOURTH_MESSAGE = "4개 일치 (50,000원) - %d개";
    private static final String THIRD_MESSAGE = "5개 일치 (1,500,000원) - %d개";
    private static final String SECOND_MESSAGE = "5개 일치, 보너스 볼 일치 (30,000,000원) - %d개";
    private static final String FIRST_MESSAGE = "6개 일치 (2,000,000,000원) - %d개";
    private static final String TOTAL_PROFIT_RATE = "총 수익률은 %.1f%%입니다.";

    private OutputView() {
    }

    public static void printMyLottos(List<Lotto> lottos){
        printPurchasedCount(lottos.size());
        printAllLottoNumbers(lottos);
    }

    public static void printPurchasedCount(int count) {
        System.out.println(formatPurchasedCount(count));
    }

    public static void printResult(LottoResult lottoResult) {
        printLottoStatisticsHeader();
        printAllStatistics(lottoResult.lottoRankStatistics());
        printEaringRate(lottoResult.earningRate());
    }

    private static void printAllLottoNumbers(List<Lotto> lottos){
        lottos.forEach(OutputView::printLottoNumbers);
    }

    private static void printLottoNumbers(Lotto lotto){
        lotto.printLotto();
    }

    private static void printLottoStatisticsHeader(){
        System.out.println(LOTTO_STATISTICS_HEADER);
    }

    /*
    이 코드가 하드코딩이 된 느낌이 있음
    어떻게 바꾸어야 하드코딩에서 탈피할 수 있을까?
    지금 무엇을, 어떻게 두가지가 하나의 메서드에 존재함
     */
    private static void printAllStatistics(Map<LottoRank, Long> statistics) {
        printStatistics(FIFTH_MESSAGE, statistics, LottoRank.FIFTH);
        printStatistics(FOURTH_MESSAGE, statistics, LottoRank.FOURTH);
        printStatistics(THIRD_MESSAGE, statistics, LottoRank.THIRD);
        printStatistics(SECOND_MESSAGE, statistics, LottoRank.SECOND);
        printStatistics(FIRST_MESSAGE, statistics, LottoRank.FIRST);
    }

    private static void printStatistics(String message, Map<LottoRank, Long> statistics, LottoRank rank) {
        System.out.println(formatStatistics(message,statistics,rank));
    }

    private static void printEaringRate(Double earningRate){
        System.out.println(formatEaringRate(earningRate));
    }

    private static String formatEaringRate(Double earningRate) {
        return String.format(TOTAL_PROFIT_RATE, earningRate);
    }

    private static String formatPurchasedCount(int count){
        return String.format(LOTTO_COUNT_RESULT_MESSAGE, count);
    }

    private static String formatStatistics(String message, Map<LottoRank, Long> statistics, LottoRank rank) {
        return String.format(message, statistics.getOrDefault(rank, 0L));
    }


}
