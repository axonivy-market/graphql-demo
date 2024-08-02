package com.axonivy.demo.graphql.test;

import static org.assertj.core.api.Assertions.assertThat;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import org.junit.jupiter.api.Test;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.security.ISecurity;
import ch.ivyteam.ivy.security.user.IUserRepository;
import ch.ivyteam.ivy.security.user.NewUser;

@IvyTest(enableWebServer = true)
class TestGraphQLSecurity {

  WebTarget graphQlClient = Ivy.rest().client("test-graphql");
  IUserRepository userRepo = ISecurity.current().users();

  @Test
  void allUsers() {
    assertThat(userRepo.count()).isZero();
    var user = userRepo.create(NewUser.create("testUser").toNewUser());
    var requestEntity = requestEntity("{allUsers {id name isEnabled}}");
    var expectedMessage = """
            {"data":{"allUsers":[{"id":"%s","name":"testUser","isEnabled":true}]}}"""
            .formatted(user.getSecurityMemberId());
    requestAndAssert(requestEntity, expectedMessage);
    userRepo.delete("testUser");
  }

  @Test
  void createUser() {
    assertThat(userRepo.count()).isZero();
    var query = "mutation{createUser(userName: \\\"createdUser\\\", password: \\\"aPassword\\\") {name isEnabled}}";
    var requestEntity = requestEntity(query);
    var expectedMessage = """
            {"data":{"createUser":{"name":"createdUser","isEnabled":true}}}""";
    requestAndAssert(requestEntity, expectedMessage);
    assertThat(userRepo.count()).isOne();
    userRepo.delete("createdUser");
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
