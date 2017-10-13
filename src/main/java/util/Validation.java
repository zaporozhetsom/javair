package util;

/**
 * Created by zom on 10.10.2017.
 */
public final class Validation {

    public static boolean isPasswordValid(String password1) {
        if (password1 == null || password1.isEmpty()) {
            return false;
        }

        return password1.matches("(?=.*\\d)(?=.*[a-zа-я])(?=.*[A-ZА-Я]).{8,}");
    }

    public static boolean isInputTextValid(String text) {
        return true;//todo write login validation
    }
}
