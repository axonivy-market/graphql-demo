package com.axonivy.demo.graphql;

import java.io.File;

public class BaseSchemaFileProvider implements SchemaFileProvider {

  @Override
  public File schemaFile() {
    var schemaUrl = getClass().getResource("schema.graphqls");
    return new File(schemaUrl.getFile());
  }
}
