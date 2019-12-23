package br.com.redhat.util;

public class RandomLongUtil {

    public static Long get(){
        long leftLimit = 1L;
        long rightLimit = 100000L;
        long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));

        return generatedLong;
    }
}
