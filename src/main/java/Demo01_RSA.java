import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class Demo01_RSA {
    private static Map<Integer, String> keyMap = new HashMap<Integer, String>();

    public static void main(String[] args) throws Exception {
        genKeyPair();////生成公钥和私钥
        String message = "这是一段需要加密的原始数据内容信息，恭喜你拿到原始数据。";
        System.out.println("加密前信息内容："+message);
        System.out.println("随机性生成私钥：" + keyMap.get(0));
        System.out.println("随机性生成公钥：" + keyMap.get(1));
        String messageEncrypt = encrypt(message, keyMap.get(1));// 公钥加密
        System.out.println("加密后信息内容："+messageEncrypt);

        String messageDecrypt = decrypt(messageEncrypt, keyMap.get(0));// 私钥解密

        System.out.println("解密后信息内容:"+messageDecrypt);

    }




    ////生成公钥和私钥

    /**
     * 获取钥匙对
     *
     * @throws NoSuchAlgorithmException
     */
    private static void genKeyPair() throws NoSuchAlgorithmException {
        //基于RSA算法生成 公钥、私钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        //初始化密钥生成器，密钥大小为96-1024位
        keyPairGenerator.initialize(1024, new SecureRandom());
        //生成一个密钥对，保存在KeyPair中
        KeyPair keypair = keyPairGenerator.generateKeyPair();
        //获取私钥
        PrivateKey privatekey = keypair.getPrivate();
        //获取公钥
        PublicKey publickey = keypair.getPublic();

        //获取私钥字符串
        String privatekeystring = new String(Base64.encodeBase64(privatekey.getEncoded()));
        //获取公钥字符串
        String publickeystring = new String(Base64.encodeBase64(publickey.getEncoded()));

        keyMap.put(0, privatekeystring);
        keyMap.put(1, publickeystring);

    }


    /**RSA公钥加密
     * @param str
     * @param publicKey
     * @return
     */
    private static String encrypt(String message, String publicKey) throws Exception {
        //Base64的编码公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        PublicKey publick = (PublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publick);
        String outStr = Base64.encodeBase64String(cipher.doFinal(message.getBytes("UTF-8")));
        return outStr;

    }
    /** RSA私钥解密
     * @param str
     * @param privateKey
     * @return
     */
    private static String decrypt(String messageEncrypt, String privateKey) throws Exception {

        //64位加密后的字符串
        byte[] inputBytes = Base64.decodeBase64(messageEncrypt.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        PrivateKey privatek = (PrivateKey)KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privatek);
        String outStr = new String(cipher.doFinal(inputBytes));
        return outStr;

    }


}
