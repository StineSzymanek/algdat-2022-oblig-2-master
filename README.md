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
