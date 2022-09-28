package duan.server.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

/**
 * @Description: Hash工具类
 * @Author: duanyhui
 * @Date: 2022/9/5
 */
public class HashUtils {
    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String getMd5(CharSequence str) {
        return DigestUtils.md5DigestAsHex(str.toString().getBytes());
    }

    public static String getBC(CharSequence rawPassword) {
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    public static boolean matchBC(CharSequence rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }

    public static void main(String[] args) {
        System.out.println(getBC("1"));
    }
}
