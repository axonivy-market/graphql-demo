package com.axonivy.demo.graphql.workflow;

import java.util.List;
import java.util.function.UnaryOperator;
import com.axonivy.demo.graphql.TypeWiringProvider;
import com.axonivy.demo.graphql.workflow.bean.CaseBean;
import com.axonivy.demo.graphql.workflow.bean.TaskBean;
import graphql.schema.DataFetcher;
import graphql.schema.idl.TypeRuntimeWiring.Builder;

public class WorkflowCaseTypeWiringProvider implements TypeWiringProvider {

  @Override
  public String typeName() {
    return "Case";
  }

  @Override
  public UnaryOperator<Builder> wiring() {
    return wiring -> wiring.dataFetcher("tasks", tasks());
  }

  private static DataFetcher<List<TaskBean>> tasks() {
    return dataFetchingEnvironment -> {
      if (dataFetchingEnvironment.getSource() instanceof CaseBean caseBean) {
        return caseBean.caze().tasks().all().stream()
                .map(TaskBean::createFor)
                .toList();
      }
      return List.of();
    };
  }
}
