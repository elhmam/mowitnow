# Mini-projet Mowitnow
## Sp&eacute;cifications
<pre>
La soci&eacute;t&eacute; MowItNow a d&eacute;cid&eacute; de d&eacute;velopper une tondeuse &agrave; gazon automatique, destin&eacute;e aux surfaces rectangulaires.

La tondeuse peut être programm&eacute;e pour parcourir l'int&eacute;gralit&eacute; de la surface.
La position de la tondeuse est repr&eacute;sent&eacute;e par une combinaison de coordonn&eacute;es (x,y) et d'une lettre indiquant l'orientation selon la notation cardinale anglaise (N,E,W,S). La pelouse est divis&eacute;e en grille pour simplifier la navigation. 

Par exemple, la position de la tondeuse peut être « 0, 0, N », ce qui signifie qu'elle se situe dans le coin inf&eacute;rieur gauche de la pelouse, et orient&eacute;e vers le Nord.

Pour contr&ocirc;ler la tondeuse, on lui envoie une s&eacute;quence simple de lettres. Les lettres possibles sont « D », « G » et « A ». « D » et « G » font pivoter la tondeuse de 90° &agrave; droite ou &agrave; gauche respectivement, sans la d&eacute;placer. « A » signifie que l'on avance la tondeuse d'une case dans la direction &agrave; laquelle elle fait face, et sans modifier son orientation.

Si la position apr&egrave;s mouvement est en dehors de la pelouse, la tondeuse ne bouge pas, conserve son orientation et traite la commande suivante. 

On assume que la case directement au Nord de la position (x, y) a pour coordonn&eacute;es (x, y+1).

Pour programmer la tondeuse, on lui fournit un fichier d'entr&eacute;e construit comme suit :

La premi&egrave;re ligne correspond aux coordonn&eacute;es du coin sup&eacute;rieur droit de la pelouse, celles du coin inf&eacute;rieur gauche sont suppos&eacute;es être (0,0)
La suite du fichier permet de piloter toutes les tondeuses qui ont &eacute;t&eacute; d&eacute;ploy&eacute;es. Chaque tondeuse a deux lignes la concernant :
la premi&egrave;re ligne donne la position initiale de la tondeuse, ainsi que son orientation. La position et l'orientation sont fournies sous la forme de 2 chiffres et une lettre, s&eacute;par&eacute;s par un espace</li>
la seconde ligne est une s&eacute;rie d'instructions ordonnant &agrave; la tondeuse d'explorer la pelouse. Les instructions sont une suite de caract&egrave;res sans espaces.

Chaque tondeuse se d&eacute;place de façon s&eacute;quentielle, ce qui signifie que la seconde tondeuse ne bouge que lorsque la premi&egrave;re a ex&eacute;cut&eacute; int&eacute;gralement sa s&eacute;rie d'instructions.

Lorsqu'une tondeuse ach&egrave;ve une s&eacute;rie d'instruction, elle communique sa position et son orientation.

OBJECTIF
Concevoir et &eacute;crire un programme s'ex&eacute;cutant sur une JVM ≥ 1.7 ou un serveur node.js, et impl&eacute;mentant la sp&eacute;cification ci-dessus et passant le test ci-apr&egrave;s

TEST
Le fichier suivant est fourni en entr&eacute;e :<br>
5 5
1 2 N
GAGAGAGAA
3 3 E
AADAADADDA

On attend le r&eacute;sultat suivant (position finale des tondeuses) :
1 3 N
5 1 E

NB: Les donn&eacute;es en entr&eacute;e peuvent être inject&eacute;e sous une autre forme qu'un fichier (par exemple un test automatis&eacute;).

</pre>
## Pr&eacute;-requis
* Maven
* JVM ≥ 1.7

## R&eacute;pertoires
* __mowitnow-core__ : Impl&eacute;mentation des sp&eacute;cifications.
* __mowitnow-web__ : Interface web permettant de tester en ligne le projet.

## Lancement
<pre>
$ mvn clean install
</pre>
__mowitnow-core__ :
<pre>
$ cd mowitnow-core
$ mvn exec:java
</pre>

__mowitnow-web__ :
<pre>
$ cd mowitnow-web
$ mvn spring-boot:run
$ curl localhost:8089
ou via le navigateur en allant sur http://localhost:8089
</pre>




