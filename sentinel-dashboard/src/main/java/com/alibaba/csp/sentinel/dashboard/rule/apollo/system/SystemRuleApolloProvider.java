//package com.alibaba.csp.sentinel.dashboard.rule.apollo.system;
//
//import com.alibaba.csp.sentinel.dashboard.config.ApolloConfig;
//import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
//import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
//import com.alibaba.csp.sentinel.dashboard.service.RuleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * @author philia
// * @since 1.7.2
// */
//@Component("systemRuleApolloProvider")
//public class SystemRuleApolloProvider implements DynamicRuleProvider<List<SystemRuleEntity>> {
//
//    @Autowired
//    private RuleService apolloRuleServiceImpl;
//
//    @Autowired
//    private ApolloConfig apolloConfig;
//
//    @Override
//    public List<SystemRuleEntity> getRules(String appName) {
//        List<SystemRuleEntity> rules = apolloRuleServiceImpl.getRules(appName, apolloConfig.getSystemDataIdSuffix(), SystemRuleEntity.class);
//        for (SystemRuleEntity entity : rules) {
//            entity.setApp(appName);
//        }
//        return rules;
//    }
//}