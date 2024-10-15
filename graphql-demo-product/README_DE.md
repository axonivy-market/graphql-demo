# GraphQL Demo

Diese Demo stellt einen GraphQL-Endpunkt bereit, der mit der Ivy Engine läuft. Der Endpunkt unterstützt Abfragen zu Aufgaben und Benutzern und ermöglicht das Erstellen neuer Benutzer. Weitere Informationen findest Du im [GraphQL](https://community.axonivy.com/d/526-graphql) Community-Beitrag.

## Demo

Für die folgende Demonstration, bei der Abfragen an den Endpunkt gesendet werden, wird der [Altair GraphQL Client](https://altairgraphql.dev) verwendet. Dieser Client ist für die meisten gängigen Plattformen verfügbar. Alternativ kann jeder andere Client verwendet werden, der POST-Anfragen senden kann.

![Abfrage für alle Aufgaben](assets/all-tasks.gif)
![Abfrage für Aufgaben mit Status-Filter](assets/task-by-state.gif)
![Neuen Benutzer erstellen](assets/create-user.gif)

## Setup

Auf der Ivy Engine-Seite ist keine besondere Einrichtung erforderlich. Die GraphQL-Ressource `com.axonivy.demo.graphql.resource.GraphQLResource` wird automatisch von der Ivy Engine erkannt und veröffentlicht. Danach ist der Endpunkt über `http://your-ivy-engine-url/your-application/api/graphql` zugänglich, z.B. `http://localhost:8081/designer/api/graphql`.

Auf der Client-Seite muss der Header `X-Requested-By` gesetzt werden, z.B. auf `myClient`, um gültige POST-Anfragen zu senden.
