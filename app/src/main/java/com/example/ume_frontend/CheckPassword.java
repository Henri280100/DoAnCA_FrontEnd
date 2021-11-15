package com.example.ume_frontend;

import java.util.regex.Pattern;

public class CheckPassword {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
//                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 6 characters
                    "$");

    public static final Pattern PASSWORD_UPPERCASE_PATTERN =
            Pattern.compile("(?=.*[A-Z])" + ".*");
    public static final Pattern PASSWORD_LOWERCASE_PATTERN =
            Pattern.compile("(?=.*[a-z])" + ".*");
    public static final Pattern PASSWORD_NUMBER_PATTERN =
            Pattern.compile("(?=.*[0-9])" + ".*");
    public static final Pattern PASSWORD_SPECIAL_PATTERN =
            Pattern.compile("(?=.*[@#$%^&+=])" + ".*");

}
