package com.hua.hibernate.retrieval.join;

import java.util.Set;

public class Teachar {
    private int id;
    private String name;
    private Set<Student> studentSet;

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

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

    @Override
    public String toString() {
        return "Teachar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
