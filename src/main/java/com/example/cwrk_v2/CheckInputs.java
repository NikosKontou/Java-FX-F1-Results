package com.example.cwrk_v2;

public class CheckInputs {

    public static Boolean checkDriverNumber(String DNumber){
        if (!DNumber.isBlank()) {
            try {
                int intDNumber = Integer.parseInt(DNumber);
                if (intDNumber > 0 && intDNumber < 100) {
                    return true;
                } else {
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("NaN");
                return false;
            }

        } else return false;
    }
    public static Boolean checkYear(String year) {
        if (!year.isBlank()) {
            try {
                int intYear = Integer.parseInt(year);
                if (intYear > 1946 && intYear < 3000) {
                    return true;
                } else {
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("NaN");
                return false;
            }

        } else return false;

    }

    public static Boolean checkRound(String round) {
        //regulations dicatate the maximum number of races over a year to 25
        try {
            if ((!round.isBlank())) {
                int intRound = Integer.parseInt(round);
                if (intRound > 0 && intRound <= 25) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("NaN");
            return false;
        }

    }
}
