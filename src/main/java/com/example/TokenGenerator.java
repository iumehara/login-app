package com.example;

import java.math.BigInteger;
import java.security.SecureRandom;

class TokenGenerator {
    static String createRandom() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
}
