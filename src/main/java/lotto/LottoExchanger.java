package lotto;

import lotto.lotto.Lotto;
import lotto.lotto.LottoGenerator;
import lotto.lotto.ManualLottoes;
import lotto.money.Money;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class LottoExchanger {

    private final LottoGenerator lottoGenerator;

    public LottoExchanger(LottoGenerator lottoGenerator) {
        this.lottoGenerator = requireNonNull(lottoGenerator, "lottoGenerator");
    }

    public List<Lotto> exchange(Money money, ManualLottoes manualLottoes) {
        final List<Lotto> purchasedLottoes = new ArrayList<>();
        final Money remainMoney = exchangeManualLottoes(money, manualLottoes, purchasedLottoes);
        exchangeAutoGeneratedLottoes(remainMoney, purchasedLottoes);
        return purchasedLottoes;
    }

    private Money exchangeManualLottoes(Money money, ManualLottoes manualLottoes, List<Lotto> purchasedLottoes) {
        if (!manualLottoes.isPurchase()) {
            return money;
        }
        final Money totalMoney = Lotto.PRICE.multiple(manualLottoes.size());
        if (!money.canDeduct(totalMoney)) {
            throw new NotEnoughMoneyException(money, totalMoney);
        }
        Money remainMoney = money;
        for (String maybeLottoNumbers : manualLottoes.lottoes()) {
            final Lotto lotto = Lotto.of(maybeLottoNumbers);
            remainMoney = money.deduct(lotto.price());
            purchasedLottoes.add(lotto);
        }
        return remainMoney;
    }

    private void exchangeAutoGeneratedLottoes(Money remainMoney, List<Lotto> purchasedLottoes) {
        if (purchasedLottoes.isEmpty() && !remainMoney.canDeduct(Lotto.PRICE)) {
            throw new NotEnoughMoneyException(remainMoney, Lotto.PRICE);
        }
        while (remainMoney.canDeduct(Lotto.PRICE)) {
            final Lotto lotto = lottoGenerator.generate();
            remainMoney = remainMoney.deduct(lotto.price());
            purchasedLottoes.add(lotto);
        }
    }
}
