package lotto.Util;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    public List<Integer> parseWinningNumbers(String winningNumbers) {
        try {
            return Arrays.stream(winningNumbers.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            ExceptionUtil.throwInvalidValue(ErrorMessage.WINNING_NUMBER_BAD_FORMAT);
            return null;
        }
    }

    public Integer parseBonusNumber(String bonusNumber) {
        try {
            return Integer.parseInt(bonusNumber);
        } catch (NumberFormatException e) {
            ExceptionUtil.throwInvalidValue(ErrorMessage.BONUS_NUMBER_BAD_INPUT);
            return null;
        }
    }

    public Integer parsePrice(String price) {
        try {
            return Integer.parseInt(price);
        } catch (NumberFormatException e) {
            ExceptionUtil.throwInvalidValue(ErrorMessage.PRICE_BAD_INPUT);
            return null;
        }
    }
}
