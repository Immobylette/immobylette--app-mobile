# Immobylette Mobile App

## Contexte du projet

Ce repository s'inscrit dans le cadre du projet Immobylette qui vise à réaliser une application mobile android de réalisation d'état des lieux. Cette application métier doit pouvoir être utilisé par une agence immobilière.

## Architecture

Tech stack du projet : 
* Kotlin (jetpack compose)
* Gradle

Librairies utilisées : 
* Retrofit
* com.google.gson

Version du SDK Android : **34**

Permissions requises : 
* Géolocalisation
* Prise de photos


L'architecture utilisée dans le projet est une architecture MVVM (Model View ViewModel). Elle a pour objectif de venir séparer la logique de l'application en 3 couches distinctes.

![Architecture MVVM](https://miro.medium.com/v2/resize:fit:813/1*j9-O4DcaYTBTlSjckaFqXA.png "Architecture MVVM").

Par ailleurs, pour venir bien séparer les dossiers au sein du projet, le choix s'est porté sur une structure de dossiers par fonctionnalités.

## Fonctionnement

### Récupération des données de l'api

Pour que l'application mobile soit capable de requêter l'api immobylette, nous allons utiliser la librairie **Retrofit**.

Pour chacune des routes de l'api, nous allons créer le service Retrofit qui y est associé par exemple : 

```kotlin
interface AgentService {
    @GET("third-parties/agents")
    suspend fun getAllAgents(): List<ThirdPartyDto>
}
```

Nous pouvons ainsi définir les endpoints avec les types de retours, les méthodes HTTP ainsi que les paramètres.

Nous devons maintenant recenser notre service auprès de retrofit

```kotlin
val agentService: AgentService = retrofitClient.create(AgentService::class.java)
```

### Data Transfer Objects des réponses de l'api

Nous allons définir pour chacune des réponses de l'api, le DTO qui lui est associée en créer des data class exemple : 

```kotlin
data class ElementDto(
    val id: UUID,
    val name: String,
    val description: String?,
    val type: String,
    val photo: URL,
    val attributes: Map<String, String>,

    @SerializedName("base_photos")
    val basePhotos: List<PhotoUrlDto>,

    @SerializedName("previous_photos")
    val previousPhotos: List<PhotoUrlDto>,
)
```

On note ici l'utilisation de la librairie `com.google.gson` pour pouvoir désérialiser les champs json.

### Navigation

Toutes les pages disponibles via la navigation sont disponibles dans le fichier `MainActivity.kt`. A chaque page y est associée un fichier de navigation. Et à chaque navigation y est associée une chaîne de caractères représentant la route de la page exemple : `property-selection`

### Pages

Les pages de l'application sont la réplique de la maquette figma réalisée. Au sein des pages sont utilisés des composants (parties du code réutillisables). Les composables des pages ont été réalisées de telle sorte qu'elles ne réalisent pas elles-mêmes des appels au ViewModel auxquelles elles sont associées : elles prennent en paramètre des callbacks responsable des actions à réaliser. Ainsi la logique est déportée plus haut, les pages ne sont responsables que de l'affichage

### ViewModels

On va distinguer 2 types de ViewModels : 

* Les shared ViewModels
* Les normaux

Les shared ViewModels ont pour objectif de pouvoir partager de l'information entre plusieurs pages cela peut être utile si par exemple on a besoin de garder tout au long d'un processus, un id qui pourraît être utilisé au sein de plusieurs requêtes sur plusieurs pages

En ce qui concerne les autres ViewModels, ils vont être le recueil des actions utilisateurs : si par exemple un utilisateur clique sur un bouton d'une page, c'est une fonction du ViewModel qui sera utilisé et qui va peut être réaliser un appel api. Toute la logique métier de l'application va se situer dans ses fichiers.

### State

Les données que l'on reçoit de l'api sont des données différentes de celles que l'on va concrètement utiliser dans notre application. De ce fait, nous allons définir des `data class` qui vont accueillir les états de notre application. Par exemple : 

```kotlin
data class PropertyListState(
    val properties: List<PropertyState> = listOf(),
    val pagination: PaginationState = PaginationState()
)
```

### Mapping

Une étape est nécessaire entre le DTO et les states : étant donné que ces `data class` sont de natures différentes, une étape de mapping est nécessaire. Par exemple : 

```kotlin
fun PropertyDto.toState() = PropertyState(
    id = id,
    nbRooms = nbRooms,
    propertyType = propertyType,
    propertyClass = propertyClass,
    owner = owner,
    currentTenant = currentTenant,
    address = address,
    currentInventory = currentInventory != null,
    currentInventoryId = currentInventory,
    photo = URL(photo)
)
```

## Build de l'application

### Pré-requis

Pour que l'application fonctionne normalement, nous avons besoin de 3 fichiers : 
* api.properties
* keystore.properties
* my-release-key.jks

Le fichier **api.properties** va devoir contenir 2 valeurs : 
* API_URL qui sera l'URL de l'api à appeler
* API_KEY clé api pour pouvoir s'authentifier auprès de l'api

Le fichier **my-release-key.jks** va contenir la clé de signature de l'apk en base64

Le fichier **keystore.properties** va devoir contenir 4 valeurs : 
* keyAlias alias de la clé de signature
* keyPassword mot de passe de la clé de signature
* storeFile nom du fichier de la clé
* storePassword mot de passe du fichier de cla clé

### Build

Pour compiler le projet et obtenir le .apk, il est nécessaire d'exécuter la commande suivante : 

```bash
./gradlew clean assembleRelease
```

Puis l'apk sera disponible  à l'emplacement : `app/build/outputs/apk/release/app-release.apk`
