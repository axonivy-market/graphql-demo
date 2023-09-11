package com.axonivy.demo.graphql;

import java.io.File;
import java.util.stream.Stream;
import com.axonivy.demo.graphql.security.SecuritySchemaFileProvider;
import com.axonivy.demo.graphql.workflow.WorkflowSchemaFileProvider;

public interface SchemaFileProvider {

  File schemaFile();

  static Stream<SchemaFileProvider> providers() {
    return Stream.of(new BaseSchemaFileProvider(),
            new WorkflowSchemaFileProvider(),
            new SecuritySchemaFileProvider());
  }
}
