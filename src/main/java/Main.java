import model.User;
import org.apache.commons.lang3.StringUtils;

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

        List<User> a  = new ArrayList<>();
        User usera = new User();
        User userb = new User();
        User userc = new User();
        usera.setName("qt4000");
        userb.setName("qt3550");
        userc.setName("bj4222");
        a.add(usera);
        a.add(userb);
        a.add(userc);

        for (User user : a){
            if (!StringUtils.isEmpty(user.getName())){
                String subChannelNumber = user.getName().substring(2);
                user.setName(subChannelNumber);
            }
        }
        System.out.println(a);


    }

}
