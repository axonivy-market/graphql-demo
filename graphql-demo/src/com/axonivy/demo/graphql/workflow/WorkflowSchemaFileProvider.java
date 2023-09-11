package com.axonivy.demo.graphql.workflow;

import java.io.File;
import com.axonivy.demo.graphql.SchemaFileProvider;

public class WorkflowSchemaFileProvider implements SchemaFileProvider {

  @Override
  public File schemaFile() {
    var schemaUrl = getClass().getResource("schema.graphqls");
    return new File(schemaUrl.getFile());
  }
}
