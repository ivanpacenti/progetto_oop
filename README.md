<div id="top"></div>
<!-- PROJECT LOGO -->
<br />
<div align="center ">
  <a href="https://github.com/ivanpacenti/progetto_oop">
    <img src="images/logo1.png" alt="Logo" width="140" height="140" >
  </a>
 <h3 align="center" size=90>Twitter Metrics</h3>



  <p align="center">
     </p>
    <a href="https://github.com/ivanpacenti/progetto_oop"><strong>Esplora il progetto »</strong></a>
    <br />
    <br />
    
</div>




<!-- TABLE OF CONTENTS -->
<details>
  <summary>Contenuti</summary>
  <ol>
    <li>
      <a href="#introduzione">Introduzione</a>
      <ul>
        <li><a href="#strumenti-utilizzati">Strumenti utilizzati</a></li>
      </ul>
    </li>
    <li>
      <a href="#per-iniziare">Per iniziare</a>
      <ul>
        <li><a href="#prerequisiti">Prerequisiti</a></li>
        <li><a href="#installazione">Installazione</a></li>
      </ul>
    </li>
    <li><a href="#utilizzo">Utilizzo</a></li>
    <ul>
        <li><a href="#rotte">Rotte</a></li>
        <li><a href="#specifiche-tecniche">Specifiche tecniche</a></li>
      </ul>
    <li><a href="#eccezioni">Eccezioni</a></li>
    <li><a href="#test">Test</a></li>
    <li><a href="#documentazione">Documentazione</a></li>
    <li><a href="#contributi">Contributi</a></li>
    
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## Introduzione

Progetto creato per l'esame di Programmazione ad oggetti 2021\2022 dell'Università Politecnica delle Marche.
Come obiettivo bisognava creare un' applicazione web che analizzasse e fornisse statistiche, interfacciandosi alle API di Twitter, di una lista di tweet dell'Università.
Una volta completato il compito, si è deciso di ampliare il servizio a qualsiasi account pubblico, in modo tale da essere utile a chiunque voglia studiare le dinamiche delle metriche di engagement del famoso Social Network.


