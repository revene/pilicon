package com.pilicon.design_pattern.principle.openclose;

public class Test {
    public static void main(String[] args) {
        ICourse javaCourse = new JavaICourse(96,"Java从零到企业级电商开发",348d);
        System.out.println("课程Id: "+javaCourse.getId()+" 课程名称: "+javaCourse.getName()+" 课程价格: "+javaCourse.getPrice()+"元");
    }
}
