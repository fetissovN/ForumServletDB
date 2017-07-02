package com.nick.forum.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtils {
	public static final String EMAIL = "txt_email";

	public static final String NICK = "txt_nick";

	public static final String EMPTY_STR = "";

	private StringUtils() {
	}

	public static boolean isEmpty(String str) {
        if (str != null) {
            return EMPTY_STR.equals(str);
        }

        return true;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isBlank(String str) {
        if (isNotEmpty(str)) {
            return EMPTY_STR.equals(str.trim());
        }
        return true;
	}

	public static boolean isNotBlank(String str) {
        return !isBlank(str);
	}

	public static String capitalizeFirstLetter(String str) {
		if(StringUtils.isBlank(str)) {
			return str;
		}

		Character firstChar = str.charAt(0);
		Character firstCapitalChar = Character.toUpperCase(firstChar);
		
		String result = firstCapitalChar + str.substring(1);
		
		return result;
	}

	public static boolean emailCheck(String str){
	    Boolean check = false;
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9]+(.[_A-Za-z0-9-])*@(.[_A-Za-z0-9]{1,10})(.[a-z]{1,15})");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()){
            check=true;
        }
	    return check;
    }
}
