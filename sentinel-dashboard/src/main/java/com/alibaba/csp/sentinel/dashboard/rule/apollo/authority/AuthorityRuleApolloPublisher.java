package com.alibaba.csp.sentinel.dashboard.rule.apollo.authority;

import com.alibaba.csp.sentinel.dashboard.config.ApolloConfig;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AbstractRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
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
@Component("authorityRuleApolloPublisher")
public class AuthorityRuleApolloPublisher implements DynamicRulePublisher<List<AuthorityRuleEntity>> {

    @Autowired
    private RuleService apolloRuleServiceImpl;

    @Autowired
    private ApolloConfig apolloConfig;

    @Override
    public void publish(String app, List<AuthorityRuleEntity> rules) throws Exception {
        apolloRuleServiceImpl.publishRules(app, apolloConfig.getAuthorityDataIdSuffix(), JSON.toJSONString(rules.stream().map(AbstractRuleEntity::getRule).collect(Collectors.toList())));
    }
}