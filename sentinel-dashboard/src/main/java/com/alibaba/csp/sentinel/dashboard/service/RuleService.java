package com.alibaba.csp.sentinel.dashboard.service;

import java.util.List;

/**
 * @author philia
 * @since 1.7.2
 */
public interface RuleService {
    <T> List<T> getRules(String appName, String flowDataIdSuffix, Class<T> ruleClass);

    void publishRules(String appName, String flowDataIdSuffix, String rules);
}
