package org.lyflexi.custom_design_pattern_framework.strategy.strategyByAop.handler.annotationHander;

import org.lyflexi.custom_design_pattern_framework.strategy.strategyByAop.handler.AiPassiveMsgHandler;
import org.lyflexi.custom_design_pattern_framework.strategy.strategyByAop.utils.SpringUtils;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @Description:
 * @Author: lyflexi
 * @project: spring-practice
 * @Date: 2024/9/13 22:59
 */
public class MsgHandlerContext {

    //里面也是单实例handlerMap
    private Map<String, Class<? extends Annotation>> handlerMap;

    public MsgHandlerContext(Map<String, Class<? extends Annotation>> handlerMap) {
        this.handlerMap = handlerMap;
    }

    public AiPassiveMsgHandler getInstance(String type) {
        Class clazz = handlerMap.get(type);
        if (clazz == null) {
            throw new IllegalArgumentException("not found handler for type: " + type);
        }

        return (AiPassiveMsgHandler) SpringUtils.getBean(clazz);
    }
}