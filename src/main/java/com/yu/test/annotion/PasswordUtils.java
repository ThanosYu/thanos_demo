package com.yu.test.annotion;

/**
 * @author Thanos_Yu
 * @date 2018/3/15.
 */
/**
 * 使用注解：
 *
 * */
public class PasswordUtils {
    @UseCase.UseCases(id="1",description="Passwords must contain at least one numeric")
    public boolean validatePassword(String password) {
        return (password.matches("\\w*\\d\\w*"));
    }

    @UseCase.UseCases(id ="2")
    public String encryptPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }
}