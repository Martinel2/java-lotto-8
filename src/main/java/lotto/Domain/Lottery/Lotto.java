package lotto.Domain.Lottery;

import lotto.Util.ErrorMessage;
import lotto.Util.ExceptionUtil;
import lotto.Util.ValidationUtil;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.IntPredicate;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateCount(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
    }

    private void validateCount(List<Integer> numbers) {
        if (numbers.size() != ValidationUtil.MAX_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    // TODO: 추가 기능 구현
    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public int countMatches(IntPredicate intPredicate) {
        int c = 0;
        for (int number : numbers) {
            c += testMatch(intPredicate, number);
        }
        return c;
    }

    private Integer testMatch(IntPredicate intPredicate, int number){
        if (intPredicate.test(number)) return 1;
        else return 0;
    }

    private void validateRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            ValidationUtil.validateLottoNumberRange(number);
        }
    }

    // 당첨 번호간 중복 여부 체크
    // 보너스 번호와 기능은 비슷하지만, 방식이 달라 합치지 않음
    private void validateDuplicate(List<Integer> numbers) {
        if (hasDuplicate(numbers)) {
            ExceptionUtil.throwInvalidValue(ErrorMessage.NUMBER_DUPLICATE);
        }
    }

    private boolean hasDuplicate(Collection<Integer> numbers) {
        return numbers.size() != new HashSet<>(numbers).size();
    }

}