<p align="right">(<a href="#top">torna all'inizio</a>)</p>



### Strumenti utilizzati

<img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="15" height="15"/> [Java](https://www.java.com/) - Linguaggio di programmazione.  
<img src="https://www.nicepng.com/png/full/264-2648074_eclipse-logo-png-transparent-eclipse-ide.png" alt="spring" width="15" height="15"/> [Eclipse](https://www.eclipse.org/) - IDE per lo sviluppo del progetto.  
<img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="15" height="15"/> [Spring Boot](https://spring.io/projects/spring-boot) - Framework per la realizzazione di applicazioni web.   
<img src="https://www.svgrepo.com/show/354202/postman-icon.svg" alt="spring" width="15" height="15"/> [Postman](https://postman.com/) - Software utilizzato per interrogare le rotte dell'applicazione.  
<img src="https://www.pngkit.com/png/full/380-3802267_ia-writer-icon-ia-writer.png" alt="java" width="15" height="15"/> [iA Writer](https://ia.net/it/writer/) - Software di videoscrittura, utilizzato per formattare questo file README.  

### Attenzione

> Per evitare i lunghi tempi di attesa per ricevere un token di autenticazione, il collegamento alle API avviene tramite un proxy. E' comunque possibile cambiare gli URL di riferimento, presenti tutti nella stessa [classe](https://github.com/ivanpacenti/progetto_oop/blob/main/Twitter_metrics/src/main/java/it/univpm/progetto/service/DataService.java).
L'applicazione sfrutta le API Standard versione 1.1, non si assicura la compatibilità con versioni successive.


<p align="right">(<a href="#top">torna all'inizio</a>)</p>



<!-- GETTING STARTED -->
## Per iniziare


### Prerequisiti

Occorre almeno aver installato il software [Java](https://www.java.com/) per eseguire il pacchetto `twitter_metrics.jar`.
Altrimenti, in caso si desideri avere una visione dettagliata del codice sorgente, occorre un IDE come [Eclipse](https://www.eclipse.org/).
Per il testing delle rotte è consigliato un software come [Postman](https://postman.com/), ma volendo basta anche un browser qualsiasi.
 


### Installazione
* Digitare il seguente comando da terminale per scaricare la cartella:   
`git clone https://github.com/ivanpacenti/progetto_oop`
 * Importare il progetto in un IDE, ad esempio Eclipse
 * Avviare l'applicazione come Spring Boot App

Oppure:  
* Scaricare il file `twitter_metrics.jar` disponibile [qui](https://github.com/ivanpacenti/progetto_oop/releases/tag/v1.0.0).
* Aprire un terminale e spostarsi nella cartella di download del file
* Digitare il comando `java -jar twitter_metrics.jar`

A questo punto, con il programma in esecuzione, è possibile utilizzare un software come Postman per interrogare le varie rotte elencate nella sezione apposita.  
Si ricorda che di default l'applicazione è impostata per l'ascolto sulla porta 8080, quindi se avete altri servizi già attivi riceverete un messaggio di errore e il programma non si avvierà.  
Per risolvere il problema potete cambiare la porta di ascolto intervenendo su questo [file](https://github.com/ivanpacenti/progetto_oop/blob/main/Twitter_metrics/src/main/resources/application.properties), aggiungendo la riga
`server.port=XXXX` e sostituendo `XXXX` con la porta che volete utilizzare.  
Se utilizzate il file `twitter_metrics.jar` dovrete per forza ricompilare il pacchetto, posizionandovi dentro la cartella `Twitter_metrics` ed eseguendo il comando `mvn package`. 


<p align="right">(<a href="#top">torna all'inizio</a>)</p>


<!-- USAGE EXAMPLES -->
## Utilizzo

## Rotte

| Tipo | Rotta | Descrizione |
|:--|:--|:--|
| **GET** |[`‌/search/accounts`](#accountslist) | Restituisce un elenco di account relativi alla query inserita‌ |
| **GET** | [`‌/search/collections`](#collectionslist) | Restituisce una lista di collezioni di tweet, relativa all'id utente inserito‌ |
| **GET** | [`/tweets‌`](#tweets) | Restituisce fino agli ultimi 200 tweet relativi all'id collezione inserito‌ |
| **GET** | [`‌/collections`](#collections) | ‌Restituisce i tweet contenuti nella collezione di cui si inserisce l'id |
| **GET** | [`/filter‌`](#filter) | ‌Filtra l'ultima lista di tweet scaricata |
| **GET** | [`‌/filter/date`](#date) | Restituisce dei tweet filtrati a seconda dei parametri inseriti, insieme a delle statistiche ‌ |
| **GET** | [`‌/metadata/{type}`](#metadata) | Restituisce i metadati relativi alla path variable inserita‌ |

## Specifiche tecniche
#### <a name="accountslist">`GET /search/accounts`</a>

| Parametro | Obbligatorio | Descrizione|
|:--|:--|:--|
| query | si | Una parola chiave associata all'utente che stiamo cercando. |

<details><summary>Modello:</summary>
  
```yaml
{
        "id": "1154170778",
        "name": "UnivPM",
        "username": "@UnivPoliMarche",
        "followers": 4206,
        "following": 1016,
        "listed": 0,
        "statuses": 6881
}
```
</details>

---

#### <a name="collectionslist">`GET /search/collections`</a>

| Parametro | Obbligatorio | Descrizione|
|:--|:--|:--|
| id | si | Identificativo dell'account di cui vogliamo visualizzare le collezioni di tweet, se presenti (es. 1154170778). |

<details><summary>Modello:</summary>
  
```yaml
{  
    "Welcome Home Tim": {
        "name": "Welcome Home Tim",
        "description": "Return to Earth of ESA astronaut Time Peake, NASA Tim Kopra and Commander Yuri, 18 June",
        "id": "custom-744050218728046592"
    },
    "Couture in orbit": {
        "name": "Couture in orbit",
        "description": "Space-inspired fashion on show on 25 May at the London Science Museum with ESA and top European fashion schools ",
        "id": "custom-727493833076912128"
    }
}
```
</details>

---

#### <a name="tweets">`GET /tweets‌`</a>

| Parametro | Obbligatorio | Descrizione|
|:--|:--|:--|
| id | no | Identificativo dell'account di cui vogliamo visualizzare i tweet (es. 1154170778). |
| count | no | Numero di tweet che vogliamo visualizzare, il massimo è 200 e in sua assenza se ne scaricheranno 50. Twitter conta nel totale anche i retweets, anche se effettivamente non vengono scaricati. |
| replies | no | Valore booleano, impostare true se si vogliono scaricare anche i tweet di risposta. Il valore di default è false. |
| retweets | no | Valore booleano, impostare true se si vogliono scaricare anche i retweet. Il valore di default è false.|

<details><summary>Modello:</summary>
  
```yaml
{
            "created_at": "Mon Dec 06 12:17:55 +0100 2021",
            "id": "1467815563707789323",
            "text": "Non mancate all'evento di festeggiamento dei 10 anni del la mattina del 20/12! Parleremo di  ",
            "entities": {
                "hashtags": [
                    {
                        "text": "#ricerca"
                    }
                ],
                "mentions": [
                    {
                        "name": "DIISM",
                        "username": "@DIISM_UNIVPM",
                        "id": "1144554916142309376"
                    }
                ]
            },
            "stats": {
                "username": "@UnivPoliMarche",
                "followers": 4206,
                "following": 1016,
                "listed": 0,
                "likes": 0,
                "retweets": 5,
                "hashtags": 1,
                "mentions": 2,
                "engagement": 0.11888
            }
```
</details>

---

#### <a name="collections">`GET /collections`</a>

| Parametro | Obbligatorio | Descrizione|
|:--|:--|:--|
| timeline | si | Identificativo della collezione di tweet che vogliamo visualizzare (es. custom-1152025267801661440). |
| count | no | Numero di tweet che vogliamo visualizzare, il massimo è 200 e in sua assenza se ne scaricheranno 50. |

Modello: uguale a tweet.

---

#### <a name="filter">`GET /filter‌`</a>

| Parametro | Obbligatorio | Descrizione|
|:--|:--|:--|
| field | si | Campo del tweet che vogliamo utilizzare come filtro. |
| op | si | Operatore per filtrare, accettati solo > < ==.|
| val | si | Valore che si vuole usare per filtrare i tweet. |

Modello: uguale a tweet.
<details> <summary>Utilizzo del filtro:</summary>
  
E' possibile utilizzare nel campo `field` le seguenti proprietà:
* `id`
* `likes`
* `retweets`
* `engagement`

Si possono visualizzare i valori maggiori, minori o uguali della proprietà scelta, rispetto al valore immesso nel campo `val`.  
Il filtro funziona sull'ultima lista o collezione di tweet scaricata. Verrà lanciata un'eccezione in caso di assenza di valori da filtrare.
</details>


---

#### <a name="date">`GET /filter/date`</a>

| Parametro | Obbligatorio | Descrizione|
|:--|:--|:--|
| from_hour | no | Accetta solo valori del tipo HH, inserire per visualizzare i tweet creati dopo l'ora inserita fino alla mezzanotte dello stesso giorno. |
| to_hour | no | Accetta solo valori del tipo HH, inserire per visualizzare i tweet creati prima dell'ora inserita dalla mezzanotte del giorno prima. |
| from_day | no | Accetta solo valori del tipo dd mm yy, inserire per visualizzare i tweet creati dopo la data inserita.|
| to_day | no | Accetta solo valori del tipo dd mm yy, inserire per visualizzare i tweet creati prima della data inserita |

<details><summary>Modello:</summary>
  
```yaml
"analytics": {
        "Tweets analized": 26,
        "Average engagement": 0.06767038461538462,
        "Variance of engagement": 0.005117544842159764,
        "Higher engagement": 0.15908,
        "Lower engagement": 0.0
    },
    "tweets": [
        "created_at": "Tue Dec 14 09:53:42 +0100 2021",
            "id": "1470678371597684992",
            "text": "Oggi in Ambasciata firma dell’Accordo di collaborazione nel campo della perinatalità tra e le Universit…",
            "entities": {
                "hashtags": [],
                "mentions": [
                    {
                        "name": "Italy in France",
                        "username": "@ItalyinFrance",
                        "id": "968165153766134786"
                    },
                    {
                        "name": "Univ. Paris-Saclay",
                        "username": "@UnivParisSaclay",
                        "id": "2238902785"
                    }
                ]
            },
            "stats": {
                "username": "@UnivPoliMarche",
                "followers": 4206,
                "following": 1016,
                "listed": 0,
                "likes": 0,
                "retweets": 13,
                "hashtags": 0,
                "mentions": 2,
                "engagement": 0.15908
            }            
        }
]
```

 </details>
<details> <summary>Utilizzo del filtro:</summary>
  
E' possibile utilizzare i parametri di questa rotta in modo completamente flessibile.  
Ad esempio, possiamo utilizzare `from_hour` con un valore di 18: visualizzeremo solo i tweet creati dopo le 18 di qualsiasi giorno.  
Aggiungendo il parametro `to_hour`, ad esempio con un valore di 22, visualizzeremo i tweet creati nella fascia oraria 18-22 di qualsiasi giorno.     
Lo stesso metodo funziona per i parametri `from_day` e `to_day`, con il formato dd mm yy, che possiamo aggiungere ai parametri precedenti o utilizzare in autonomia.  
Esempio:   
`from_hour`: 18  
`to_hour`: 22   
`from_day`: 01 11 21   
`to_day`: 31 11 21   
Avremo i tweets creati dallo 01 al 31 novembre, compresi nella fascia oraria 18-22.   
Il filtro funziona sull'ultima lista o collezione di tweet scaricata. Verrà lanciata un'eccezione in caso di assenza di valori da filtrare.
</details>

---

#### <a name="metadata">`GET /metadata/{type}`</a>

| Path Variable | Descrizione|
|:--|:--|
| /accounts | Visualizza i metadati della rotta `/search/accounts` |  
| /collections | Visualizza i metadati della rotta `/collections`  |  
| /tweets | Visualizza i metadati della rotta `/tweets`  |  
| /analytics | Visualizza i metadati della rotta `/filter/date`  |  

Output: descrizione delle proprietà JSON, con alias e tipo di valore.


<p align="right">(<a href="#top">torna all'inizio</a>)</p>

## Eccezioni
Sono state utilizzate le seguenti eccezioni personalizzate:

<details><summary>EmptyCollectionListException</summary>
  
 Lanciata in caso di lista vuota in output, per specificare che non ci sono stati errori ma non c'è nulla da visualizzare.</details>
<details><summary>InvalidHourException</summary>
 
 Lanciata in caso nella rotta [`‌/filter/date`](#date) venga data in input un' ora di formato diverso da quello richiesto.</details>
<details><summary>InvalidDateException</summary>
  
 Lanciata in caso nella rotta [`‌/filter/date`](#date) venga data in input una data di formato diverso da quello richiesto.</details>
<details><summary>InputStreamException</summary>
 
 Lanciata in caso si tenti di visualizzare i tweet di un utente con profilo privato, o più in generale se non sono presenti tweet o collezioni.</details>
<details><summary>InvalidFilterException</summary>
 
 Lanciata in caso nella rotta [`/filter‌`](#filter) venga dato in input un campo non esistente.</details>

<p align="right">(<a href="#top">torna all'inizio</a>)</p>

## Test
Sono stati implementati i seguenti test sfruttando JUnit 5:

<details><summary>ConnectionTest</summary>Test di connessione all'API di twitter, verifica che i dati scaricati non siano nulli.</details>
<details><summary>PrivateUserTest</summary>Viene utilizzato un id utente privato per verificare il funzionamento dell'eccezione apposita.</details>
<details><summary>DateExceptionTest</summary>Si immette una data in modo volutamente scorretto per verificare il lancio dell'eccezione.</details>
<details><summary>HourExceptionTest</summary>Si testa il filtro orario con un valore che sfora le 24 ore, per forzare l'eccezione.</details>

<p align="right">(<a href="#top">torna all'inizio</a>)</p>

## Documentazione

Il Javadoc del progetto è disponibile [qui](https://github.com/ivanpacenti/progetto_oop/tree/main/doc).

<p align="right">(<a href="#top">torna all'inizio</a>)</p>

<!-- CONTRIBUTI -->
## Contributi

Il progetto è stato realizzato interamente da Ivan Pacenti.  

[![Linkedin](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/ipacenti/) [![Github](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/ivanpacenti?tab=repositories)

<p align="right">(<a href="#top">torna all'inizio</a>)</p>
