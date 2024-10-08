package org.lyflexi.custom_design_pattern_framework.strategy.strategyByAop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.lyflexi.custom_design_pattern_framework.strategy.strategyByAop.annotation.AiPassiveMsg;
import org.lyflexi.custom_design_pattern_framework.strategy.strategyByAop.biz.ConditionEnums;
import org.lyflexi.custom_design_pattern_framework.strategy.strategyByAop.biz.MethodParam;
import org.lyflexi.custom_design_pattern_framework.strategy.strategyByAop.biz.ParamA;
import org.lyflexi.custom_design_pattern_framework.strategy.strategyByAop.biz.ParamB;
import org.lyflexi.custom_design_pattern_framework.strategy.strategyByAop.handler.annotationHander.MsgHandlerContext;
import org.lyflexi.custom_design_pattern_framework.strategy.strategyByAop.handler.AiPassiveMsgHandler;
import org.lyflexi.custom_design_pattern_framework.strategy.strategyByAop.utils.JoinPointUtils;
import org.lyflexi.custom_design_pattern_framework.strategy.strategyByAop.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/**
 * @Description:
 * @Author: lyflexi
 * @project: spring-practice
 * @Date: 2024/9/13 22:44
 */
@Aspect
@Component
@Slf4j
public class AiPassiveMsgAspect {


    @Autowired
    MsgHandlerContext msgHandlerContext;

    @Pointcut("@annotation(org.lyflexi.custom_design_pattern_framework.strategy.strategyByAop.annotation.AiPassiveMsg)")
    public void cut() {
        log.info("被动消息切面切入");
    }

    @AfterReturning("cut()")
    public void addAiInformation(JoinPoint joinPoint) throws IOException {
        //通过注解参数获取策略类型
        AiPassiveMsg annotation = JoinPointUtils.getAnnotationByClass(joinPoint,AiPassiveMsg.class);
        String sceneType = annotation.sceneType();
        String[] users = annotation.users();
        String param = annotation.param();
        log.info("handle by annotation param: {}",sceneType);
        AiPassiveMsgHandler instance = msgHandlerContext.getInstance(sceneType);
        HashMap<String, Object> map = new HashMap<>();
        instance.handle(map);

        //通过方法参数获取策略类型
        ParamA paramA = JoinPointUtils.getCertainParam(joinPoint, "paramA", ParamA.class);
        ParamB paramB = JoinPointUtils.getCertainParam(joinPoint, "paramB", ParamB.class);
        handleMethodParam(paramA, map);
        handleMethodParam(paramB, map);
    }

    /**
     *
     * @param param
     * @param map
     * @throws IOException
     */
    private static void handleMethodParam(MethodParam param, HashMap<String, Object> map) throws IOException {
        if (!Objects.isNull(param)){
            String condition = param.getCondition();
            ConditionEnums conditionEnums = ConditionEnums.match(condition);
            AiPassiveMsgHandler methodHandler = SpringUtils.getBean(conditionEnums.getValue(), AiPassiveMsgHandler.class);
            if (Objects.nonNull(methodHandler)){
                methodHandler.handle(map);
            }
        }
    }
}