/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.remotetokenizer.providers;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author Georgi Spasov
 */
public class Tokenizer {

    public boolean checkCardId(String cardId) {
        int cardIdDigits[] = Stream
                .of(cardId.split(""))
                .mapToInt(n -> Integer.parseInt(n))
                .toArray();
        int sum = 0;
        int length = cardIdDigits.length;
        for (int i = 0; i < length; i++) {
            int digit = cardIdDigits[length - i - 1];
            if (i % 2 == 1) {
                digit *= 2;
            }
            sum += digit > 9 ? digit - 9 : digit;
        }
        return sum % 10 == 0;
    }

    public String tokenize(String cardId) {
        Random rnd = new Random();
        int tokenLength = cardId.length();
        int firstDigits[] = {3, 4, 5, 6};
        int cardIdDigits[] = Stream
                .of(cardId.split(""))
                .mapToInt(n -> Integer.parseInt(n))
                .toArray();
        int tokenDigits[] = new int[tokenLength];
        String result;

        for (int i = 0; i < tokenLength; i++) {
            if (i == 0) {
                tokenDigits[i] = getRandomWithExclusion(rnd, firstDigits);
            } else if (i >= tokenLength - 4) {
                tokenDigits[i] = cardIdDigits[i];
                if (i == tokenLength - 1) {
                    int tokenNumSum = IntStream.of(tokenDigits).sum();
                    while (tokenNumSum % 10 == 0
                            || tokenDigits[tokenLength - 4 - 1] == cardIdDigits[tokenLength - 4 - 1]) {
                        tokenDigits[tokenLength - 4 - 1] = ++tokenDigits[tokenLength - 4 - 1] % 10;
                        tokenNumSum = IntStream.of(tokenDigits).sum();
                    }
                }
            } else {
                tokenDigits[i] = getRandomWithExclusion(rnd, cardIdDigits[i]);
            }
        }
        result = Arrays.stream(tokenDigits)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(""));

        return result;
    }

    private static int getRandomWithExclusion(Random rnd, int... excluded) {
        int random = rnd.nextInt(10);
        for (int ex : excluded) {
            if (random == ex) {
                random = ++random % 10;
            }
        }

        return random;
    }
}
