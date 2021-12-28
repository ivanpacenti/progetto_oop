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
    <li><a href="#contributi">Contributi</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## Introduzione

Progetto creato per l'esame di Programmazione ad oggetti 2021\2022 dell'Università Politecnica delle Marche.
L'obiettivo era di analizzare le metriche di engagement di una lista di tweet dell'Università, ma si è deciso di ampliare il servizio a qualsiasi account pubblico.


<p align="right">(<a href="#top">back to top</a>)</p>



### Strumenti utilizzati

### [Java](https://www.java.com/)
### [Eclipse](https://www.eclipse.org/)
### [Spring Boot](https://spring.io/projects/spring-boot)
### [Postman](https://postman.com/)
### [iA Writer](https://ia.net/it/writer/)

### Attenzione

> Per evitare i lunghi tempi di attesa per ricevere un token di autenticazione, il collegamento alle API avviene tramite un proxy. E' comunque possibile cambiare gli URL di riferimento, presenti tutti nella stessa classe.
L'applicazione sfrutta le API Standard versione 1.1, non si assicura la compatibilità con versioni successive.


<p align="right">(<a href="#top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Per iniziare


### Prerequisiti

Occorre almeno aver installato il software [Java](https://www.java.com/) per eseguire il pacchetto `twitter_metrics.jar`.
Altrimenti, in caso si desideri avere una visione dettagliata del codice sorgente, occorre un IDE come [Eclipse](https://www.eclipse.org/).
Per il testing delle rotte è consigliato un software come [Postman](https://postman.com/), ma volendo basta anche un browser qualsiasi.
 


### Installazione
### Digitare il seguente comando da terminale per scaricare la cartella:   
`git clone https://github.com/ivanpacenti/progetto_oop`
### Importare il progetto in un IDE, ad esempio Eclipse
### Avviare l'applicazione come Spring Boot App

Oppure:
### Scaricare il file `twitter_metrics.jar` dentro la cartella `target/`
### Aprire un terminale e spostarsi nella cartella di download del file
### Digitare il comando `java -jar twitter_metrics.jar`

A questo punto, con il programma in esecuzione, è possibile utilizzare un software come Postman per interrogare le varie rotte elencate nella sezione apposita.  
Si ricorda che di default l'applicazione è impostata per l'ascolto sulla porta 8080, quindi se avete altri servizi già attivi riceverete un messaggio di errore e il programma non si avvierà.  
Per risolvere il problema potete cambiare la porta di ascolto intervenendo sul file `progetto_oop/Twitter_metrics/src/main/resources/application.properties`, aggiungendo la riga
`server.port=XXXX` e sostituendo `XXXX` con la porta che volete utilizzare.  
Se utilizzate il file `twitter_metrics.jar` dovete per forza ricompilare il pacchetto, posizionandovi dentro la cartella `Twitter_metrics` ed eseguendo il comando `mvn package`. 


<p align="right">(<a href="#top">back to top</a>)</p>


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

#### <a name="collectionslist">`GET /search/collections`</a>

| Parametro | Obbligatorio | Descrizione|
|:--|:--|:--|
| id | si | Identificativo dell'account di cui vogliamo visualizzare le collezioni di tweet, se presenti. |

#### <a name="tweets">`GET /tweets‌`</a>

| Parametro | Obbligatorio | Descrizione|
|:--|:--|:--|
| id | no | Identificativo dell'account di cui vogliamo visualizzare i tweet. |
| count | no | Numero di tweet che vogliamo visualizzare, il massimo è 200 e in sua assenza se ne scaricheranno 50. Twitter conta nel totale anche i retweets, anche se effettivamente non vengono scaricati. |
| replies | no | Valore booleano, impostare true se si vogliono scaricare anche i tweet di risposta. Il valore di default è false. |
| retweets | no | Valore booleano, impostare true se si vogliono scaricare anche i retweet. Il valore di default è false.|

#### <a name="collections">`GET /collections`</a>

| Parametro | Obbligatorio | Descrizione|
|:--|:--|:--|
| timeline | si | Identificativo della collezione di tweet che vogliamo visualizzare. |
| count | no | Numero di tweet che vogliamo visualizzare, il massimo è 200 e in sua assenza se ne scaricheranno 50. |

#### <a name="filter">`GET /filter‌`</a>

| Parametro | Obbligatorio | Descrizione|
|:--|:--|:--|
| field | si | Campo del tweet che vogliamo utilizzare come filtro. |
| op | si | Operatore per filtrare, accettati solo > < ==.|
| val | si | Valore che si vuole usare per filtrare i tweet. |


#### <a name="date">`GET /filter/date`</a>

| Parametro | Obbligatorio | Descrizione|
|:--|:--|:--|
| from_hour | no | Accetta solo valori del tipo HH, inserire per visualizzare i tweet creati dopo l'ora inserita fino alla mezzanotte dello stesso giorno. |
| to_hour | no | Accetta solo valori del tipo HH, inserire per visualizzare i tweet creati prima dell'ora inserita dalla mezzanotte del giorno prima. |
| from_day | no | Accetta solo valori del tipo dd mm yy, inserire per visualizzare i tweet creati dopo la data inserita.|
| to_day | no | Accetta solo valori del tipo dd mm yy, inserire per visualizzare i tweet creati prima della data inserita |
 
	 
#### <a name="metadata">`GET /metadata/{type}`</a>

| Path Variable | Descrizione|
|:--|:--|
| /accounts | Visualizza i metadati della rotta `/search/accounts` |  
| /collections | Visualizza i metadati della rotta `/collections`  |  
| /tweets | Visualizza i metadati della rotta `/tweets`  |  
| /analytics | Visualizza i metadati della rotta `/filter/date`  |  



<p align="right">(<a href="#top">back to top</a>)</p>


<!-- CONTRIBUTI -->
## Contributi

Il progetto è stato realizzato interamente da Ivan Pacenti.


<p align="right">(<a href="#top">back to top</a>)</p>


<!-- CONTACT -->
## Contatti


 [https://github.com/ivanpacenti/progetto_oop](https://github.com/ivanpacenti/progetto_oop)

<p align="right">(<a href="#top">back to top</a>)</p>






<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/github_username/repo_name.svg?style=for-the-badge
[contributors-url]: https://github.com/github_username/repo_name/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/github_username/repo_name.svg?style=for-the-badge
[forks-url]: https://github.com/github_username/repo_name/network/members
[stars-shield]: https://img.shields.io/github/stars/github_username/repo_name.svg?style=for-the-badge
[stars-url]: https://github.com/github_username/repo_name/stargazers
[issues-shield]: https://img.shields.io/github/issues/github_username/repo_name.svg?style=for-the-badge
[issues-url]: https://github.com/github_username/repo_name/issues
[license-shield]: https://img.shields.io/github/license/github_username/repo_name.svg?style=for-the-badge
[license-url]: https://github.com/github_username/repo_name/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/linkedin_username
[product-screenshot]: 











<table>
<tbody>
<tr>
<th>Endpoint</th>
<th>Risposte</th>
</tr>
<tr>
<td><img src="https://img.shields.io/badge/GET-%2Fsearch/accounts-success" alt="Italian Trulli" /></td>
<td><details> <summary>Click to expand!</summary> ## Heading con questo ho usato prova </details></td>
</tr>
<tr>
<td><img src="https://img.shields.io/badge/GET-%2Fsearch/collections-success" alt="Italian Trulli" /></td>
<td><details> <summary>Click to expand!</summary> ## Heading con questo ho usato prova </details></td>
</tr>
<tr>
<td><img src="https://img.shields.io/badge/GET-%2Ftweets-success" alt="Italian Trulli" /></td>
<td><details> <summary>Click to expand!</summary> ## Heading con questo ho usato prova </details></td>
</tr>
  <tr>
<td><img src="https://img.shields.io/badge/GET-%2Fcollections-success" alt="Italian Trulli" /></td>
<td><details> <summary>Click to expand!</summary> ## Heading con questo ho usato prova </details></td>
</tr>
<tr>
<td><img src="https://img.shields.io/badge/GET-%2Ffilter-success" alt="Italian Trulli" /></td>
<td><details> <summary>Click to expand!</summary> ## Heading con questo ho usato prova </details></td>
</tr>
<tr>
<td><img src="https://img.shields.io/badge/POST-%2Ffilter/parameter-blue" alt="Italian Trulli" /></td>
<td><details> <summary>Click to expand!</summary> ## Heading con questo ho usato prova </details></td>
</tr>

</tbody>
</table>
