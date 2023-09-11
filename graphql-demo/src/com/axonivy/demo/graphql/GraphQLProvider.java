package com.axonivy.demo.graphql;

import graphql.GraphQL;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

public class GraphQLProvider {

  private GraphQLProvider() {}

  private static final GraphQL GRAPH_QL = init();

  public static GraphQL init() {
    var schemaGenerator = new SchemaGenerator();
    var typeRegistry = buildTypeRegistry();
    var typeWiring = buildTypeWiring();
    var graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, typeWiring);
    return GraphQL.newGraphQL(graphQLSchema).build();
  }

  private static TypeDefinitionRegistry buildTypeRegistry() {
    var schemaParser = new SchemaParser();
    var typeRegistry = new TypeDefinitionRegistry();
    SchemaFileProvider.providers()
            .map(SchemaFileProvider::schemaFile)
            .map(file -> schemaParser.parse(file))
            .forEach(registry -> typeRegistry.merge(registry));
    return typeRegistry;
  }

  private static RuntimeWiring buildTypeWiring() {
    var builder = RuntimeWiring.newRuntimeWiring();
    TypeWiringProvider.providers()
            .forEach(provider -> builder.type(provider.typeName(), provider.wiring()));
    return builder.build();
  }

  public static GraphQL instance() {
    return GRAPH_QL;
  }
}
