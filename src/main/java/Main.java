import com.alibaba.fastjson.JSONObject;
import model.SMKeyPair;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author: Xusj
 * @Date: 2018/9/4
 * @Description:
 */
public class Main {
    //ip正则表达式
    private static Pattern NUMBER_PATTERN = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+\\:\\d+)");

    public void invoke() throws Exception {
        List<Integer> list = new ArrayList<>();

        list.add(12);
        //这里直接添加会报错
//        list.add("a");
        Class<? extends List> clazz = list.getClass();
        Method add = clazz.getDeclaredMethod("add", Object.class);
        //但是通过反射添加，是可以的
        add.invoke(list, "kl");

        System.out.println(list);
    }

    public static void main(String[] args) throws Exception {
//        LocalDateTime time = LocalDateTime.now().withDayOfMonth(10).withMonth(8).withHour(16).withMinute(35);
//        System.out.println(time + " 消失");
//        for (int i = 0; i < 20; i++) {
//            time = time.plusHours(5);
//            System.out.println(time + " 出现");
//            time = time.plusMinutes(5);
//            System.out.println(time + " 消失");
//        }

        JSONObject jsonObject = new JSONObject();

        SMKeyPair smKeyPair = new SMKeyPair();
        smKeyPair.setPubKey("04e59044f682310efbcf4e8936bed8b8de913b11db1d988d2edad9d39f60caa47f64caac234c2d23180e513cc1d16ed69f5555341848dd79ce64325337af2bee3d");
        smKeyPair.setPriKey("e1eb862dc370d8c5ff9bb1ae239c332a0fb3550026944287a1d581a1acc4c08d");
        jsonObject.put("sm2", smKeyPair);
        String a =  jsonObject.toString();
        String b =  jsonObject.toJSONString();
        System.out.println(a);
        System.out.println(b);

    }

}
