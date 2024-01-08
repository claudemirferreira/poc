package com.claudemir.backend.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringUtilTest {

    private StringUtil stringUtil = new StringUtil();

    @Test
    public void contain_ok(){
        String html = "<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\"><html><head><title>301 Moved Permanently</title></head><body><h1>Moved Permanently</h1><p>The document has moved <a href=\"https://github.com/linux-pam/linux-pam/library/\">here</a>.</p></body></html>";
        Assertions.assertTrue(stringUtil.containValue(html, "html"));
    }

    @Test
    public void contain_not(){
        String html = "<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\"><html><head><title>301 Moved Permanently</title></head><body><h1>Moved Permanently</h1><p>The document has moved <a href=\"https://github.com/linux-pam/linux-pam/library/\">here</a>.</p></body></html>";
        Assertions.assertFalse(stringUtil.containValue(html, "htmlllll"));
    }

}
