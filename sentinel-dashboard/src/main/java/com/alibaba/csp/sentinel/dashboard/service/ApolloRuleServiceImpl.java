package com.alibaba.csp.sentinel.dashboard.service;

import com.alibaba.csp.sentinel.dashboard.config.ApolloConfig;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import com.ctrip.framework.apollo.openapi.dto.NamespaceReleaseDTO;
import com.ctrip.framework.apollo.openapi.dto.OpenItemDTO;
import com.ctrip.framework.apollo.openapi.dto.OpenNamespaceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author philia
 * @since 1.7.2
 */
@Service
public class ApolloRuleServiceImpl implements RuleService{
    @Autowired
    private ApolloOpenApiClient apolloOpenApiClient;

    @Autowired
    private ApolloConfig apolloConfig;

    @Override
    public <T> List<T> getRules(String appName, String flowDataIdSuffix, Class<T> ruleClass) {
        String flowDataId = appName + "-" + flowDataIdSuffix;
        OpenNamespaceDTO openNamespaceDTO = apolloOpenApiClient.getNamespace(apolloConfig.getAppId(), apolloConfig.getEnv(), apolloConfig.getClusterName(), apolloConfig.getNamespaceName());
        String rules = openNamespaceDTO
                .getItems()
                .stream()
                .filter(p -> p.getKey().equals(flowDataId))
                .map(OpenItemDTO::getValue)
                .findFirst()
                .orElse("");

        if (StringUtil.isEmpty(rules)) {
            return new ArrayList<>();
        }

        List<T> flow = JSON.parseArray(rules,ruleClass);
        if (Objects.isNull(flow)){
            return new ArrayList<>();
        }
        return flow;
    }

    @Override
    public void publishRules(String appName, String flowDataIdSuffix, String rules) {
        String flowDataId = appName + "-" + flowDataIdSuffix;
        AssertUtil.notEmpty(appName, "app name cannot be empty");
        if (rules == null) {
            return;
        }
        OpenItemDTO openItemDTO = new OpenItemDTO();
        openItemDTO.setKey(flowDataId);
        openItemDTO.setValue(rules);
        openItemDTO.setComment(apolloConfig.getModifyComment());
        openItemDTO.setDataChangeCreatedBy(apolloConfig.getModifyUser());
        apolloOpenApiClient.createOrUpdateItem(apolloConfig.getAppId(), apolloConfig.getEnv(), apolloConfig.getClusterName(), apolloConfig.getNamespaceName(), openItemDTO);

        NamespaceReleaseDTO namespaceReleaseDTO = new NamespaceReleaseDTO();
        namespaceReleaseDTO.setEmergencyPublish(true);
        namespaceReleaseDTO.setReleaseComment(apolloConfig.getReleaseComment());
        namespaceReleaseDTO.setReleasedBy(apolloConfig.getReleaseUser());
        namespaceReleaseDTO.setReleaseTitle(apolloConfig.getReleaseComment());
        apolloOpenApiClient.publishNamespace(apolloConfig.getAppId(), apolloConfig.getEnv(), apolloConfig.getClusterName(), apolloConfig.getNamespaceName(), namespaceReleaseDTO);
    }
}
