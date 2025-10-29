package lotto.Domain;

import lotto.Util.ErrorMessage;
import lotto.Util.ExceptionUtil;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        validateRange(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    // TODO: 추가 기능 구현
    private void validateRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            validateRangeNumber(number);
        }
    }

    private void validateRangeNumber(final int number) {
        if (1 <= number && number <= 45) {
            ExceptionUtil.throwInvalidValue(ErrorMessage.PRICE_BAD_INPUT);
        }
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }
}
