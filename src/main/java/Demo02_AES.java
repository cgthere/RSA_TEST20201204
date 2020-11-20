import org.apache.commons.codec.binary.Base64;
import org.testng.annotations.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.OperationNotSupportedException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

public class Demo02_AES {
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final String derectory = "G:\\公司";
    private static File file1;
    private static   String str = null;
    @Test
    public  void test() throws Exception {
        //生成密钥
        try {
            //获取密钥
            byte[] key = Demo02_AES.initKey();
            System.out.println("生成的密钥key的值："+key.toString());
            //对密钥进行加密
            str = Base64.encodeBase64String(key);
            System.out.println("生成的密钥："+str);
            File file = new File(derectory + File.separator  + "KKKK.txt");
            //对文件进行加密
            encryptfile(file, str);
            Thread.sleep(10000);
            //对文件进行解密
            decriptfile(file1, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //生成密钥
    public static byte[] initKey() throws  Exception {
        //实例化
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        //设置密钥长度
        kgen.init(128);
        //生成密钥
        SecretKey skey = kgen.generateKey();
        return skey.getEncoded();
    }

    //文件加密
    private void encryptfile(File file, String key) throws Exception {
        //调用文件处理的方法
        doFile(ZERO, file, key);
    }

    private void decriptfile(File file, String key) throws Exception {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        byte[] bytes = new byte[1024];
        int read = bufferedInputStream.read(bytes, 0, (int) file.length());
        System.out.println("加密后的文件名：" + file.getName());
        System.out.println("加密后文件内容：" + (new String(bytes)));
        System.out.println("加密后文件路徑：" + file.getAbsolutePath());
        doFile(ONE, file, key);
    }

/**
 * 文件处理方法
 * code为加密或者解密的判断条件
 * key 加密密钥
 */
    private void doFile(int code, File file, String key) throws  Exception {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        byte[] bytIn = new byte[(int) file.length()];

        int read = bis.read(bytIn);
        if (code == ZERO) {
            System.out.println("加密前文件内容："+new String(bytIn));
            System.out.println("加密前文件名称:"+file.getName());
            System.out.println("加密前文件路径："+file.getAbsolutePath());
        }
        bis.close();

        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        keygen.init(128, new SecureRandom(key.getBytes()));

        SecretKey secretKey = keygen.generateKey();
        byte[] raw = secretKey.getEncoded();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        switch (code) {
            case 0:{cipher.init(Cipher.ENCRYPT_MODE,skeySpec); }break;
            case 1:cipher.init(Cipher.DECRYPT_MODE,skeySpec);break;
            default:
                System.out.println("数据异常");break;
        }
        // 写文件

        byte[] bytOut = cipher.doFinal(bytIn);
        File  path = new File(derectory + File.separator + "AES测试文件夹");
        if (!path.exists()) {
            path.mkdirs();
        }
        file1 = new File(path+File.separator+ "AES测试文件夹"+new Date().getTime() + "." + file.getName().split("\\.")[1]);

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file1));

        bos.write(bytOut);
        if (code == ONE) {
            System.out.println("解密文件的内容：" + new String(bytOut));
            System.out.println("解密文件的名称:"+file1.getName());
            System.out.println("解密文件的路径："+file1);

        }
        bos.close();

    }


}
