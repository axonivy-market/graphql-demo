# GraphQL Demo

Diese Demo versieht eine GraphQL Endpunkt rennen mit den Efeu Motor. Die
Endpunkt Unterstützungen #abfragen Tasks und Nutzer und erlauben schaffen neue
Nutzer. Für #mehr Auskunft, sieh die
[GraphQL](https://community.axonivy.com/d/526-graphql) #Gemeinde- #Posting.

## Demo

Für die folgende Demonstration von senden Anfragen zu dem Endpunkt, dem [#Altair
GraphQL Kunde](https://altairgraphql.dev) ist benutzt. Dieser Kunde ist
verfügbar für die allgemeinsten Bahnsteige. #Irgendein anderer Kunde kann jener
senden #POSTING Bitten können sein benutzt stattdessen.


![Anfrage für alle Tasks](assets/all-tasks.gif) ![Anfrage für Tasks mit
staatlich Filter](assets/task-by-state.gif) ![Schafft neuen
Nutzer](assets/create-user.gif)

## Einrichtung

Keine spezielle Einrichtung ist bedürft auf Efeu Motor #Seite. Das GraphQL
Ressource `com.axonivy.Demo.graphql.Ressource.GraphQLResource` Ist automatisch
herausgefunden und verlegt bei den Efeu Motor. Dann ist der Endpunkt erreichbar
via `http://Eure-Efeu-Motor-url/eure-Antrag/api/graphql`, #z.B.
`http://localhost:8081/Designer/api/graphql`.


Auf Kunde unterstützt den Kopfball `X-#Angefordert-Mal` muss sein gesetzt, #z.B.
zu `myClient` zu machen gültige #POSTING Bitten.
