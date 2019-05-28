package com.megatravel.LoginAndRegistration.validation;

import java.util.regex.Pattern;

public class StaticData {
	public static final int lengthValue = 30;
	public static final int lengthDescription = 140;
	public static final int minLength = 0;
	public static final int minLengthEmail = 10;
	public static final int maxLengthEmail = 60;
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public static final Pattern VALID_PASSWORD_NUMBERS = Pattern.compile("[0-9]+");
	public static final Pattern VALID_PASSWORD_SPECIAL_CHARACTERS = Pattern.compile("[+-/*/.,!?+_#%^]+");
	public static final Pattern VALID_PASSWORD_UPPER_CASE = Pattern.compile("[A-Z]+");
	public static final Pattern VALID_PASSWORD_LOWER_CASE = Pattern.compile("[a-z]+");
}
