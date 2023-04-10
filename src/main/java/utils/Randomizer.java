package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class Randomizer {

    public  static String getRandomEmail(){
        return String.format("%s@ya.com", RandomStringUtils.randomAlphabetic(15));
    }
    public static String getText(){
        return RandomStringUtils.randomAlphabetic(6,15);
    }
}
