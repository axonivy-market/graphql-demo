package com.axonivy.demo.graphql.security;

import java.io.InputStream;
import com.axonivy.demo.graphql.SchemaInputStreamProvider;

public class SecuritySchemaInputStreamProvider implements SchemaInputStreamProvider {

  @Override
  public InputStream schema() {
    return getClass().getResourceAsStream("schema.graphqls");
  }
}
