# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Stine Szymanek, S362097, s362097@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Stine har hatt ansvar for alle oppgavene.

# Oppgavebeskrivelse

I oppgave 1 sin antall()-metode returneres antall og i tom() har jeg
en sjekk på antall == 0 som returnerer true/false. For konstruktøren
DobbeltLenketListe(T[]) finner vi den første verdien i tabellen som ikke
er null og setter den til hode. Hvis det kun er null-verdier skjer return.
Så leter vi videre etter den neste verdien som ikke er null og setter den som
den neste. Hvis det kun er den ene verdien som ikke er null så settes hale
til den samme og return. Så går vi gjennom resten av tabellen og setter resten
av neste- og forrige-pekere og til slutt settes halen til den siste.

I oppgave 2a) er toString()-metoden den samme som i en ukesoppgave fra kompendiet.
Vi sjekker først at lista ikke er tom slik at vi har et hode, og legger verdien
inn i StringBuilderen. Så har vi en løkke som legger til resten av verdiene, så
lenge det ikke er null. omvendtString()-metoden fungerer på samme måte, bare at
vi starter med halen sin verdi og går gjennom resten av lista ved bruk av forrige-
pekere.

I oppgave 2b) opprettes en ny node med verdien som tas inn i metoden. Hvis listen 
er tom blir både hode og hale satt til den nye noden. Hvis ikke blir hale sin neste-
peker satt til den nye noden og den nye noden sin forrige blir satt til halen. Så blir
halen den nye noden.

I oppgave 3a) sin finnNode(int indeks) har vi en sjekk på om indeksen er mindre enn antall/2. Hvis den er det
går løkka fra starten av lista til vi kommer til noden med riktig indeks, hvis ikke 
går det en løkke fra slutten. I hent(int indeks) returneres verdien til noden man får av
finnNode-metoden. I oppdater-metoden lagres den gamle verdien på indeksen ved bruk av 
finnNode-metoden, den nye verdien settes inn for den noden, og den gamle verdien returneres.

I oppgave 3b) i subliste(fra, til)-metoden opprettes en instans av DobbeltLenketListe.
Deretter sjekkes det om fra og til er det samme for å sjekke om det er et tomt intervall.
I så fall returneres en tom liste. Hvis det ikke er et tomt intervall brukes en løkke til
å finne noden som er på posisjon "fra" og denne legges inn i listen som hode. Deretter er 
det en til løkke som går fra etter "fra" helt til, men ikke med, posisjonen "til" og legger
disse nodene inn i listen. Siden det er leggInn-metoden som brukes, så blir også hale satt.

I oppgave 4 sin indeksTil-metode brukes en løkke til å gå gjennom listen. Hvis det finnes
en node som har den riktige verdien så returneres den indeksen, hvis ikke returneres -1.
inneholder(T verdi)-metoden returnerer true hvis indeksTil-metoden på den verdien ikke er -1,
for hvis den ikke gir -1 betyr det at metoden fant en node som inneholder den verdien.

I oppgave 5 sin leggInn(indeks, verdi)-metode sjekker vi at indeksen er lovlig og oppretter
en ny node med verdien. Hvis lista er tom settes hale og hode til den nye noden. Hvis ikke,
og verdien skal legges først, settes hode sin forrige-peker til den nye noden og den nye noden 
sin neste-peker settes til hodet og så gjør vi den nye noden til hodet. Hvis verdien skal legges
bakerst skjer det omvendte bare med hale. Hvis verdien skal legges mellom to andre verdier brukes
finnNode-metoden for å finne de nodene den nye skal legges mellom og setter pekerne riktig.

I oppgave 6 sin fjern(indeks)-metode sjekker vi om indeksen er 0. Det betyr at det er den første
verdien som skal fjernes. Da sjekker vi om hode sin neste er null, i så fall er det kun en verdi i 
listen og hode og hale blir satt til null slik at det blir en tom liste. Hvis ikke det kun er en 
verdi og vi skal fjerne den første så fjerner vi hode-noden og setter hode til å være den neste i
lista. Hvis det er den siste som skal fjernes så gjør vi det samme bare for hale. Hvis en verdi 
mellom to andre skal fjernes så finner vi de to verdiene og setter pekerne deres på hverandre slik
at ingenting peker på den som skal fjernes. I fjern(verdi)-metoden bruker vi en løkke for å finne
den riktige noden og fjerner den på samme måte som i den andre fjern-metoden.

NB! Oppgave 7 - Jeg lagde nullstill-metoden for at testene til oppgave 8 skulle kjøre, men jeg har 
ikke lett etter den mest effektive måten siden denne oppgaven ikke egentlig måtte gjøres.