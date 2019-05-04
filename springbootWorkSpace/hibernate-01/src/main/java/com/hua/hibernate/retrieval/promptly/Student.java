package com.hua.hibernate.retrieval.promptly;

/**
 * 学生
 */
public class Student {
    private int id;
    private String name;

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

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teachar=" + teachar +
                '}';
    }
}
