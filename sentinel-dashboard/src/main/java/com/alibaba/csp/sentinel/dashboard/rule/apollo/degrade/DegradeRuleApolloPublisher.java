package com.alibaba.csp.sentinel.dashboard.rule.apollo.degrade;

import com.alibaba.csp.sentinel.dashboard.config.ApolloConfig;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
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
@Component("degradeRuleApolloPublisher")
public class DegradeRuleApolloPublisher implements DynamicRulePublisher<List<DegradeRuleEntity>> {

    @Autowired
    private RuleService apolloRuleServiceImpl;

    @Autowired
    private ApolloConfig apolloConfig;

    @Override
    public void publish(String app, List<DegradeRuleEntity> rules) {
        apolloRuleServiceImpl.publishRules(app, apolloConfig.getDegradeDataIdSuffix(), JSON.toJSONString(rules));
    }
}