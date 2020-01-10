import java.util.List;

/**
 * @Author: Xusj
 * @Date: 2019/6/20
 * @Description:
 */
public class ArrayList {
    /**
     * 处理数据
     * @return
     */
    private List<Integer> processData() {
        List<Integer> resList = new java.util.ArrayList<>();
        for (int i = 0; i <= 2000; i++) {
            resList.add(i);
        }
        return resList;
    }

    public static void main(String[] args) {
        List<Integer> resList = new java.util.ArrayList<>();
        List<Integer> list = new ArrayList().processData();
        for(Integer res : list){
            resList.add(res+1);
        }
        System.out.println(111);
    }
}
