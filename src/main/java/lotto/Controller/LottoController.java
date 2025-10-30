package lotto.Controller;

import lotto.Domain.Lottery.Lotto;
import lotto.Domain.Lottery.LottoFactory;
import lotto.Domain.Lottery.PriceAmount;
import lotto.Domain.Lottery.WinningLotto;
import lotto.Domain.Rank.LottoRank;
import lotto.View.InputView;
import lotto.View.OutputView;

import java.util.List;

public class LottoController {
    private final LottoFactory lottoFactory = new LottoFactory();

    public void run() {
        // 가격 설정
        PriceAmount priceAmount = makePriceAmount();
        List<Lotto> myLottos = lottoFactory.makeLottos(priceAmount.countLotto());
        // 내가 산 자동 로또 출력
        OutputView.printmyLottos();
        // 당첨 번호 + 보너스 번호 설정
        WinningLotto winningLotto = makeWinningLotto();
        // 로또 당첨 매칭 하기
        List<LottoRank> results = matchLottoTime(priceAmount,myLottos,winningLotto);
        //결과 출력하기
        OutputView.printResults(results);
    }

    private PriceAmount makePriceAmount(){
        // 가격 입력 받기
        int price = InputView.readPrice();
        return new PriceAmount(price);
    }

    private WinningLotto makeWinningLotto(){
        // 당첨 번호 입력 받기
        List<Integer> winningNumbers = InputView.readWinningNumber();
        Lotto lotto = new Lotto(winningNumbers);
        // 보너스 번호 입력 받기
        int bonusNumber = InputView.readBonusNumber();

        return new WinningLotto(lotto,bonusNumber);
    }
}
