package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data()
@AllArgsConstructor()
@NoArgsConstructor()
@Entity(name = "students")
public class Student {

        @Id
        @GeneratedValue
        //@GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String firstName;
        private String lastName;
        //private Integer age;
        @Enumerated(EnumType.STRING)

        @OneToMany
        private List<Course> course = new ArrayList<>();

}
