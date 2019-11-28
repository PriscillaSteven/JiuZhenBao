package com.mall.jiuzhenbao.sql;

public class StringUtils {
    private static final String UNDERLINE_CHAR = "_";
    public static String camel2Underline(String camelStr) {
        if (camelStr == null || camelStr.isEmpty()) {
            return "";
        }

        int len = camelStr.length();
        StringBuilder strb = new StringBuilder(len + len >> 1);
        for (int i = 0; i < len; i++) {

            char c = camelStr.charAt(i);
            if (Character.isUpperCase(c)) {

                strb.append(UNDERLINE_CHAR);
                strb.append(Character.toLowerCase(c));
            } else {

                strb.append(c);
            }
        }
        return strb.toString();
    }
}
