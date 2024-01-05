package com.axreng.backend.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GenerateIdUtilTest {

    @Test
    public void generacode(){
        String codigo = GenerateIdUtil.gerarCodigo();
        int number = Integer.parseInt(codigo.substring(0,2));
        Assertions.assertTrue(number >= 0);
        Assertions.assertEquals(8, codigo.length());
    }

}
