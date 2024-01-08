package com.claudemir.backend.util;

public class StringUtil {

    public boolean containValue(String content, String keyword){
        return content.toLowerCase().contains(keyword.toLowerCase());
    }
}
