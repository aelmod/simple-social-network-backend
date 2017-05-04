package com.github.aelmod.ssn2.security.google2fa;

import com.github.aelmod.ssn2.user.User;
import org.apache.commons.codec.binary.Base32;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class TimeBasedOneTimePassword {

    private static String QR_PREFIX =
            "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";

    private static String APP_NAME = "ElectronicHealthCardSystem";

    public String generateQRUrl(User user) {
        try {
            return QR_PREFIX + URLEncoder.encode(String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s", APP_NAME, user.getEmail(), user.getSecret(), APP_NAME), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final int VARIANCE = 5;

    public boolean isVerificationCodeValid(String secret, int code) throws VerificationCodeException {
        boolean result;
        try {
            result = verifyCode(secret, code, getTimeIndex(), VARIANCE);
        } catch (NoSuchAlgorithmException e) {
            throw new VerificationCodeException("invalid algorithm");
        } catch (InvalidKeyException e) {
            throw new VerificationCodeException("invalid key");
        }

        return result;
    }

    private static boolean verifyCode(String secret, int code, long timeIndex, int variance)
            throws NoSuchAlgorithmException, InvalidKeyException {
        for (int i = -variance; i <= variance; i++) {
            if (getCode(secret, timeIndex + i) == code) {
                return true;
            }
        }
        return false;
    }

    private static long getCode(String secret, long timeIndex) throws InvalidKeyException, NoSuchAlgorithmException {
        return getCode(new Base32().decode(secret), timeIndex);
    }

    private static long getCode(byte[] secret, long timeIndex) throws NoSuchAlgorithmException, InvalidKeyException {

        SecretKeySpec signKey = new SecretKeySpec(secret, "HmacSHA1");
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(timeIndex);
        byte[] timeBytes = buffer.array();
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(timeBytes);
        int offset = hash[19] & 0xf;
        long truncatedHash = hash[offset] & 0x7f;
        for (int i = 1; i < 4; i++) {
            truncatedHash <<= 8;
            truncatedHash |= hash[offset + i] & 0xff;
        }
        return truncatedHash % 1000000;
    }

    private static long getTimeIndex() {
        return System.currentTimeMillis() / 1000 / 30;
    }

}
