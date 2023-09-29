package com.axonivy.demo.graphql;

import java.io.InputStream;
import java.util.stream.Stream;
import com.axonivy.demo.graphql.security.SecuritySchemaInputStreamProvider;
import com.axonivy.demo.graphql.workflow.WorkflowSchemaInputStreamProvider;

public interface SchemaInputStreamProvider {

  InputStream schema();

  static Stream<SchemaInputStreamProvider> providers() {
    return Stream.of(new BaseSchemaInputStreamProvider(),
            new WorkflowSchemaInputStreamProvider(),
            new SecuritySchemaInputStreamProvider());
  }
}
