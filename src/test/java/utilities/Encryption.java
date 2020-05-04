package utilities;


import org.apache.commons.codec.binary.Base64;

public class Encryption {

    /**
     * Method is to encrypt the password
     */
    public static String encryptPassword(String password){
        byte[] encodedPwdBytes = Base64.encodeBase64(password.getBytes());
        String decodedPwd = new String(encodedPwdBytes);
        return decodedPwd;
    }

    /**
     * Method is to decrypt the password
     */
    public static String decryptPassword(String password){
        byte[] decodedPwdBytes = Base64.decodeBase64(password.getBytes());
        String decodedPwd = new String(decodedPwdBytes);
        return decodedPwd;
    }
}
