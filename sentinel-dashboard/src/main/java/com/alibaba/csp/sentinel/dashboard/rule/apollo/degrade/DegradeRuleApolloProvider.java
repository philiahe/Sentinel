package com.alibaba.csp.sentinel.dashboard.rule.apollo.degrade;

import com.alibaba.csp.sentinel.dashboard.config.ApolloConfig;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author philia
 * @since 1.7.2
 */
@Component("degradeRuleApolloProvider")
public class DegradeRuleApolloProvider implements DynamicRuleProvider<List<DegradeRuleEntity>> {

    @Autowired
    private RuleService apolloRuleServiceImpl;

    @Autowired
    private ApolloConfig apolloConfig;

    @Override
    public List<DegradeRuleEntity> getRules(String appName) {
        List<DegradeRuleEntity> rules = apolloRuleServiceImpl.getRules(appName, apolloConfig.getDegradeDataIdSuffix(), DegradeRuleEntity.class);
        for (DegradeRuleEntity entity : rules) {
            entity.setApp(appName);
        }
        return rules;
    }
}