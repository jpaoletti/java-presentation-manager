package jpaoletti.jpm.util;

import com.sun.crypto.provider.SunJCE;
import java.net.URLDecoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEParameterSpec;
import org.apache.commons.codec.binary.Base64;

/**
 * An easy to use class to encrypt and decrypt a string. Just call the simplest
 * constructor and the needed methods.
 *
 * @author jpaoletti
 * @since 11/01/2012
 * @version 1.2.0
 *
 */
public class StringEncrypter {

    private Cipher encryptCipher;
    private Cipher decryptCipher;
    private Base64 codec = new Base64(76, null, true);
    final private String charset = "UTF-8";
    final private String defaultEncryptionPassword = "PAOSIDUFHQWER98234QWE378AHASDF93HASDF9238HAJSDF923";
    final private byte[] defaultSalt = {
        (byte) 0xa3, (byte) 0x21, (byte) 0x24, (byte) 0x2c,
        (byte) 0xf2, (byte) 0xd2, (byte) 0x3e, (byte) 0x19};

    /**
     * The simplest constructor which will use a default password and salt to
     * encode the string.
     * 
     * @throws SecurityException
     */
    public StringEncrypter() throws SecurityException {
        setupEncryptor(defaultEncryptionPassword, defaultSalt);
    }

    /**
     * Dynamic constructor to give own key and salt to it which going to be used
     * to encrypt and then decrypt the given string.
     * 
     * @param encryptionPassword
     * @param salt
     */
    public StringEncrypter(String encryptionPassword, byte[] salt) {
        setupEncryptor(encryptionPassword, salt);
    }

    /**
     * Dynamic constructor to give own key and use default salt to it which 
     * going to be used to encrypt and then decrypt the given string.
     * 
     * @param encryptionPassword
     */
    public StringEncrypter(String encryptionPassword) {
        setupEncryptor(encryptionPassword, defaultSalt);
    }

    public void init(char[] pass, byte[] salt, int iterations) throws SecurityException {
        try {
            PBEParameterSpec ps = new javax.crypto.spec.PBEParameterSpec(salt, 20);
            SecretKeyFactory kf = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey k = kf.generateSecret(new javax.crypto.spec.PBEKeySpec(pass));
            encryptCipher = Cipher.getInstance("PBEWithMD5AndDES/CBC/PKCS5Padding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, k, ps);
            decryptCipher = Cipher.getInstance("PBEWithMD5AndDES/CBC/PKCS5Padding");
            decryptCipher.init(Cipher.DECRYPT_MODE, k, ps);
        } catch (Exception e) {
            throw new SecurityException("Could not initialize CryptoLibrary: " + e.getMessage());
        }
    }

    /**
     * 
     * method to decrypt a string.
     * 
     * @param str
     *            Description of the Parameter
     * 
     * @return String the encrypted string.
     * 
     * @exception SecurityException
     *                Description of the Exception
     */
    public synchronized String encrypt(String str) throws SecurityException {
        try {
            byte[] utf8 = str.getBytes(charset);
            byte[] enc = encryptCipher.doFinal(utf8);
            return codec.encodeAsString(enc);
        } catch (Exception e) {
            throw new SecurityException("Could not encrypt: " + e.getMessage());
        }
    }

    /**
     * 
     * Method to encrypting a string.
     * 
     * @param str String to encript
     * 
     * @return String the encrypted string.
     * 
     * @exception SecurityException
     *                Description of the Exception
     */
    public synchronized String decrypt(String str) throws SecurityException {
        try {
            byte[] dec = codec.decode(URLDecoder.decode(str, charset));
            byte[] utf8 = decryptCipher.doFinal(dec);
            return new String(utf8, charset);
        } catch (Exception e) {
            throw new SecurityException("Could not decrypt: " + e.getMessage());
        }
    }

    private void setupEncryptor(String defaultEncryptionPassword, byte[] salt) {
        java.security.Security.addProvider(new SunJCE());
        char[] pass = defaultEncryptionPassword.toCharArray();
        int iterations = 3;
        init(pass, salt, iterations);
    }
}
