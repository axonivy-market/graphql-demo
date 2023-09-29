package com.axonivy.demo.graphql.security;

import java.util.List;
import java.util.function.UnaryOperator;
import com.axonivy.demo.graphql.TypeWiringProvider;
import com.axonivy.demo.graphql.security.bean.UserBean;
import ch.ivyteam.ivy.security.ISecurity;
import ch.ivyteam.ivy.security.query.UserQuery;
import graphql.schema.DataFetcher;
import graphql.schema.idl.TypeRuntimeWiring.Builder;

public class SecurityQueryTypeWiringProvider implements TypeWiringProvider {

  @Override
  public UnaryOperator<Builder> wiring() {
    return wiring -> wiring
            .dataFetcher("allUsers", allUsers());
  }

  private static DataFetcher<List<UserBean>> allUsers() {
    return dataFetchingEnvironment -> ISecurity.current().users()
            .queryExecutor()
            .getResults(UserQuery.create())
            .stream()
            .map(UserBean::createFor)
            .toList();
  }
}
