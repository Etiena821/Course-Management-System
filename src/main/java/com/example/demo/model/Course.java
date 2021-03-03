package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course extends BaseModel{

    private String courseName;
    private String lecturer;
    private String courseLevel;

    @ManyToOne
    private Student student;

}
