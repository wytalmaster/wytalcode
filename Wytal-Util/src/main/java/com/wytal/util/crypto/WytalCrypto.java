package com.wytal.util.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;

import com.wytal.logging.factory.WytalLoggingFactory;

public class WytalCrypto {
	private static final String WYTALKEYSTORE = "java:comp/env/encryptionKey";
    protected static final Logger logger = WytalLoggingFactory.getLogger(WytalLoggingFactory.UTIL_LOGGER);


    public void init() throws Exception {

        Context initialContext = null;
        try {
        	initialContext = new InitialContext();
            key= ((String)initialContext.lookup(WYTALKEYSTORE)).getBytes("UTF-8");
            ivspec = new IvParameterSpec(iv);
            //System.out.println("Secret key"+ new String(key));
            
        } catch(Exception ex){
        	ex.printStackTrace();
        	logger.error("",ex);
            logger.error("Error in loading the initial context",ex);
            throw ex;
        } finally {
            closeQuietly(initialContext);
        }
    }
    
    private static void closeQuietly(final Context context) {

        if (context == null) {
            return;
        }

        try {
            context.close();
        } catch (Exception ignored) {

        }
    }
    private IvParameterSpec ivspec  = null;
	private static final String ALGORITHM ="AES/CBC/PKCS5Padding" ;
	private byte[] key ;
	private static final byte[] iv ={ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	
	public byte[] enrypt(String data) throws InvalidKeyException,
											 NoSuchPaddingException,
											 NoSuchAlgorithmException,
											 BadPaddingException,
											 IllegalBlockSizeException,
											 InvalidAlgorithmParameterException{
		if(data==null) { return null;}
		Cipher encryptCipher = Cipher.getInstance(ALGORITHM);
	    final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
	    encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey,ivspec);
	    return Base64.encodeBase64(encryptCipher.doFinal(data.getBytes()));
	}
	
	
	public String decrypt(byte[] encryptData) throws InvalidKeyException,
													 NoSuchPaddingException,
													 NoSuchAlgorithmException,
													 BadPaddingException,
													 IllegalBlockSizeException,
													 InvalidAlgorithmParameterException{
		if(encryptData ==null) { return null;}
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey,ivspec);
        final String decryptedString = new String(cipher.doFinal(Base64.decodeBase64(encryptData)));
        return decryptedString;
    
	}
	
	

}
