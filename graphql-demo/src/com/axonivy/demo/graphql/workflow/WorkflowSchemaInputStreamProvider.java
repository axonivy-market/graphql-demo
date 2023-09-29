package com.axonivy.demo.graphql.workflow;

import java.io.InputStream;
import com.axonivy.demo.graphql.SchemaInputStreamProvider;

public class WorkflowSchemaInputStreamProvider implements SchemaInputStreamProvider {

  @Override
  public InputStream schema() {
    return getClass().getResourceAsStream("schema.graphqls");
  }
}
