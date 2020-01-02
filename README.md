<p> D'abord, le problème est que les producteurs et les consommateurs n'ont pas accès à Platform et cela à cause de la politique de sécurité.<br> Une "java.security.AccessControlException: access denied ("java.net.SocketPermission" "127.0.0.1:2001" "connect,resolve")" est levée car quelque part je n'ai pas défini les bons droits. <br> Les lignes utilisant tampon renvoient un NullPointerException. <br> On doit régler cela demain matin. </p>

<p>Pour exécuter ce projet :<br>
Il faut ouvrir 3 terminaux : </p>

- Platform : 
<ol>
    <li> javac PlatformInterface.java Platform.java PlatformServer.java </li>
    <li> rmic -vcompat Platform : créer les talons pour les objets appelés à distance </li>
    <li> Faire l'etape 1 de Producer/Consumer </li>
    <li> rmiregistry -J-Djava.security.policy=serveur.policy 2001 & : cela afin de préciser 2001, car il prend 10.. par défaut </li>
    <li> java PlatformServer </li>
</ol>

 - Producer : 
 <ol>
    <li> javac PlatformInterface.java Producer.java</li>
    <li> java -Djava.security.policy=producer.policy Producer </li>
</ol>

 - Consumer : n'est pas totalement exploitable !!! </p>
  <ol>
    <li> javac PlatformInterface.java Consumer.java</li>
    <li> java -Djava.security.policy=consumer.policy Consumer </li>
</ol>
