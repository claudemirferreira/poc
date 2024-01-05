package com.axreng.backend.util;

import java.util.Random;

public class GenerateIdUtil {

    public static String gerarCodigo() {
        return gerarNumeros() + gerarLetras();
    }

    private static String gerarNumeros() {
        int QTD_NUMBER = 2;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < QTD_NUMBER; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private static String gerarLetras() {
        int QTD_CHAR = 6;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < QTD_CHAR; i++) {
            sb.append((char) (random.nextInt(26) + 'A'));
        }
        return sb.toString();
    }

}
