package com.example.fitnessapp.PrivateWorkout;

public class InputChecker {
    private static final int PHONE_NUMBER_LENGTH = 10;

    public static boolean isStringInputValid(String str) {
        // Returns true if the input field is not all spaces
        return str.trim().length() > 0;
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber.length() == PHONE_NUMBER_LENGTH) {
            for (int i = 0; i < phoneNumber.length(); i++) {
                if (phoneNumber.charAt(i) < '0' && phoneNumber.charAt(i) > '9') {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
