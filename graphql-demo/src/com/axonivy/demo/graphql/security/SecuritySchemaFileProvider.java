package com.axonivy.demo.graphql.security;

import java.io.File;
import com.axonivy.demo.graphql.SchemaFileProvider;

public class SecuritySchemaFileProvider implements SchemaFileProvider {

  @Override
  public File schemaFile() {
    var schemaUrl = getClass().getResource("schema.graphqls");
    return new File(schemaUrl.getFile());
  }
}
