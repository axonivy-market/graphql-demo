package com.axonivy.demo.graphql.workflow.bean;

import java.util.Date;
import java.util.List;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

public record TaskBean(
        Long id,
        String name,
        String description,
        Date startTimeStamp,
        Date expiryTimeStamp,
        WorkflowPriority priority,
        TaskState state,
        ActivatorBean activator,
        CaseBean caze,
        List<CustomFieldBean> customFields) {

  public static TaskBean createFor(ITask task) {
    var customFields = task.customFields().all().stream().map(CustomFieldBean::createFor).toList();
    return new TaskBean(
            task.getId(),
            task.getName(),
            task.getDescription(),
            task.getStartTimestamp(),
            task.getExpiryTimestamp(),
            task.getPriority(),
            task.getState(),
        ActivatorBean
            .createFor(!task.responsibles().all().isEmpty() ? task.responsibles().all().getFirst().get() : null),
            CaseBean.createFor(task.getCase()),
            customFields);
  }
}
