package util;

import model.SMKeyPair;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.*;
import org.bouncycastle.crypto.signers.SM2Signer;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

/**
 * @Author: xusj
 * @Date: 2022/7/29 14:42
 * @Description: SM2 国密工具类
 */
public class SM2Util {


    /**
     * 生成SM2公私钥对
     * @return
     */
    private static AsymmetricCipherKeyPair genKeyPair0() {
        //获取一条SM2曲线参数
        X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");

        //构造domain参数
        ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(),
                sm2ECParameters.getG(), sm2ECParameters.getN());

        //1.创建密钥生成器
        ECKeyPairGenerator keyPairGenerator = new ECKeyPairGenerator();

        //2.初始化生成器,带上随机数
        try {
            keyPairGenerator.init(new ECKeyGenerationParameters(domainParameters, SecureRandom.getInstance("SHA1PRNG")));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("生成公私钥对时出现异常:" + e);
//            e.printStackTrace();
        }

        //3.生成密钥对
        AsymmetricCipherKeyPair asymmetricCipherKeyPair = keyPairGenerator.generateKeyPair();
        return asymmetricCipherKeyPair;
    }

    /**
     * 生成公私钥对(默认压缩公钥)
     * @return
     */
    public static SMKeyPair genKeyPair() {
        return genKeyPair(true);
    }

    /**
     * 生成公私钥对
     * @param compressedPubKey  是否压缩公钥
     * @return
     */
    public static SMKeyPair genKeyPair(boolean compressedPubKey) {
        AsymmetricCipherKeyPair asymmetricCipherKeyPair = genKeyPair0();

        //提取公钥点
        ECPoint ecPoint = ((ECPublicKeyParameters) asymmetricCipherKeyPair.getPublic()).getQ();
        //公钥前面的02或者03表示是压缩公钥,04表示未压缩公钥,04的时候,可以去掉前面的04
        String pubKey = Hex.toHexString(ecPoint.getEncoded(compressedPubKey));

        BigInteger privatekey = ((ECPrivateKeyParameters) asymmetricCipherKeyPair.getPrivate()).getD();
        String priKey = privatekey.toString(16);

        SMKeyPair keyPair = new SMKeyPair(priKey, pubKey);
        return keyPair;
    }

    /**
     * 私钥签名
     * @param privateKey    私钥
     * @param content       待签名内容
     * @return
     */
    public static String sign(String privateKey, String content) throws CryptoException {
        //待签名内容转为字节数组
        byte[] message = Hex.decode(content);

        //获取一条SM2曲线参数
        X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
        //构造domain参数
        ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(),
                sm2ECParameters.getG(), sm2ECParameters.getN());

        BigInteger privateKeyD = new BigInteger(privateKey, 16);
        ECPrivateKeyParameters privateKeyParameters = new ECPrivateKeyParameters(privateKeyD, domainParameters);

        //创建签名实例
        SM2Signer sm2Signer = new SM2Signer();

        //初始化签名实例,带上ID,国密的要求,ID默认值:zxkjsm2
        try {
            sm2Signer.init(true, new ParametersWithID(new ParametersWithRandom(privateKeyParameters, SecureRandom.getInstance("SHA1PRNG")), Strings.toByteArray("zxkjsm2")));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("签名时出现异常:" + e);
        }
        sm2Signer.update(message, 0, message.length);
        //生成签名,签名分为两部分r和s,分别对应索引0和1的数组
        byte[] signBytes = sm2Signer.generateSignature();

        String sign = Hex.toHexString(signBytes);

        return sign;
    }

    /**
     * 将R或者S修正为固定字节数
     * @param rs
     * @return
     */
    private static byte[] modifyRSFixedBytes(byte[] rs) {
        int length = rs.length;
        int fixedLength = 32;
        byte[] result = new byte[fixedLength];
        if (length < 32) {
            System.arraycopy(rs, 0, result, fixedLength - length, length);
        } else {
            System.arraycopy(rs, length - fixedLength, result, 0, fixedLength);
        }
        return result;
    }

    /**
     * 验证签名
     * @param publicKey     公钥
     * @param content       待签名内容
     * @param sign          签名值
     * @return
     */
    public static boolean verify(String publicKey, String content, String sign) {
        //待签名内容
        byte[] message = Hex.decode(content);
        byte[] signData = Hex.decode(sign);

        // 获取一条SM2曲线参数
        X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
        // 构造domain参数
        ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(),
                sm2ECParameters.getG(),
                sm2ECParameters.getN());
        //提取公钥点
        ECPoint pukPoint = sm2ECParameters.getCurve().decodePoint(Hex.decode(publicKey));
        // 公钥前面的02或者03表示是压缩公钥，04表示未压缩公钥, 04的时候，可以去掉前面的04
        ECPublicKeyParameters publicKeyParameters = new ECPublicKeyParameters(pukPoint, domainParameters);
        //创建签名实例
        SM2Signer sm2Signer = new SM2Signer();
        ParametersWithID parametersWithID = new ParametersWithID(publicKeyParameters, Strings.toByteArray("zxkjsm2"));
        sm2Signer.init(false, parametersWithID);
        sm2Signer.update(message, 0, message.length);
        //验证签名结果
        boolean verify = sm2Signer.verifySignature(signData);
        return verify;
    }

    /**
     * SM2加密算法
     * @param publicKey     公钥
     * @param data          数据
     * @return
     */
    public static String encrypt(String publicKey, String data){
        // 获取一条SM2曲线参数
        X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
        // 构造domain参数
        ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(),
                sm2ECParameters.getG(),
                sm2ECParameters.getN());
        //提取公钥点
        ECPoint pukPoint = sm2ECParameters.getCurve().decodePoint(Hex.decode(publicKey));
        // 公钥前面的02或者03表示是压缩公钥，04表示未压缩公钥, 04的时候，可以去掉前面的04
        ECPublicKeyParameters publicKeyParameters = new ECPublicKeyParameters(pukPoint, domainParameters);

        SM2Engine sm2Engine = new SM2Engine();
        sm2Engine.init(true, new ParametersWithRandom(publicKeyParameters, new SecureRandom()));

        byte[] arrayOfBytes = null;
        try {
            byte[] in = data.getBytes("utf-8");
            arrayOfBytes = sm2Engine.processBlock(in, 0, in.length);
        } catch (Exception e) {
            System.out.println("SM2加密时出现异常:" + e);
        }
        return Hex.toHexString(arrayOfBytes);
    }

    /**
     * SM2加密算法
     * @param publicKey     公钥
     * @param data          明文数据
     * @return
     */
    public static String encrypt(PublicKey publicKey, String data) {

        ECPublicKeyParameters ecPublicKeyParameters = null;
        if (publicKey instanceof BCECPublicKey) {
            BCECPublicKey bcecPublicKey = (BCECPublicKey) publicKey;
            ECParameterSpec ecParameterSpec = bcecPublicKey.getParameters();
            ECDomainParameters ecDomainParameters = new ECDomainParameters(ecParameterSpec.getCurve(),
                    ecParameterSpec.getG(), ecParameterSpec.getN());
            ecPublicKeyParameters = new ECPublicKeyParameters(bcecPublicKey.getQ(), ecDomainParameters);
        }

        SM2Engine sm2Engine = new SM2Engine();
        sm2Engine.init(true, new ParametersWithRandom(ecPublicKeyParameters, new SecureRandom()));
        byte[] arrayOfBytes = null;
        try {
            byte[] in = data.getBytes("utf-8");
            arrayOfBytes = sm2Engine.processBlock(in,0, in.length);
        } catch (Exception e) {
            System.out.println("SM2加密时出现异常:" + e);
        }
        return Hex.toHexString(arrayOfBytes);
    }

    /**
     * SM2解密算法
     * @param privateKey    私钥
     * @param cipherData    密文数据
     * @return
     */
    public static String decrypt(String privateKey, String cipherData) {
        byte[] cipherDataByte = Hex.decode(cipherData);

        //获取一条SM2曲线参数
        X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
        //构造domain参数
        ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(),
                sm2ECParameters.getG(), sm2ECParameters.getN());

        BigInteger privateKeyD = new BigInteger(privateKey, 16);
        ECPrivateKeyParameters privateKeyParameters = new ECPrivateKeyParameters(privateKeyD, domainParameters);

        SM2Engine sm2Engine = new SM2Engine();
        sm2Engine.init(false, privateKeyParameters);

        String result = null;
        try {
            byte[] arrayOfBytes = sm2Engine.processBlock(cipherDataByte, 0, cipherDataByte.length);
            return new String(arrayOfBytes, "utf-8");
        } catch (Exception e) {
            System.out.println("SM2解密时出现异常:" + e);
        }
        return result;

    }

    /**
     * SM2解密算法
     * @param privateKey        私钥
     * @param cipherData        密文数据
     * @return
     */
    public static String decrypt(PrivateKey privateKey, String cipherData) {
        byte[] cipherDataByte = Hex.decode(cipherData);

        BCECPrivateKey bcecPrivateKey = (BCECPrivateKey) privateKey;
        ECParameterSpec ecParameterSpec = bcecPrivateKey.getParameters();

        ECDomainParameters ecDomainParameters = new ECDomainParameters(ecParameterSpec.getCurve(),
                ecParameterSpec.getG(), ecParameterSpec.getN());

        ECPrivateKeyParameters ecPrivateKeyParameters = new ECPrivateKeyParameters(bcecPrivateKey.getD(),
                ecDomainParameters);

        SM2Engine sm2Engine = new SM2Engine();
        sm2Engine.init(false, ecPrivateKeyParameters);

        String result = null;
        try {
            byte[] arrayOfBytes = sm2Engine.processBlock(cipherDataByte, 0, cipherDataByte.length);
            return new String(arrayOfBytes, "utf-8");
        } catch (Exception e) {
            System.out.println("SM2解密时出现异常:" + e);
        }
        return result;
    }

    /**
     * 将未压缩公钥压缩成压缩公钥
     * 公钥前面的02或者03表示是压缩公钥，04表示未压缩公钥, 04的时候，可以去掉前面的04
     * @param pubKey    未压缩公钥(16进制,不要带头部04)
     * @return
     */
    public static String compressPubKey(String pubKey) {
        pubKey = "04" + pubKey;    //将未压缩公钥加上未压缩标识.
        // 获取一条SM2曲线参数
        X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
        //提取公钥点
        ECPoint pukPoint = sm2ECParameters.getCurve().decodePoint(Hex.decode(pubKey));
        String compressPubKey = Hex.toHexString(pukPoint.getEncoded(Boolean.TRUE));

        return compressPubKey;
    }

    /**
     * 将压缩的公钥解压为非压缩公钥
     * @param compressKey   压缩公钥
     * @return
     */
    public static String unCompressPubKey(String compressKey) {
        // 获取一条SM2曲线参数
        X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
        //提取公钥点
        ECPoint pukPoint = sm2ECParameters.getCurve().decodePoint(Hex.decode(compressKey));
        String pubKey = Hex.toHexString(pukPoint.getEncoded(Boolean.FALSE));
        //去掉前面的04   (04的时候，可以去掉前面的04)
        pubKey = pubKey.substring(2);
        return pubKey;
    }

    public static void main(String[] args) {
        //生成公秘钥
//        SMKeyPair smKeyPair = genKeyPair(false);
//        String priKey = smKeyPair.getPriKey();
        String priKey = "e1eb862dc370d8c5ff9bb1ae239c332a0fb3550026944287a1d581a1acc4c08d";
        System.out.println("私钥："+ priKey);
//        String pubKey = smKeyPair.getPubKey();
        String pubKey = "04e59044f682310efbcf4e8936bed8b8de913b11db1d988d2edad9d39f60caa47f64caac234c2d23180e513cc1d16ed69f5555341848dd79ce64325337af2bee3d";
        System.out.println("公钥："+ pubKey);
        //公钥解压缩
//        String substring = pubKey.substring(2, pubKey.length());
//        String s = compressPubKey(substring);
//        System.out.println("压缩后" + s);
//        String s1 = unCompressPubKey(s);
//        System.out.println("解压后" + s1);
//        //明文
//        String text = "zxkj-digital-ca";
//        System.out.println("测试明文文本:" + text);
//        //签名验签测试
//        String sign = "";
//        try {
//            sign = sign(priKey, Hex.toHexString(text.getBytes()));
//        } catch (CryptoException e) {
//            e.printStackTrace();
//        }
//        System.out.println("生成签名" + sign);
//        boolean verify = verify(pubKey, Hex.toHexString(text.getBytes()), sign);
//        System.out.println("验签结果" + verify);

        //加解密测试
//        String encryptData = encrypt(pubKey, text);
        String encryptData = "047fba5c3f2be2b8dcacf1e97b8a696b0366fc3546357fb19c7612d7326e360470661c3d689173803d29b04d185f61dda5c23c1621643ef19ba4f9bd1269d673c0861552b5ba2c77829d31437dd2c9ee6faf0c3c44608110e8f8d99864def42bfbf95ab4a45616c1d77a367bc9eb1367c6a1f042d667e7e11be5b468beb753845179996a4039390f630082dc6d1d4fd78a67e584525ea24e48f9bdf25419cc4071142410d4e59ba59555b7daccc5d9db3f4111eca0d333adbac06c8aefcd62448c0994911046d62eb669b12a2af539dcb0b8650a6b136312b8aec0058a756251e9887348a224255e2d3623c8983ebef2dcf3a38b0db54befa2d5aa44f3f8aa40116febfacda3646e639aca87a4c23c19f3250cb0313893c353183ea37575cdc6f51152c0ec25935daabd3c9532ee9c2233e96af3f6a9622a76db0e55e38e35a78dced0dc6848d251a7538c5488f093bf7331faa5eb7edca6b1c460b4dee4975c780a25396bd9e0723ed71ea2f019c5e09c82d48b1108b34730f10d9a990829e4488f754557544bcaede0ba83dfa7cc009fcd252f320eca7891ba0b5533c97324113db6a1dbc18ae43f34ccbced462be8c2b66232e9bb19245a101eb45a2eb121a5ad49401c47df0e3f9ad95ff57665a3b35dab45c6f4f293551402f831384e8680419a199283";
        System.out.println("加密结果：" + encryptData);
        String decryptData = decrypt(priKey, encryptData);
        System.out.println("解密结果：" + decryptData);

//        //前端加密的数据 需要添加04前缀
////        String disContent = "04" + "3f09da39b0e9926dfaf7f0d4aa6b78983555f50300f9d67a7f41375c518841ed117fee4a8d784fe497e3378dfb53077b3e77f4f2d73f0fdaf549165a4ba433ff0b366dc1a30cca302c915edbe59ffd7c774288bdd91519904a8442418c4b21bc7d9077";
////        String disContent = "042eaebe2a9637afda5a6812d1a82d0b715ee7d1804855e00efe22893a910d76a14b196af0bd69b481fbd22799927b308020400d0ee2b598a8bfcb0c1cee04965af3a04305546aa25ae0868aabbc704c6d42bc65d180779143db8603e54062fa8730740bc01fa238cc476b77868153fa508ae6e6786e3eddd53d66baba23d2c11bbe8523be0046a81886c4a1d3c2cdf82f54952dce813b8e0e3800252c61eb03720294d3aec371405de4fc";
//        System.out.println("加密数据:" + disContent);
//        String des = decrypt(priKey, disContent);
//        System.out.println("解密结果:" + des);
    }
}
