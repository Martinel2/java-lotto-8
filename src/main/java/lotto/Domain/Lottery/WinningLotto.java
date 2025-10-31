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
        validate(winningNumbers, bonusNumber);

        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lotto winningNumbers, int bonusNumber){
        validateBonusNumberRange(bonusNumber);
        validateBonusNumberNoDuplicate(winningNumbers, bonusNumber);
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
