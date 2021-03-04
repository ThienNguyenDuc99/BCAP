package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Periods {
    private int PeriodsId;
    private ArrayList<Courses> ListCourses;
    public Periods(int a){
        this.PeriodsId = a;
        ListCourses = new ArrayList<>();
    }
    public int PeriodsId(){
        return this.PeriodsId;
    }
    public void PeriodsId(int PeriodsId) {
        this.PeriodsId = PeriodsId;
    }
    public int TotalCourses(){
        return ListCourses.size();
    }
    public int TotalCredit(){
        int result = 0;
        for(int i=0; i<ListCourses.size(); i++){
            result = result + ListCourses.get(i).NumberCredit();
        }
        return result;
    }
    public void AddCourses(Courses courses){
        ListCourses.add(courses);
    }
    public ArrayList<Courses> GetCourses(){
        return ListCourses;
    }
    // độ dài của Periods
    public int length(){
        return ListCourses.size();
    }
    public int load(){
        int load = 0;
        for(int i=0; i<ListCourses.size(); i++){
            load = load + ListCourses.get(i).NumberCredit();
        }
        return load;
    }
}
