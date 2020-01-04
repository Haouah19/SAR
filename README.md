- Platform : 
<ol>
    <li> javac PlatformInterface.java Platform.java PlatformServer.java </li>
    <li> rmic -vcompat Platform : créer les talons pour les objets appelés à distance, (je ne sais pas s'il faut copier les stub et les Skel dans les dossiers consumer et producer à chaque fois qu'on les crée)</li>
    <li> Faire l'etape 1 de Producer/Consumer </li>
    <li> rmiregistry -J-Djava.security.policy=serveur.policy 2001 & : cela afin de préciser 2001, car il prend 10.. par défaut </li>
    <li> java PlatformServer </li>
</ol>

 - Producer : 
 <ol>
    <li> javac PlatformInterface.java Producer.java</li>
    <li> java Producer </li>
</ol>

 - Consumer : n'est pas totalement exploitable !!! </p>
  <ol>
    <li> javac PlatformInterface.java Consumer.java</li>
    <li> java Consumer </li>
</ol>
