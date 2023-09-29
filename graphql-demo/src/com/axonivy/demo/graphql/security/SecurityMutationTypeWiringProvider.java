package com.axonivy.demo.graphql.security;

import java.util.function.UnaryOperator;
import com.axonivy.demo.graphql.TypeWiringProvider;
import com.axonivy.demo.graphql.security.bean.UserBean;
import ch.ivyteam.ivy.security.ISecurity;
import graphql.schema.DataFetcher;
import graphql.schema.idl.TypeRuntimeWiring.Builder;

public class SecurityMutationTypeWiringProvider implements TypeWiringProvider {

  @Override
  public String typeName() {
    return "Mutation";
  }

  @Override
  public UnaryOperator<Builder> wiring() {
    return wiring -> wiring
            .dataFetcher("createUser", createUser());
  }

  private static DataFetcher<UserBean> createUser() {
    return dataFetchingEnvironment -> {
      var userName = dataFetchingEnvironment.getArgument("userName").toString();
      var password = dataFetchingEnvironment.getArgument("password").toString();
      var user = ISecurity.current().users().create(userName, password);
      return UserBean.createFor(user);
    };
  }
}
