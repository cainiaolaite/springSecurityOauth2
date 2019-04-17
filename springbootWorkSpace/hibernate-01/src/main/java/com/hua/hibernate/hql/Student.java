package com.hua.hibernate.hql;

/**
 * 学生
 */
public class Student {
    private int id;
    private String name;
    private String sex="女";
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
