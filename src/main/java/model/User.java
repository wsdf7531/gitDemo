package model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

    private List<Strategy> strategyList;
}
