package model;

import lombok.Data;

/**
 * @Author: Xusj
 * @Date: 2020/6/8
 * @Description: 学生类
 */
@Data
public class Student {
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 班级
     */
    private String classes;

    private Long groupId;
}
