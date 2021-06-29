package lambda;

import lombok.val;
import model.Student;

/**
 * @Author:xusj
 * @Date:2021/6/1
 * @Description:
 */
public class LambdaDemo {

    public void sort(Student a, Student b){
        final Integer age;
        age = Student::getAge;

    }

}
