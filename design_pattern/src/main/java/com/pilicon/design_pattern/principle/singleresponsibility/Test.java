package com.pilicon.design_pattern.principle.singleresponsibility;

/**
 * 类的单一职责要根据实际的情况,不能一味地单一职责, 不然会爆炸
 */
public class Test {
    public static void main(String[] args) {
        /** v1 没有单一职责**/
//        Bird bird = new Bird();
//        bird.mainMoveMode("大雁");
//        bird.mainMoveMode("鸵鸟");

        /** v2 单一职责**/
        WalkBird walkBird = new WalkBird();
        walkBird.mainMoveMode("鸵鸟");

        FlyBird flyBird = new FlyBird();
        flyBird.mainMoveMode("大雁");
    }
}
