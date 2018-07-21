package com.beiyou.pojo;


public class Student {

    private  long id;
    private String stu_name;
    private String stu_sex;
    private long stu_seat;
    private String stu_desc;

    @Override
    public String toString() {
        return "StudentInfo{" +
                "id=" + id +
                ", stu_name='" + stu_name + '\'' +
                ", stu_sex='" + stu_sex + '\'' +
                ", stu_seat=" + stu_seat +
                ", stu_esc='" + stu_desc + '\'' +
                '}';
    }

    public  long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_sex() {
        return stu_sex;
    }

    public void setStu_sex(String stu_sex) {
        this.stu_sex = stu_sex;
    }

    public long getStu_seat() {
        return stu_seat;
    }

    public void setStu_seat(long stu_seat) {
        this.stu_seat = stu_seat;
    }

    public String getStu_desc() {
        return stu_desc;
    }

    public void setStu_desc(String stu_desc) {
        this.stu_desc = stu_desc;
    }
}
