package lotto.view;

import lotto.lotto.LottoNumber;
import lotto.lotto.ManualLottoes;
import lotto.lotto.WinningLotto;
import lotto.money.Money;

public interface InputView {

    static InputView console() {
        return new ConsoleInputView();
    }

    Money readMoney();

    ManualLottoes readManualLottoes();

    WinningLotto readPreviousWinningLotto();

    LottoNumber readBonusLottoNumber();
}
