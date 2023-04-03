package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class Randomizer {

    private static String randomEmail = String.format("%s@ya.com", RandomStringUtils.randomAlphabetic(15));

    public static String getRandomEmail(){
        return randomEmail;
    }
}
