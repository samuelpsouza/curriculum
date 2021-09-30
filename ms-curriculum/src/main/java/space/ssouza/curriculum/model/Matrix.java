package space.ssouza.curriculum.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Matrix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> courseList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<Semester> semesterList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public List<Course> getCourseList() {
        return courseList;
    }
}
