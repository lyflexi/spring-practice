package org.lyflexi.custom_design_pattern_framework.strategy.strategyByAop.biz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @Description:
 * @Author: lyflexi
 * @project: spring-practice
 * @Date: 2024/9/14 21:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MethodParam {
    public String condition;
}
