package org.lyflexi.custom_design_pattern_framework.strategy.strategyByReflect.impl;

import org.lyflexi.custom_design_pattern_framework.strategy.strategyByReflect.Strategy;

/**
 * @Description:
 * @Author: lyflexi
 * @project: spring-practice
 * @Date: 2024/9/13 22:14
 */
public class StrategyB implements Strategy {
    @Override
    public void execute(String data) {
        System.out.println("Executing Strategy B with data: " + data);
    }
}