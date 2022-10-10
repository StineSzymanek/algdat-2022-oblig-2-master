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

