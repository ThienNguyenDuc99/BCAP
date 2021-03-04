package com.company;

public class Courses {
    private int CoursesId;
    private int NumberCredit;
    private int Tick;
    private int Periods;
    private Courses ParentCourses;
    private Courses ChildrenCourses;
    public int Tick(){
        return this.Tick;
    }
    public void Tick(int a){
        this.Tick = a;
    }
    public Courses(int a, int b, int c){
        this.CoursesId = a;
        this.NumberCredit = b;
        this.Tick = c;
    }
    public int CoursesId(){
        return this.CoursesId;
    }
    public int NumberCredit(){
        return this.NumberCredit;
    }
    public void CoursesId(int CoursesId) {
        this.CoursesId = CoursesId;
    }
    public void NumberCredit(int NumberCredit) {
        this.NumberCredit = NumberCredit;
    }
    public Courses ParentCourses(){
        return this.ParentCourses;
    }
    public Courses ChildrenCourses(){
        return this.ChildrenCourses;
    }
    public void ParentCourses(Courses ParentCourses) {
        this.ParentCourses = ParentCourses;
    }
    public void ChildrenCourses(Courses ChildrenCourses) {
        this.ChildrenCourses = ChildrenCourses;
    }
}
