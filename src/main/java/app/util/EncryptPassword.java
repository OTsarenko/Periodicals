package app.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Method used to hash user password.
 *
 * @return hashed password
 */
public class EncryptPassword {

    static final Logger LOG = LogManager.getLogger(EncryptPassword.class);
    private static final int iterations = 20 * 1000;
    private static final int saltLen = 32;
    private static final int desiredKeyLen = 256;

    public static String getSaltedHash(String password) {
        byte[] salt = new byte[0];
        String result = "";
        try {
            salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
            result = Base64.getEncoder().encodeToString(salt) + "$" + hash(password, salt);
        } catch (Exception e) {
            LOG.error(e.fillInStackTrace());
        }
        return result;
    }

    public static boolean check(String password, String stored) {
        String[] saltAndPass = stored.split("\\$");
        if (saltAndPass.length != 2) {
            throw new IllegalStateException("The stored password have the form 'salt$hash'");
        }
        String hashOfInput = null;
        try {
            hashOfInput = hash(password, Base64.getDecoder().decode(saltAndPass[0]));
        } catch (Exception e) {
            LOG.error(e.fillInStackTrace());
        }
        return hashOfInput.equals(saltAndPass[1]);
    }

    private static String hash(String password, byte[] salt) throws Exception {
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = f.generateSecret(new PBEKeySpec(
                password.toCharArray(), salt, iterations, desiredKeyLen)
        );
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
}
