package com.axonivy.demo.graphql.workflow.bean;

import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomField;

public record CustomFieldBean(
        String name,
        CustomFieldType type,
        Object value) {

  public static CustomFieldBean createFor(ICustomField<?> customField) {
    return new CustomFieldBean(
            customField.name(),
            customField.type(),
            customField.getOrNull());
  }
}
