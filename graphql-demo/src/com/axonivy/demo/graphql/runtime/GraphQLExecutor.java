package com.axonivy.demo.graphql.runtime;

import java.util.Map;
import com.axonivy.demo.graphql.GraphQLProvider;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionInput;
import graphql.ExecutionResult;

public class GraphQLExecutor {

  private GraphQLExecutor() {}

  public static String run(String body) {
    var graphQL = GraphQLProvider.instance();
    var requestBody = toRequestBody(body);
    var executionInput = ExecutionInput.newExecutionInput()
            .query(requestBody.query())
            .variables(requestBody.variables())
            .build();
    var executionResult = graphQL.execute(executionInput);
    return toJson(executionResult);
  }

  private static RequestBody toRequestBody(String body) {
    try {
      return new ObjectMapper().readValue(body, RequestBody.class);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  private static String toJson(ExecutionResult executionResult) {
    try {
      return new ObjectMapper().writeValueAsString(executionResult.toSpecification());
    } catch (JsonProcessingException ex) {
      throw new RuntimeException(ex);
    }
  }

  private record RequestBody(
          String operationName,
          String query,
          @JsonAnySetter Map<String, Object> variables) {}
}
