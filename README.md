#### Class Platform
***
Créer les talons pour les objets appelés à distance, (je ne sais pas s'il faut copier les stub et les Skel dans les dossiers consumer et producer à chaque fois qu'on les crée)
```
javac PlatformInterface.java Platform.java PlatformServer.java
rmic -vcompat Platform
``` 

Faire l'etape 1 de Producer/Consumer
```
rmiregistry -J-Djava.security.policy=serveur.policy 2001 & 
java PlatformServer
```
Cla afin de préciser 2001, car il prend 10.. par défaut

#### Class Producer
***
```
javac PlatformInterface.java Producer.java
java Producer
```

#### Class Consumer 
***
```
javac PlatformInterface.java Consumer.java
java Consumer
```
