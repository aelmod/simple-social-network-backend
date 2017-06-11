package com.github.aelmod.ssn2.security.google2fa;

import com.github.aelmod.ssn2.user.User;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TokenGenerator {

    private final static String QR_PREFIX =
            "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";

    // TODO: 11.06.17 change this!
    private final static String APP_NAME = "diplom";

    public User2faToken generateQRUrl(User user) {
        try {
            String qr = QR_PREFIX + URLEncoder.encode(String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s",
                    APP_NAME, user.getEmail(), user.getSecret(), APP_NAME), "UTF-8");
            return new User2faToken(qr, user.getSecret());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
