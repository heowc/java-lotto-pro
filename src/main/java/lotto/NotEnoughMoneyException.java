package lotto;

import lotto.money.Money;

public class NotEnoughMoneyException extends RuntimeException {

    public NotEnoughMoneyException(Money money, Money price) {
        super(String.format("Money가 충분하지 않습니다. (금액: %s, 가격: %s)", money, price));
    }
}
