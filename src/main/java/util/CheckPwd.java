package util;

/**
 * @Author: Xusj
 * @Date: 2020/3/26
 * @Description: 校验密码类
 */
public class CheckPwd {


    public static final String PW_PATTERN = "^(?![A-z]+$)(?![0-9]+$)(?![\\W]+$)[A-z0-9\\W]{8,}$";


    public static void main(String[] args) {

        String pw1 = "ABCDEFGHIG";
        String pw2 = "abcdefghig";
        String pw3 = "0123456789";
        String pw4 = "!@#$%^&*()";
        String pw5 = "ABCDEabcde";
        String pw6 = "ABCDE01234";
        String pw7 = "ABCDE!@#$%";
        String pw8 = "abcde01234";
        String pw9 = "abcde!@#$%";
        String pw10 = "01234!@#$%";
        String pw11 = "abcde01234!@#$%";
        String pw12 = "ABCDE01234!@#$%";
        String pw13 = "ABCDEabcde!@#$%";
        String pw14 = "ABCDEabcde01234";
        String pw15 = "Aa0!";

        String pw16="12345678!";

        System.out.println("1:"+pw1.matches(PW_PATTERN));
        System.out.println("2:"+pw2.matches(PW_PATTERN));
        System.out.println("3:"+pw3.matches(PW_PATTERN));
        System.out.println("4:"+pw4.matches(PW_PATTERN));
        System.out.println("5:"+pw5.matches(PW_PATTERN));
        System.out.println("6:"+pw6.matches(PW_PATTERN));
        System.out.println("7:"+pw7.matches(PW_PATTERN));
        System.out.println("8:"+pw8.matches(PW_PATTERN));
        System.out.println("9:"+pw9.matches(PW_PATTERN));
        System.out.println("10:"+pw10.matches(PW_PATTERN));
        System.out.println("11:"+pw11.matches(PW_PATTERN));
        System.out.println("12:"+pw12.matches(PW_PATTERN));
        System.out.println("13:"+pw13.matches(PW_PATTERN));
        System.out.println("14:"+pw14.matches(PW_PATTERN));
        System.out.println("15:"+pw15.matches(PW_PATTERN));
        System.out.println("16:"+pw16.matches(PW_PATTERN));
    }
}
