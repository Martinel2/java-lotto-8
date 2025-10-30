package lotto.Util;

public enum ErrorMessage {
    LOTTO_NUMBER_RANGE_ERROR("번호는 1-45의 범위여야 합니다."),

    BONUS_NUMBER_BAD_INPUT("보너스 번호는 하나의 정수만 입력해야 합니다."),
    BONUS_NUMBER_DUPLICATE("보너스 번호는 당첨 번호와 중복될 수 없습니다."),

    NUMBER_DUPLICATE("번호는 서로 중복되지 않아야 합니다."),
    WINNING_NUMBER_BAD_FORMAT("당첨 번호 입력 형식을 준수해야 합니다. ex)1,2,3,4,5,6"),

    PRICE_BAD_INPUT("구입 금액은 숫자여야 합니다."),
    PRICE_NOT_DIV_THOUSAND("구입 금액은 1000단위여야 합니다."),
    PRICE_BAD_INTEGER("구입 금액은 0이나 음수가 될 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}