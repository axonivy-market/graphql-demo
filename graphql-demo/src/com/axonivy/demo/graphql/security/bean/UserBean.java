package com.axonivy.demo.graphql.security.bean;

import ch.ivyteam.ivy.security.IUser;

public record UserBean(
        String id,
        String name,
        boolean isEnabled,
        boolean isExternal) {

  public static UserBean createFor(IUser user) {
    return new UserBean(
            user.getSecurityMemberId(),
            user.getName(),
            user.isEnabled(),
            user.isExternal());
  }
}
