package com.axonivy.demo.graphql.workflow.bean;

import ch.ivyteam.ivy.security.ISecurityMember;

public record ActivatorBean(
        String id,
        String name,
        String displayName,
        boolean isUser,
        boolean isEnabled) {

  public static ActivatorBean createFor(ISecurityMember securityMember) {
    if (securityMember == null) {
      return null;
    }
    return new ActivatorBean(
            securityMember.getSecurityMemberId(),
            securityMember.getName(),
            securityMember.getDisplayName(),
            securityMember.isUser(),
            securityMember.isEnabled());
  }
}
