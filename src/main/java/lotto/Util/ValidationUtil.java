package lotto.Util;

public final class ValidationUtil {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    public static final Integer MAX_SIZE = 6;

    // private 생성자로 객체 생성 방지
    private ValidationUtil() {
    }

    /**
     * 숫자가 로또 번호 범위(1-45)를 벗어나는지 검증
     */
    public static void validateLottoNumberRange(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            ExceptionUtil.throwInvalidValue(ErrorMessage.LOTTO_NUMBER_RANGE_ERROR);
        }
    }
}
