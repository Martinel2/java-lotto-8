package lotto.Domain.Lottery;

import lotto.DTO.MatchResult;
import lotto.Util.ErrorMessage;
import lotto.Util.ExceptionUtil;
import lotto.Util.ValidationUtil;

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

    public boolean isContainWinningNumber(int number) {
        return winningNumbers.contains(number);
    }

    public boolean isBonusNumber(int number) {
        return bonusNumber == number;
    }

    public MatchResult matchLotto(Lotto lotto) {
        int count = lotto.countMatches(this::isContainWinningNumber);
        boolean isBonus = lotto.contains(this.bonusNumber);
        return new MatchResult(count, isBonus);
    }

    //메서드 이름으로 유추할 수 있도록 한번 더 묶어줬음
    private void validateBonusNumberRange(int bonusNumber) {
        ValidationUtil.validateLottoNumberRange(bonusNumber);
    }

    private void validateBonusNumberNoDuplicate(Lotto winningNumbers, int bonusNumber) {
        if (isNull(winningNumbers)) {
            ExceptionUtil.throwInvalidValue(ErrorMessage.WINNING_NUMBER_BAD_FORMAT);
        }
        if (winningNumbers.contains(bonusNumber)) {
            ExceptionUtil.throwInvalidValue(ErrorMessage.BONUS_NUMBER_DUPLICATE);
        }
    }

    private boolean isNull(Lotto lotto) {
        return lotto == null;
    }
}
