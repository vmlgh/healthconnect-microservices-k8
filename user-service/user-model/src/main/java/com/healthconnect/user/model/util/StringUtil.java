package com.healthconnect.user.model.util;

import java.util.regex.Pattern;

public class StringUtil {

	public static final String EMAIL_REGEX = "[A-Za-z0-9-_.]+@[A-Za-z0-9-_]+(?:\\.[A-Za-z0-9]+)+";
	
    public static boolean checkValidEmail(String text) {
        boolean email = false;
        Pattern pat = Pattern.compile(EMAIL_REGEX);
        if(pat.matcher(text).matches()) {
            email = true;
        }
        return email;


    }
}