package bo;

import lombok.Data;
import model.Student;

import java.util.List;

/**
 * @Author: Xusj
 * @Date: 2020/7/13
 * @Description:
 */
@Data
public class StudentBo {
    private Integer floor;

    private List<Student> studentList;
}
