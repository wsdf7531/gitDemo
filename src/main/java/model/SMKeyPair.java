package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: xusj
 * @Date: 2022/7/29 14:38
 * @Description: SM2 国密加解密demo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SMKeyPair {
    //私钥
    private String priKey;
    //公钥
    private String pubKey;
}
