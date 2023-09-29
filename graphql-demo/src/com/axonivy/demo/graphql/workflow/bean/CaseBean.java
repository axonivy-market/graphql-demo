package com.axonivy.demo.graphql.workflow.bean;

import java.util.List;
import ch.ivyteam.ivy.workflow.ICase;

public record CaseBean(
        ICase caze,
        Long id,
        String name,
        String description,
        List<CustomFieldBean> customFields) {

  public static CaseBean createFor(ICase caze) {
    var customFields = caze.customFields().all().stream().map(CustomFieldBean::createFor).toList();
    return new CaseBean(
            caze,
            caze.getId(),
            caze.getName(),
            caze.getDescription(),
            customFields);
  }
}
