package org.lyflexi.custom_design_pattern_framework.responsibilityChain.genericBuilder.handler.userhandler;


import org.lyflexi.custom_design_pattern_framework.responsibilityChain.genericBuilder.AbstractHandler;
import org.lyflexi.custom_design_pattern_framework.responsibilityChain.genericBuilder.model.LoginUser;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: ly
 * @Date: 2024/3/13 22:33
 */
@Component
@Order(2)
public class VerifyRoleHanlder extends AbstractHandler<LoginUser,Boolean> {
    @Override
    public Boolean doHandler(LoginUser targetData) {
        if(!"admin".equals(targetData.getRoleName())){
            System.out.println("LoginUser角色信息有误");
            return false;
        }
        System.out.println("2.LoginUser角色信息校验通过");

        return super.handle(targetData);
    }
}

