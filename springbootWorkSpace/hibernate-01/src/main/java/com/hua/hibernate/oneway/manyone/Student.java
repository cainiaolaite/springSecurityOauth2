package com.hua.hibernate.oneway.manyone;

/**
 * 学生
 */
public class Student {
    private int id;
    private String name;
    private StudentInfo studentInfo;

    private Teachar teachar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teachar getTeachar() {
        return teachar;
    }

    public void setTeachar(Teachar teachar) {
        this.teachar = teachar;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teachar=" + teachar +
                '}';
    }
}
