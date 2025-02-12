# spring-practice
# debug_spring
debug_spring涵盖以下调试记录
- xml：spring工程编译时，resource目录中的文件会被复制到Java的类路径classpath中，读取方式如`ApplicationContext context = new ClassPathXmlApplicationContext("classpath:dependencecircle/dependence_hasaop.xml");`
- ioc：见[debug-factory](debug-factory)
- aop：见[debug-aop](debug-aop)
- aware：见[debug-aware](debug-aware)
- Bean后置处理器：见[debug-beanlife](debug-beanlife)
- Bean生命周期：见[debug-beanlife](debug-beanlife)
- spring支持循环依赖：见[debug-circular-dependency](debug-circular-dependency)
- BeanFactory后置处理器：见[debug-factory](debug-factory)
- 监听器测试：见[debug-listener](debug-listener)
- 事务测试: 见[debug-tx](debug-tx)

注意：对于纯Spring测试，建议走Junit单元测试，如
```java
public class IOCTest {

    @Test
    public void test01() {
        
    }
}
```

# custom_design_pattern_framework
custom_design_pattern_framework涵盖以下调试记录：
调试方式如下
1. 请按需excludeFilters相应的包，避免启动冲突
```java
@SpringBootApplication
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "org.lyflexi.custom_design_pattern_framework.strategyByReflect.*"),
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "org.lyflexi.custom_design_pattern_framework.strategyByInitializingBean.*"),
})
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
```
2. 执行单元测试目录
org.lyflexi.custom_design_pattern_framework


## 策略模式
### 设计方案1：
基于枚举和反射实现，按需实例化对应的策略

图略
### 设计方案2
手写策略工厂，基于InitializingBean#afterPropertiesSet自动注册所有策略到自己的策略工厂

图略
### 设计方案3：
基于BeanFactoryPostProcessor，利用Spring现成BeanFactory，配合Aop注解实现

兄弟们，一图胜千言
![img.png](custom_design_pattern_framework/pic/strategy.png)

打印信息如下：
```shell
2024-09-14T21:54:53.265+08:00  INFO 8744 --- [           main] o.l.c.StrategyByAop                      : Starting StrategyByAop using Java 17.0.9 with PID 8744 (started by hasee in E:\github\spring-practice\custom_design_pattern_framework)
2024-09-14T21:54:53.267+08:00  INFO 8744 --- [           main] o.l.c.StrategyByAop                      : No active profile set, falling back to 1 default profile: "default"
2024-09-14T21:54:54.851+08:00  INFO 8744 --- [           main] o.l.c.StrategyByAop                      : Started StrategyByAop in 1.9 seconds (process running for 2.985)
2024-09-14T21:54:55.315+08:00  INFO 8744 --- [           main] o.l.c.strategyByAop.biz.BizServiceImpl   : applyPublish begin
2024-09-14T21:54:55.318+08:00  INFO 8744 --- [           main] o.l.c.s.aspect.AiPassiveMsgAspect        : handle by annotation param: audit.serviceVersion.publish
2024-09-14T21:54:55.321+08:00  INFO 8744 --- [           main] .c.s.h.a.PublishServiceVersionMsgHandler : PublishServiceVersionMsgHandler begin
2024-09-14T21:54:55.321+08:00  INFO 8744 --- [           main] o.l.c.s.h.methodHandler.ParamAHandler    : ParamAHandler begin
2024-09-14T21:54:55.321+08:00  INFO 8744 --- [           main] o.l.c.s.h.methodHandler.ParamBHandler    : ParamBHandler begin

2024-09-14T21:54:55.321+08:00  INFO 8744 --- [           main] o.l.c.strategyByAop.biz.BizServiceImpl   : approvePublish begin
2024-09-14T21:54:55.321+08:00  INFO 8744 --- [           main] o.l.c.s.aspect.AiPassiveMsgAspect        : handle by annotation param: audit.serviceVersion.publish.audit
2024-09-14T21:54:55.322+08:00  INFO 8744 --- [           main] a.ApprovePublishServiceVersionMsgHandler : ApprovePublishServiceVersionMsgHandler begin
2024-09-14T21:54:55.322+08:00  INFO 8744 --- [           main] o.l.c.s.h.methodHandler.ParamAHandler    : ParamAHandler begin
2024-09-14T21:54:55.322+08:00  INFO 8744 --- [           main] o.l.c.s.h.methodHandler.ParamBHandler    : ParamBHandler begin
```

# 更新日志
## 2024/9月更新

策略模式设计方案2优化, 新增策略模式方案4：

手写策略工厂，基于Spring3新特性，通过构造函数@Autowired批量注入策略Bean集合，见[strategyByHandsFactory](custom_design_pattern_framework%2Fsrc%2Fmain%2Fjava%2Forg%2Flyflexi%2Fcustom_design_pattern_framework%2FstrategyByHandsFactory)

图略


## 2024/09/29更新 责任链模式
适用场景：
- 适用于AOP式执行
- 适用于Mybatis的二级缓存

实现方式：
- 基于工厂的责任链
- 基于建造者的责任链
- 基于泛型和工厂的责任链，将数据对象和工厂都定义为泛型，使得责任链更加通用，
- 基于泛型和建造者的责任链，将数据对象和建造器都定义为泛型，使得责任链更加通用

附图见：![responsibilityChain.png](custom_design_pattern_framework/pic/responsibilityChain.png)
