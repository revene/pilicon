package com.pilicon.design_pattern.principle.dependenceinversion;

public class Test {
    public static void main(String[] args) {
        /**v1**/
//        Geely geely = new Geely();
//        geely.studyJavaCourse();
//        geely.studyFECourse();

        /**v2**/
//        Geely geely = new Geely();
//        geely.studyImoocCourse(new JavaCourse());
//        geely.studyImoocCourse(new FECoures());

        /**v3 构造器注入**/
//        Geely geely = new Geely(new JavaCourse());
//        geely.studyImoocCourse();

        /**v4 set方法注入**/
        Geely geely = new Geely();
        geely.setiCourse(new JavaCourse());
        geely.studyImoocCourse();
    }
}
