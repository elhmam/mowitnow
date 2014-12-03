<h1>Mini-projet Mowitnow</h1>
<h2>Sp&eacute;cifications</h2>
La soci&eacute;t&eacute; MowItNow a d&eacute;cid&eacute; de d&eacute;velopper une tondeuse à gazon automatique, destin&eacute;e aux surfaces rectangulaires.

La tondeuse peut être programm&eacute;e pour parcourir l'int&eacute;gralit&eacute; de la surface.
La position de la tondeuse est repr&eacute;sent&eacute;e par une combinaison de coordonn&eacute;es (x,y) et d'une lettre indiquant l'orientation selon la notation cardinale anglaise (N,E,W,S). La pelouse est divis&eacute;e en grille pour simplifier la navigation. 

Par exemple, la position de la tondeuse peut être « 0, 0, N », ce qui signifie qu'elle se situe dans le coin inf&eacute;rieur gauche de la pelouse, et orient&eacute;e vers le Nord.

Pour contrôler la tondeuse, on lui envoie une s&eacute;quence simple de lettres. Les lettres possibles sont « D », « G » et « A ». « D » et « G » font pivoter la tondeuse de 90° à droite ou à gauche respectivement, sans la d&eacute;placer. « A » signifie que l'on avance la tondeuse d'une case dans la direction à laquelle elle fait face, et sans modifier son orientation.

Si la position après mouvement est en dehors de la pelouse, la tondeuse ne bouge pas, conserve son orientation et traite la commande suivante. 

On assume que la case directement au Nord de la position (x, y) a pour coordonn&eacute;es (x, y+1).

Pour programmer la tondeuse, on lui fournit un fichier d'entr&eacute;e construit comme suit :
<ul>
<li>	La première ligne correspond aux coordonn&eacute;es du coin sup&eacute;rieur droit de la pelouse, celles du coin inf&eacute;rieur gauche sont suppos&eacute;es être (0,0)</li>
<li>	La suite du fichier permet de piloter toutes les tondeuses qui ont &eacute;t&eacute; d&eacute;ploy&eacute;es. Chaque tondeuse a deux lignes la concernant :
<ul>
<li>	la première ligne donne la position initiale de la tondeuse, ainsi que son orientation. La position et l'orientation sont fournies sous la forme de 2 chiffres et une lettre, s&eacute;par&eacute;s par un espace</li>
<li>	la seconde ligne est une s&eacute;rie d'instructions ordonnant à la tondeuse d'explorer la pelouse. Les instructions sont une suite de caractères sans espaces.</li>
</li>
</ul>
Chaque tondeuse se d&eacute;place de façon s&eacute;quentielle, ce qui signifie que la seconde tondeuse ne bouge que lorsque la première a ex&eacute;cut&eacute; int&eacute;gralement sa s&eacute;rie d'instructions.

Lorsqu'une tondeuse achève une s&eacute;rie d'instruction, elle communique sa position et son orientation.

<h3>OBJECTIF</h3>
Concevoir et &eacute;crire un programme s'ex&eacute;cutant sur une JVM ≥ 1.7 ou un serveur node.js, et impl&eacute;mentant la sp&eacute;cification ci-dessus et passant le test ci-après

<h3>TEST</h3>
Le fichier suivant est fourni en entr&eacute;e :<br>
5 5<br>
1 2 N<br>
GAGAGAGAA<br>
3 3 E<br>
AADAADADDA<br>

On attend le r&eacute;sultat suivant (position finale des tondeuses) :<br>
1 3 N<br>
5 1 E<br>

NB: Les donn&eacute;es en entr&eacute;e peuvent être inject&eacute;e sous une autre forme qu'un fichier (par exemple un test automatis&eacute;).

<h2>Pr&eacute;requis</h2>
Maven
JVM ≥ 1.7

<h2>R&eacute;pertoires</h2>
<ul>
<li><b>mowitnow-core :</b>Impl&eacute;mentation des sp&eacute;cifications.</li>
<li><b>mowitnow-web :</b> Interface web permettant de tester en ligne le projet.</li>
</ul>

<h2>R&eacute;pertoires</h2>
<ul>
<li><b>mowitnow-core :</b>Impl&eacute;mentation des sp&eacute;cifications.</li>
<li><b>mowitnow-web :</b> Interface web permettant de tester en ligne le projet.</li>
</ul>
