package com.jea.cards.util;

import java.util.Random;

/**
 * Source : https://gist.github.com/halienm/b929d2bc62eb69a1726a0c76a3dbbd57
 * 
 * String generate(String bin, int length)
 * 
 * @param bin
 *            The bank identification number, a set digits at the start of the card
 *            number, used to identify the bank that is issuing the card.
 * @param length
 *            The total length (i.e. including the BIN) of the card number.
 * @return
 *            A randomly generated, valid, credit card number.
 */
public class CreditCardNumberGenerator {

	private static Random random = new Random(System.currentTimeMillis());

	public static String generate(String bin, int length) {
        int randomNumberLength = length - (bin.length() + 1);
        StringBuilder builder = new StringBuilder(bin);
        
        for (int i = 0; i < randomNumberLength; i++) {
            int digit = random.nextInt(10);
            builder.append(digit);
        }
        
        int checkDigit = getCheckDigit(builder.toString());
        builder.append(checkDigit);
        return builder.toString();
    }

    private static int getCheckDigit(String number) {
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {

            int digit = Integer.parseInt(number.substring(i, (i + 1)));
            if ((i % 2) == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }
        int mod = sum % 10;
        return ((mod == 0) ? 0 : 10 - mod);
    }
}