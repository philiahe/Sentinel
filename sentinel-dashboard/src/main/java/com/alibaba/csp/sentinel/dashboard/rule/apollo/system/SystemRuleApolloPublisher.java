package com.alibaba.csp.sentinel.dashboard.rule.apollo.system;

import com.alibaba.csp.sentinel.dashboard.config.ApolloConfig;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.service.RuleService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author philia
 * @since 1.7.2
 */
@Component("systemRuleApolloPublisher")
public class SystemRuleApolloPublisher implements DynamicRulePublisher<List<SystemRuleEntity>> {

    @Autowired
    private RuleService apolloRuleServiceImpl;

    @Autowired
    private ApolloConfig apolloConfig;

    @Override
    public void publish(String app, List<SystemRuleEntity> rules) throws Exception {
        apolloRuleServiceImpl.publishRules(app, apolloConfig.getSystemDataIdSuffix(), JSON.toJSONString(rules));
    }
}