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

    public static void printAllLottoNumbers(List<Lotto> lottos){
        lottos.forEach(OutputView::printLottoNumbers);
    }

    public static void printLottoNumbers(Lotto lotto){
        lotto.printLotto();
    }

    public static void printPurchasedCount(int count) {
        System.out.println(String.format(LOTTO_COUNT_RESULT_MESSAGE, count));
    }

    // 결과 출력하기
    public static void printResult(LottoResult lottoResult) {
        printLottoStatisticsHeader();
        printStatistics(lottoResult.lottoRankStatistics());
        printEaringRate(lottoResult.earningRate());
    }

    public static void printLottoStatisticsHeader(){
        System.out.println(LOTTO_STATISTICS_HEADER);
    }

    // 통계 출력하기
    public static void printStatistics(Map<LottoRank, Long> statistics) {
        System.out.println(String.format(FIFTH_MESSAGE, statistics.getOrDefault(LottoRank.FIFTH, 0L)));
        System.out.println(String.format(FOURTH_MESSAGE,statistics.getOrDefault(LottoRank.FOURTH, 0L)));
        System.out.println(String.format(THIRD_MESSAGE,statistics.getOrDefault(LottoRank.THIRD, 0L)));
        System.out.println(String.format(SECOND_MESSAGE,statistics.getOrDefault(LottoRank.SECOND, 0L)));
        System.out.println(String.format(FIRST_MESSAGE,statistics.getOrDefault(LottoRank.FIRST, 0L)));
    }

    // 수익률 출력하기
    public static void printEaringRate(Double earningRate){
        System.out.println(String.format(TOTAL_PROFIT_RATE, earningRate));
    }

}
