package lotto.Domain.Lottery;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

public class LottoFactory {
    public List<Lotto> makeLottos(int buyCount){
        List<Lotto> lottos = new ArrayList<Lotto>();
        for (int i = 0; i < buyCount; i++) {
            lottos.add(randomLotto());
        }
        return lottos;
    }

    private Lotto randomLotto(){
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                ValidationUtil.MIN_LOTTO_NUMBER,
                ValidationUtil.MAX_LOTTO_NUMBER,
                ValidationUtil.MAX_SIZE
        );
        return new Lotto(numbers);
    }
}
