package model.enums;

/**
 * @Author: xusj
 * @Date: 2022/1/26 17:01
 * @Description:
 */
public enum Stage {
    PERMISSION,  // 工作许可
    PRESENTATION,  // 安全交底
    EXECUTE,  // 工作执行
    TERMINATOR;  // 验收终结

    //根据name匹配枚举
    public static Stage find(String name) {
        for (Stage stageState : Stage.values()) {
            if (stageState.toString().equals(name)) {
                return stageState;
            }
        }
        return null;
    }
}
