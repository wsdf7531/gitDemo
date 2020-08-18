package model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: Xusj
 * @Date: 2018/11/12
 * @Description:
 */
@Data
public class User {
    private String name;
    private Integer age;
    private Integer source;
    private String sex;
    private BigDecimal power;

    private List<Strategy> strategyList;

    private ManageStatus manageStatus;

    public enum ManageStatus {
        Enable, Disable;

        public static ManageStatus find(int code) {
            for (ManageStatus stauts : ManageStatus.values()) {
                if (stauts.ordinal() == code) {
                    return stauts;
                }
            }
            return null;
        }

    }

}
