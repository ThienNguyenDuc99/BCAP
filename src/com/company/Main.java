package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    static int N; //
    static int M;
    static int a;
    static int b;
    static int[] listArrResult = new int[100];
    static Courses[] listCourses = new Courses[100];
    static Periods[] listPeriods = new Periods[100];
    static ArrayList<Integer> listMax = new ArrayList<Integer>();
    static int arr[][] = new int[100][100];
    static int fileinput[] = new int[1000];
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        /*System.out.print("Nhập số học kỳ (M): ");
        M = sc.nextInt();
        listArrResult = new int[M+1];
        System.out.print("Nhập số môn học (N): ");
        N = sc.nextInt();
        System.out.print("Nhập a: ");
        a = sc.nextInt();
        System.out.print("Nhập b: ");
        b = sc.nextInt();*/
        String url = "C:\\Users\\Admin\\Desktop\\New folder\\BACP\\file.txt";
        // Đọc dữ liệu từ File với Scanner
        FileInputStream fileInputStream = new FileInputStream(url);
        Scanner scanner = new Scanner(fileInputStream);
        try {
            int index = 0;
            while (scanner.hasNextLine()) {
                fileinput[index] = scanner.nextInt();
                index++;
            }
        }finally {

        }
        N = fileinput[0]; M = fileinput[1]; a = fileinput[2]; b= fileinput[3];
        for(int j = 4; j<4+N; j++){
            listCourses[j-3] = new Courses(j-3,fileinput[j],0);
            int k = 0;
        }
        for(int j=1; j<=N; j++){
            for(int i=1; i<=N; i++){
                int idex = (j-1)*N + i + (N+3);
                arr[j][i] = fileinput[idex];
                int x = 0;
            }
        }
        System.out.println("f");
        // Khởi tạo list periods
       /* for(int i=1; i<=M; i++){
            listPeriods[i] = new Periods(i);
        }
        // Khởi tạo list courses
        for(int i=1; i<=N; i++){
            int x = sc.nextInt();
            // Khởi tạo từng đối tượng Courses
            listCourses[i] = new Courses(i,x,0);
        }
        arr[3][4] = 1; arr[3][5] = 1; arr[1][6] = 1;*/
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                //int x = sc.nextInt();
                if(arr[i][j] == 1){
                    listCourses[i].ChildrenCourses(listCourses[j]);
                }
            }
        }
        FindArrResult(N, M, a, b, 1);
        int min = minLoad();
        System.out.println("Result: " + min);
    }

    // Tìm ra mảng đại diện cho số lượng các môn học của từng học kỳ
    public static void FindArrResult(int smh, int skh, int a, int b, int k){
        for(int i=a+1;i<b;i++){
            listArrResult[k]=i;
            if(skh<=1){
                if(smh==i) {
                    ArrayList<Periods> CopyPer = new ArrayList<Periods>(Arrays.asList(listPeriods));
                    // Tìm max load của từng trường hợp
                   // ArrayList<Courses> CopyCourses = new ArrayList<Courses>(Arrays.asList(listCourses));
                    Courses[] CopyCourses = new Courses[N+1];
                    int Bool[] = new int[N+1];
                    for(int j=1; j<=N; j++){
                        Bool[j] = 0;
                    }
                    int[] listArrResult1 = listArrResult;
                    for(int j=1; j<M+1; j++){
                        listArrResult1[j] = listArrResult1[j-1] + listArrResult1[j];
                    }
                    Try(CopyPer, CopyCourses, listArrResult1, Bool, 1);
                }
            }else{
                FindArrResult(smh - listArrResult[k],skh-1, a,b, k+1);
                //result(M-ki[k],N-1,a,b,k+1)
            }
        }
    }

    // Tìm ra các hoán vị của các môn học
    public static void Try(ArrayList<Periods> CopyPer, Courses[] CopyCourses, int[] listArrResult, int[] Bool, int k) {
        for (int i = 1; i <= N; i++) {
            if (Bool[i] == 0) {
                CopyCourses[k] = listCourses[i];
                Bool[i] = 1;
                if (k == N){
                    assignCourses(CopyPer, listArrResult, CopyCourses);
                    int z = 0;
                }
                else
                    Try(CopyPer, CopyCourses, listArrResult, Bool, k+1);
                Bool[i] = 0;
            }
        }
    }

    // Map mảng số lượng các môn học của từng học kỳ với các bộ hoán vị
    public static void assignCourses(ArrayList<Periods> CopyPer, int[] listArrResult, Courses[] CopyCourses){
        for(int i =1 ; i<=M; i++) {
            for (int j = 1; j <= N; j++) {
                if (j > listArrResult[i - 1] && j <= listArrResult[i]) {
                    CopyPer.get(i).AddCourses(CopyCourses[j]);
                }
            }
        }
        if(check(CopyPer)) {
            listMax.add(FindMaxLoad(CopyPer));
            Print(CopyPer);
        }
        for(int i =1 ; i<=M; i++) {
            CopyPer.get(i).GetCourses().clear();
        }
    }

    // Tìm học kỳ có số tín nhiều nhất
    public static int FindMaxLoad(ArrayList<Periods> periods){
        int max = 0;
        for(int i = 1; i<=M; i++){
            int c = periods.get(i).load();
            if(periods.get(i).load() > max) max = periods.get(i).load();
        }
        return max;
    }

    // In ra các trường hợp
    public static void Print(ArrayList<Periods> periods){
        for(int i = 1; i<=M; i++){
            Periods x = periods.get(i);
            int h = x.TotalCourses();
            for(int j=0; j<h; j++){
                int z = x.GetCourses().get(j).NumberCredit();
                int z1 = x.GetCourses().get(j).CoursesId();
                System.out.print(" "+ z + "(CoursesId: " + z1 + ")");
            }
            System.out.print("--->");
        }
        System.out.println("Max:" + FindMaxLoad(periods));
    }

    // Tìm min của tất cả các các max academy load
    public static int minLoad(){
        int min = listMax.get(0);
        for(int i=0; i<listMax.size(); i++){
            if(listMax.get(i)<min) min = listMax.get(i);
        }
        return min;
    }

    //Check sự ràng buộc về tín chỉ tiên quyết
    public static boolean check(ArrayList<Periods> periods){
        for(int i=1; i<=M; i++){
            for(int p = 0; p< periods.get(i).length(); p++) {
                int ParentId = periods.get(i).GetCourses().get(p).CoursesId();
                for (int j = 1; j <= i; j++) {
                    for (int k = 0; k < periods.get(j).length(); k++) {
                        int Id = periods.get(j).GetCourses().get(k).CoursesId();
                        int c = 9;
                        if (arr[ParentId][Id] == 1) return false;
                    }
                }
            }
        }
        return true;
    }

}
