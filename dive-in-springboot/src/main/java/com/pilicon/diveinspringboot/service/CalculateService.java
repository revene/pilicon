package com.pilicon.diveinspringboot.service;

/**
 * 计算服务
 */
public interface CalculateService {

    /**
     * 从多个整数sum求和
     * @param values 多个整数
     * @return sum累加结果
     */
    Integer sum(Integer... values);
}
