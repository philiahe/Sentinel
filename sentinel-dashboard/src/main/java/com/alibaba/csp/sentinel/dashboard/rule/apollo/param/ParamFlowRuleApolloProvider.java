//package com.alibaba.csp.sentinel.dashboard.rule.apollo.param;
//
//import com.alibaba.csp.sentinel.dashboard.config.ApolloConfig;
//import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
//import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
//import com.alibaba.csp.sentinel.dashboard.service.RuleService;
//import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @author philia
// * @since 1.7.2
// */
//@Component("paramFlowRuleApolloProvider")
//public class ParamFlowRuleApolloProvider implements DynamicRuleProvider<List<ParamFlowRuleEntity>> {
//
//    @Autowired
//    private RuleService apolloRuleServiceImpl;
//
//    @Autowired
//    private ApolloConfig apolloConfig;
//
//    @Override
//    public List<ParamFlowRuleEntity> getRules(String appName) {
//        List<ParamFlowRuleEntity> rules = apolloRuleServiceImpl.getRules(appName, apolloConfig.getParamDataIdSuffix(), ParamFlowRule.class)
//                .stream()
//                .map(ParamFlowRuleEntity::new)
//                .collect(Collectors.toList());
//
//        for (ParamFlowRuleEntity entity : rules) {
//            entity.setApp(appName);
//            if (entity.getClusterConfig() != null && entity.getClusterConfig().getFlowId() != null) {
//                entity.setId(entity.getClusterConfig().getFlowId());
//            }
//        }
//
//        return rules;
//    }
//}