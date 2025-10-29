package lotto.Domain;

import lotto.Util.ErrorMessage;
import lotto.Util.ExceptionUtil;

public class PriceAmount {
    Integer price;

    public PriceAmount(Integer price) {
        validate(price);
        this.price = price;
    }

    private void validate(Integer price) {
        validatePositive(price);
        validateDivByThousand(price);
    }

    private void validatePositive(Integer price) {
        if (price <= 0) {
            ExceptionUtil.throwInvalidValue(ErrorMessage.PRICE_BAD_INTEGER);
        }
    }

    private void validateDivByThousand(Integer price) {
        if (price % 1000 != 0) {
            ExceptionUtil.throwInvalidValue(ErrorMessage.PRICE_NOT_DIV_THOUSAND);
        }
    }
}
