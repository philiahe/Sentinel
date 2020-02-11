package com.alibaba.csp.sentinel.dashboard.rule.apollo.param;

import com.alibaba.csp.sentinel.dashboard.config.ApolloConfig;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AbstractRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.service.RuleService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author philia
 * @since 1.7.2
 */
@Component("paramFlowRuleApolloPublisher")
public class ParamFlowRuleApolloPublisher implements DynamicRulePublisher<List<ParamFlowRuleEntity>> {

    @Autowired
    private RuleService apolloRuleServiceImpl;

    @Autowired
    private ApolloConfig apolloConfig;

    @Override
    public void publish(String app, List<ParamFlowRuleEntity> rules) throws Exception {
        apolloRuleServiceImpl.publishRules(app, apolloConfig.getParamDataIdSuffix(), JSON.toJSONString(rules.stream().map(AbstractRuleEntity::getRule).collect(Collectors.toList())));
    }
}