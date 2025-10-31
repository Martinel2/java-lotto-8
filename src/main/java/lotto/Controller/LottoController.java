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
        PriceAmount priceAmount = readPriceAmount();

        List<Lotto> myLottos = lottoFactory.makeLottos(priceAmount.countLotto());
        OutputView.printMyLottos(myLottos);

        WinningLotto winningLotto = readWinningLotto();

        LottoRequest lottoRequest = new LottoRequest(priceAmount, myLottos, winningLotto);
        LottoResult lottoResult = makeLottoResult(lottoRequest);

        OutputView.printResult(lottoResult);
    }

    private LottoResult makeLottoResult(LottoRequest lottoRequest) {
        Map<LottoRank, Long> statistics = lottoService.getStatisticsResult(lottoRequest);
        Double earningRate = lottoService.getEarningRate(makeEaringRateRequest(statistics, lottoRequest.priceAmount()));
        return new LottoResult(statistics, earningRate);
    }

    private EarningRateRequest makeEaringRateRequest(Map<LottoRank, Long> statistics, PriceAmount priceAmount) {
        return new EarningRateRequest(statistics, priceAmount);
    }

    private PriceAmount readPriceAmount() {
        int price = InputView.readPrice();
        return new PriceAmount(price);
    }

    private WinningLotto readWinningLotto() {
        List<Integer> winningNumbers = InputView.readWinningNumber();
        Lotto lotto = new Lotto(winningNumbers);

        int bonusNumber = InputView.readBonusNumber();

        return new WinningLotto(lotto, bonusNumber);
    }
}
