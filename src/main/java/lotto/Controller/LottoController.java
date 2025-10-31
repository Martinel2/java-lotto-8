package lotto.Controller;

import lotto.DTO.EarningRateRequest;
import lotto.DTO.LottoRequest;
import lotto.DTO.LottoResult;
import lotto.Domain.Lottery.Lotto;
import lotto.Domain.Lottery.LottoFactory;
import lotto.Domain.Lottery.PriceAmount;
import lotto.Domain.Lottery.WinningLotto;
import lotto.Domain.Rank.LottoRank;
import lotto.Service.LottoService;
import lotto.View.InputView;
import lotto.View.OutputView;

import java.util.List;
import java.util.Map;

public class LottoController {
    private final LottoFactory lottoFactory = new LottoFactory();
    private final LottoService lottoService = new LottoService();

    public void run() {
        // 가격 설정
        PriceAmount priceAmount = readPriceAmount();
        List<Lotto> myLottos = lottoFactory.makeLottos(priceAmount.countLotto());
        // 내가 산 자동 로또 출력
        OutputView.printMyLottos(myLottos);
        // 당첨 번호 + 보너스 번호 설정
        WinningLotto winningLotto = readWinningLotto();
        // 로또 당첨 매칭 하기
        LottoRequest lottoRequest = new LottoRequest(priceAmount,myLottos,winningLotto);
        LottoResult lottoResult = makeLottoResult(lottoRequest);
        //결과 출력하기
        OutputView.printResult(lottoResult);
    }

    private LottoResult makeLottoResult(LottoRequest lottoRequest) {
        Map<LottoRank, Long> statistics = lottoService.getStatisticsResult(lottoRequest);
        Double earningRate = lottoService.getEarningRate(makeEaringRateRequest(statistics,lottoRequest.priceAmount()));
        return new LottoResult(statistics, earningRate);
    }

    private EarningRateRequest makeEaringRateRequest(Map<LottoRank, Long> statistics, PriceAmount priceAmount) {
        return new EarningRateRequest(statistics, priceAmount);
    }

    private PriceAmount readPriceAmount(){
        // 가격 입력 받기
        int price = InputView.readPrice();
        return new PriceAmount(price);
    }

    private WinningLotto readWinningLotto(){
        // 당첨 번호 입력 받기
        List<Integer> winningNumbers = InputView.readWinningNumber();
        Lotto lotto = new Lotto(winningNumbers);
        // 보너스 번호 입력 받기
        int bonusNumber = InputView.readBonusNumber();

        return new WinningLotto(lotto,bonusNumber);
    }
}
