package com.example.fitnessapp;

public class IsCheck {





    public void setStr(String str) {
        str = str;
    }
    public String checkPassword(String str){
        String msg = "";
        if(str.length()<3){
            msg+=" שם משתמש חייב להיות 3 תוים";

        }
        for(int i = 0 ; i <str.length();i++){
            if(str.charAt(i)<'a' || str.charAt(i)>'z'){
                msg+="השם משתמש לא תקין";

            }
            if(str.charAt(i)<'A' || str.charAt(i)>'Z'){
                msg+="אי אפשר בשם משתמש אותיות גדולות";

            }
        }

        return msg;
    }
    public static boolean checkEmail(String str) {
        String msg = "";
        String fixStr = "";
        boolean ok = true;

        for (char i = 'a'; i < 'z'; i++)
            fixStr += i;


        for (char J = 'A'; J < 'Z'; J++)
            fixStr += J;

        for ( int K = 0; K < 9; K++)
            fixStr += K;


        fixStr += "_";


        if (!(str.indexOf('@') >= 0)) {
            msg += " באיימל אין  שטרודל" + "/n";

        }
        if (!(str.indexOf('@') == str.lastIndexOf('@'))) {
            msg += " באיימל מופיע שטרודל יותר מפעם אחת" + "/n";

        }


        int r = 0;
        while (r < str.length() && ok) {
            if (fixStr.indexOf(str.charAt(r)) < 0) {
                ok = false;
                msg += "תו לא תקין באיימל" + "/n";
            }
            r++;


        }
        return ok;
    }
}




