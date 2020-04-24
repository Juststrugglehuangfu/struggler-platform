package com.struggler.authorization;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author wanglp
 * @date 2019/6/5
 **/

public class BCryptPasswordEncoderTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("android"));
    }
}
