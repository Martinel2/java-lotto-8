package lotto.Domain.Lottery;

import lotto.Util.ErrorMessage;
import lotto.Util.ExceptionUtil;

public class PriceAmount {
    private static final int UNIT = 1000;
    private final Integer price;

    public PriceAmount(Integer price) {
        validate(price);
        this.price = price;
    }

    public Integer countLotto() {
        return price / UNIT;
    }

    public Double getEarningRate(double earning){
        return roundEarningRate(calculateEarningRate(earning));
    }

    private Double calculateEarningRate(double earning) {
        // 수익률 = 당첨 금액 / 구입 금액 * 100
        return earning / price * 100;
    }

    private Double roundEarningRate(double earningRate) {
        return Math.round(earningRate * 100.0) / 100.0;
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
        if (price % UNIT != 0) {
            ExceptionUtil.throwInvalidValue(ErrorMessage.PRICE_NOT_DIV_THOUSAND);
        }
    }
}
