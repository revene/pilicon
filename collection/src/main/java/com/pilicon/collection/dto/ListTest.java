package com.pilicon.collection.dto;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class ListTest {

    public List courseToSelect ;

    public ListTest() {
        this.courseToSelect = new ArrayList();
    }

    public void testAdd(){
        Course course = new Course("1","数据结构");
        courseToSelect.add(course);
        log.info("添加了课程:{}",course.getName());

        Course course1 = new Course("2","C语言");
        //在序列头添加,会把数据结构挤下去到1
        courseToSelect.add(0,course1);
        log.info("添加了课程:{}",course1.getName());

        Course course2 = new Course("3","傻逼前端");
        //现在可以访问的元素只有下标 0 , 1 , 所以下面这个方法会抛出indexOutOfBound异常
//      courseToSelect.add(4,course2);
//      log.info("添加了课程:{}",course2.getName());

        Course[] courses = {new Course("3","离散数学"),new Course("4","汇编语言")};
        courseToSelect.addAll(Arrays.asList(courses));
        /** courseToselect是一个没有泛型指定的ArrayList,所以其中的元素都被认为是Object,所以要强转*/
        log.info("添加了两门课程{},{}",((Course)courseToSelect.get(2)).getName(),((Course)courseToSelect.get(3)).getName());

    }

    /**
     * 取得list中元素的方法
     */
    public void testGet(){
        int size = courseToSelect.size();
        log.info("==================");
        log.info("有如下课程待选");
        for (int i = 0 ; i < size ; i++){
            Course cr = (Course) courseToSelect.get(i);
            log.info("课程为号为{},课程为{}",cr.getId(),cr.getName());
        }
    }

    /**
     * 通过迭代器访问
     */
    public void testIterator(){
        Iterator it = courseToSelect.iterator();
        while (it.hasNext()){
            Course cr = (Course) it.next();
            log.info("========= 通过迭代器访问");
            log.info("待选课程为:{}",cr.getName());
        }
    }

    /**
     * 通过foreach的方法访问, foreach其实就是迭代器的一种简便的写法
     */
    public void testForeach(){
        for (Object object : courseToSelect){
            Course cr = (Course)object;
            log.info("========= 通过foreach访问");
            log.info("待选课程为:{}",cr.getName());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ListTest listTest = new ListTest();
        listTest.testAdd();
        listTest.testGet();
        listTest.testForeach();
        Class course = Class.forName("Course");
        Method testForeach = course.getDeclaredMethod("testForeach");
        Course course1 = new Course("2","傻逼");
        testForeach.invoke(course1,null);

    }


}
