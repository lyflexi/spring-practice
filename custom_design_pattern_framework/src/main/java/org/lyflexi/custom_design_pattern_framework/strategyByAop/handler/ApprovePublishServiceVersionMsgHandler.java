package org.lyflexi.custom_design_pattern_framework.strategyByAop.handler;

import lombok.extern.slf4j.Slf4j;
import org.lyflexi.custom_design_pattern_framework.strategyByAop.annotation.PassiveMsgHandlerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: lyflexi
 * @project: spring-practice
 * @Date: 2024/9/13 22:52
 */
@Component
@Slf4j
@PassiveMsgHandlerType("audit.serviceVersion.publish.audit")
public class ApprovePublishServiceVersionMsgHandler extends AiPassiveMsgHandler {

    @Override
    public Map<String, Object> handle(Map<String, Object> params) {
        try {
            log.info("ApprovePublishServiceVersionMsgHandler begin");
        }
        catch (RuntimeException e) {
        }
        return new HashMap<>();
    }
}