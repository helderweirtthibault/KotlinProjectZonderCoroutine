# Project: Netwerk & hardware belasting

De applicatie probeert op een representatieve wijze gegevens te verzamelen in de netwerkcommunicatie.
Er werd gebruikgemaakt van drie verschillende WeatherAPIs en van de Google Maps API omdat de toepassingen populair zijn in de ontwikkeling van applicaties.
In de applicatie zijn vele verschillende werkingen getest of geëxperimenteerd, en is het mogelijk dat sommige code niet relevant meer is voor de huidige werking.
De hardware belasting zal worden gemeten met de profiler tools en de meerwaarde in netwerkcommunicatie via testen.

Uiteindelijk wordt om accurate gegevens de applicatie opgesplist in 2 applicaties waarbij één coroutines bevat. Dit voorkomt dat de data beïnvloedt wordt door de cache.
In het totaal worden dus 3 applicaties getest (+ retrofit Mars applicatie). 

## TODO - planning

* Applicatie converteren naar Java
* Verzamelen gegevens en vergelijken
* Proof-of-concept uitwerken in de paper (pdf)

## Werking huidige applicatie

### WeatherAPIs

De app kan navigeren naar twee verschillende fragmenten die gebruikmaken van een viewmodel. De NetworkCallragment probeert data op te halen uit drie verschillende API calls zonder coroutines en vult de livedata op. 
Het andere fragment heeft dezelfde werking maar maakt gebruik van coroutines. 
Deze API requests werden meerdere malen gestuurd en in verschillende coroutinescopes getest.

De testen berekenen de totale executie tijd van het ophalen van data voor zowel de werkwijze met als zonder coroutine.

### Google Maps API

Wanneer Google Maps wordt geopend in de applicatie, probeert het systeem de locatie van de gebruiker op te halen.
Als het systeem de locatie heeft opgehaald, zal de markeerstip wijzigen.

De visuele UI testen berekenen hoeveel tijd nodig is om Google Maps te openen, de locatie van de gebruiker op te vragen en ten slotte de coördinaten te wijzigen.

