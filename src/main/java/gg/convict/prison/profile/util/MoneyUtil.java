package gg.convict.prison.profile.util;

import java.math.BigDecimal;

public class MoneyUtil {

    private static final char[] END_TYPES = new char[]{
            'K', 'M', 'B', 'T'
    };

    public static String format(BigDecimal bigDecimal, int iteration) {
        if (bigDecimal.longValue() < 1000)
            return bigDecimal.toString();

        double cleanedValue = ((double) bigDecimal.longValue() / 100) / 10.0;
        boolean isRound = (cleanedValue * 10) % 10 == 0;

        return cleanedValue < 1000
                ? (isRound || cleanedValue > 9.99
                ? (int) cleanedValue * 10 / 10
                : String.valueOf(cleanedValue)) + String.valueOf(END_TYPES[iteration])
                : format(BigDecimal.valueOf(cleanedValue), iteration + 1);

    }

}