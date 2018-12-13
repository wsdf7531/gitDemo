package model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: Xusj
 * @Date: 2018/11/12
 * @Description:
 */
@Data
public class User {
    private String name;
    private Integer age;
    private String sex;
    private BigDecimal power;


}
