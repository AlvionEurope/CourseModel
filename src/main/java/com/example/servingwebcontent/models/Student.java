package com.example.servingwebcontent.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long num;
    private String name, adress, tel, email;

    @OneToMany
    @JoinColumn(name = "studMarks")
    public List<Progress> marks;



    public Student(Long num, String name, String adress, String tel, String email, List<Progress> marks) {
        this.num = num;
        this.name = name;
        this.adress = adress;
        this.tel = tel;
        this.email = email;
        this.marks = marks;
    }

    public Student() {
    }

    public List<Progress> getMarks() {
        return marks;
    }

    public void setMarks(List<Progress> marks) {
        this.marks = marks;
    }

    public Long getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getFinCourses(){
    List<String> finCourses = new ArrayList<>();
     int k=0;
        for(int i = 0;i<marks.size();i++){
            if(marks.get(i).finMark()>=70){
                k++;
            }}
        if(k>0){
            finCourses = new ArrayList<>(k);
            for(int i = 0;i<marks.size();i++){
                if(marks.get(i).finMark()>=70){
                     finCourses.add(i,marks.get(i).getCourse().getName());
        }}}
        if(k==0){
            finCourses = new ArrayList<>(1);
            finCourses.add(0,"Нет пройденных курсов");}
        return finCourses;
    }

}
