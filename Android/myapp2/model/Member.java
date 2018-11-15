package com.example.kitri.myapp2.model;

public class Member {
    private int id;
    private String name;
    private String tel;
    private int img_res;
    public Member(String name, String tel) {
        this.name = name;
        this.tel = tel;
    }

    public Member(int id, String name, String tel, int img_res) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.img_res = img_res;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member(){

    }

    public void setImg_res(int img_res) {
        this.img_res = img_res;
    }

    public int getImg_res() {

        return img_res;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    @Override
    public String toString() {
        return  "name=" + name +": tel=" + tel;
    }
}
