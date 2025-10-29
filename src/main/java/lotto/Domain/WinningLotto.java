package lotto.Domain;

import lotto.Util.ErrorMessage;
import lotto.Util.ExceptionUtil;

// 당첨 번호 + 보너스 번호
public class WinningLotto {

    private final Lotto winningNumbers; // 6개 당첨 번호
    private final int bonusNumber;      // 1개 보너스 번호

    public WinningLotto(Lotto winningNumbers, int bonusNumber) {
        // 보너스 번호 범위 검증 (1~45)
        validateBonusNumberRange(bonusNumber);

        // 6개 당첨 번호와 보너스 번호 중복 검증
        validateBonusNumberNoDuplicate(winningNumbers, bonusNumber);

        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            ExceptionUtil.throwInvalidValue(ErrorMessage.LOTTO_NUMBER_RANGE_ERROR);
        }
    }

    private void validateBonusNumberNoDuplicate(Lotto winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            ExceptionUtil.throwInvalidValue(ErrorMessage.BONUS_NUMBER_DUPLICATE);
        }
    }

}
