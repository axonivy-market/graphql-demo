package com.axonivy.demo.graphql.test;

import static org.assertj.core.api.Assertions.assertThat;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.workflow.ITask;

@IvyTest(enableWebServer = true)
class TestGraphQLWorkflow {

  WebTarget graphQlClient = Ivy.rest().client("test-graphql");

  @BeforeEach
  @SuppressWarnings("restriction")
  void setup() {
    ((ch.ivyteam.ivy.workflow.internal.Task) ITask.current()).makePersistent();
  }

  @Test
  void allTasks() {
    var requestEntity = requestEntity("{allTasks {id name state}}");
    var expectedMessage = """
            {"data":{"allTasks":[{"id":"1","name":"test process","state":"CREATED"}]}}""";
    requestAndAssert(requestEntity, expectedMessage);
  }

  @Test
  void taskById() {
    var requestEntity = requestEntity("{taskById(id: 1) {priority state}}");
    var expectedMessage = """
            {"data":{"taskById":{"priority":"NORMAL","state":"CREATED"}}}""";
    requestAndAssert(requestEntity, expectedMessage);
  }

  @Test
  void taskByIdNoMatch() {
    var requestEntity = requestEntity("{taskById(id: 999) {id name state}}");
    var expectedMessage = """
            {"data":{"taskById":null}}""";
    requestAndAssert(requestEntity, expectedMessage);
  }

  @Test
  void tasksByState() {
    var query = "{tasksByState(state: \\\"CREATED\\\") {priority state}}";
    var requestEntity = requestEntity(query);
    var expectedMessage = """
            {"data":{"tasksByState":[{"priority":"NORMAL","state":"CREATED"}]}}""";
    requestAndAssert(requestEntity, expectedMessage);
  }

  @Test
  void tasksByStateNoMatch() {
    var query = "{tasksByState(state: \\\"DONE\\\") {id name}}";
    var requestEntity = requestEntity(query);
    var expectedMessage = """
            {"data":{"tasksByState":[]}}""";
    requestAndAssert(requestEntity, expectedMessage);
  }

  String requestEntity(String query) {
    return """
            {
              "variables": {},
              "query": "%s"
            }
            """.formatted(query);
  }

  void requestAndAssert(String requestEntity, String expectedMessage) {
    var response = graphQlClient.request()
            .header("X-Requested-By", "ivy")
            .post(Entity.json(requestEntity));
    assertThat(response.getStatus()).isEqualTo(200);
    var message = response.readEntity(String.class);
    assertThat(message).isEqualTo(expectedMessage);
  }
}
