package com.axonivy.demo.graphql.workflow;

import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import com.axonivy.demo.graphql.TypeWiringProvider;
import com.axonivy.demo.graphql.workflow.bean.TaskBean;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.IWorkflowContext;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import graphql.schema.DataFetcher;
import graphql.schema.idl.TypeRuntimeWiring.Builder;

public class WorkflowQueryTypeWiringProvider implements TypeWiringProvider {

  private static final String ID_KEY = "id";
  private static final String STATE_KEY = "state";

  @Override
  public UnaryOperator<Builder> wiring() {
    return wiring -> wiring
            .dataFetcher("allTasks", allTasks())
            .dataFetcher("taskById", taskById())
            .dataFetcher("tasksByState", tasksByState());
  }

  private static DataFetcher<List<TaskBean>> allTasks() {
    return dataFetchingEnvironment -> tasks()
            .map(TaskBean::createFor)
            .toList();
  }

  private static DataFetcher<TaskBean> taskById() {
    return dataFetchingEnvironment -> {
      var id = Long.parseLong(dataFetchingEnvironment.getArgument(ID_KEY));
      var task = Optional.ofNullable(workflowContext().findTask(id));
      return task.map(TaskBean::createFor).orElse(null);
    };
  }

  private static DataFetcher<List<TaskBean>> tasksByState() {
    return dataFetchingEnvironment -> {
      var state = dataFetchingEnvironment.getArgument(STATE_KEY).toString();
      return tasks().filter(task -> task.getState().name().equalsIgnoreCase(state))
              .map(TaskBean::createFor)
              .toList();
    };
  }

  private static Stream<ITask> tasks() {
    return workflowContext()
            .getTaskQueryExecutor()
            .getResults(TaskQuery.create())
            .stream();
  }

  private static IWorkflowContext workflowContext() {
    return IWorkflowContext.current();
  }
}
