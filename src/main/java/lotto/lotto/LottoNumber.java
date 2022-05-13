package lotto.lotto;

import lotto.util.StringUtils;
import java.util.Objects;

class LottoNumber implements Comparable<LottoNumber> {

    static final int MIN_VALUE = 1;
    static final int MAX_VALUE = 45;

    private final int value;

    protected LottoNumber(String value) {
        this(parse(value));
    }

    protected LottoNumber(int value) {
        this.value = validate(value);
    }

    public static LottoNumber of(String value) {
        return new LottoNumber(value);
    }

    public static LottoNumber of(int value) {
        return new LottoNumber(value);
    }

    private static int parse(String value) {
        if (StringUtils.isEmpty(value)) {
            throw new LottoNumberFormatException(value);
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            throw new LottoNumberFormatException(value);
        }
    }

    private static int validate(int value) {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new LottoNumberOutOfBoundsException(value);
        }
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        LottoNumber lottoNumber = (LottoNumber) other;
        return value == lottoNumber.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.value, other.value);
    }
}
