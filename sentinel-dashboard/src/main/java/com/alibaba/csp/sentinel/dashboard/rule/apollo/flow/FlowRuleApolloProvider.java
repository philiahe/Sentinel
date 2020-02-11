package com.alibaba.csp.sentinel.dashboard.rule.apollo.flow;

import com.alibaba.csp.sentinel.dashboard.config.ApolloConfig;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author philia
 * @since 1.7.2
 */
@Component("flowRuleApolloProvider")
public class FlowRuleApolloProvider implements DynamicRuleProvider<List<FlowRuleEntity>> {

    @Autowired
    private RuleService apolloRuleServiceImpl;

    @Autowired
    private ApolloConfig apolloConfig;

    @Override
    public List<FlowRuleEntity> getRules(String appName) {
        List<FlowRuleEntity> rules = apolloRuleServiceImpl.getRules(appName, apolloConfig.getFlowDataIdSuffix(), FlowRuleEntity.class);
        for (FlowRuleEntity entity : rules) {
            entity.setApp(appName);
            if (entity.getClusterConfig() != null && entity.getClusterConfig().getFlowId() != null) {
                entity.setId(entity.getClusterConfig().getFlowId());
            }
        }
        return rules;
    }
}