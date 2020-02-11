package com.alibaba.csp.sentinel.dashboard.rule.apollo.authority;

import com.alibaba.csp.sentinel.dashboard.config.ApolloConfig;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.service.RuleService;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author philia
 * @since 1.7.2
 */
@Component("authorityRuleApolloProvider")
public class AuthorityRuleApolloProvider implements DynamicRuleProvider<List<AuthorityRuleEntity>> {

    @Autowired
    private RuleService apolloRuleServiceImpl;

    @Autowired
    private ApolloConfig apolloConfig;

    @Override
    public List<AuthorityRuleEntity> getRules(String appName) {
        List<AuthorityRuleEntity> rules = apolloRuleServiceImpl.getRules(appName, apolloConfig.getAuthorityDataIdSuffix(), AuthorityRule.class)
                .stream()
                .map(AuthorityRuleEntity::new)
                .collect(Collectors.toList());
        for (AuthorityRuleEntity entity : rules) {
            entity.setApp(appName);
        }
        return rules;
    }
}