package com.example.cwrk_v2;
//share persistend data with every controller
public class UserHolder {

    private static String userName;
    private static boolean isAdmin;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        UserHolder.userName = userName;
    }

    public static boolean isIsAdmin() {
        return isAdmin;
    }

    public static void setIsAdmin(boolean isAdmin) {
        UserHolder.isAdmin = isAdmin;
    }
}