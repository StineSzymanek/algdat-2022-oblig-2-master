package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        hode = hale = null;     // hode og hale settes til null
        antall = 0;     // ingen verdier i listen til å starte med
        endringer = 0;      // ingen endringer har blitt gjort ved start
    }

    public DobbeltLenketListe(T[] a) {
        this();     // nullstiller variablene

        Objects.requireNonNull(a, "Tabellen a er null!");
        if(a.length == 0) return;       // tom liste

        int indeks = 0;
        Node<T> p = null;
        // Finner den første som ikke er null
        for(; indeks < a.length; indeks++) {
            if(a[indeks] != null) {
                p = hode = new Node<>(a[indeks]);
                antall = 1;
                break;
            }
        }
        if(p == null) return;      // ingen verdi som ikke er null, tom liste

        int indeks2 = indeks + 1;
        Node<T> q = null;
        // Finner den neste som ikke er null
        for(; indeks2 < a.length; indeks2++) {
            if(a[indeks2] != null) {
                q = p.neste = new Node<>(a[indeks2]);
                antall++;
                break;
            }
        }
        if(q == null) {     // kun en verdi i listen som ikke er null
            hale = p;
            return;
        }

        for(int i = indeks2+1; i < a.length; i++) {
            if(a[i] != null) {
                q.forrige = p;
                q.neste = new Node<>(a[i]);
                p = q;
                q = q.neste;
                antall++;
            }
        }
        q.forrige = p;
        hale = q;
    }

    private static void fratilKontroll(int antall, int fra, int til)
    {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    public Liste<T> subliste(int fra, int til) {
        fratilKontroll(antall,fra,til);     // sjekker om indeksene fra og til er lovlige

        DobbeltLenketListe<T> liste = new DobbeltLenketListe<>();

        if(fra == til) {        // tomt intervall
            liste.hode = liste.hale = null;
            liste.antall = 0;
            liste.endringer = 0;
            return liste;
        }

        Node<T> p = hode;
        int i = 0;
        for(; i < fra; i++) {      // finner noden på posisjonen "fra", som er hode i subliste
            p = p.neste;
        }
        liste.leggInn(p.verdi);

        for(; i < til - 1; i++) {       // legger inn noder fram til, men ikke med, "til"
            p = p.neste;
            liste.leggInn(p.verdi);
        }
        liste.endringer = 0;

        return liste;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi,"Null kan ikke legges inn i lista");

        Node<T> nyNode = new Node<>(verdi);
        if(tom()) {
            hode = hale = nyNode;
        }
        else {
            hale.neste = nyNode;
            nyNode.forrige = hale;
            hale = nyNode;
        }
        antall++;
        endringer++;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi, "Null kan ikke legges inn i lista");

        // Sjekker indeks
        if(!(indeks >= 0 && indeks <= antall)) {
            throw new IndexOutOfBoundsException("Ikke gyldig indeks");
        }

        Node<T> nyNode = new Node<>(verdi);

        if(tom()) {
            hode = hale = nyNode;
        }

        else if(indeks == 0) {      // Verdien skal legges først
            hode.forrige = nyNode;
            nyNode.neste = hode;
            hode = nyNode;
        }

        else if(indeks == antall) {     // Verdien skal legges bakerst
            hale.neste = nyNode;
            nyNode.forrige = hale;
            hale = nyNode;
        }

        else {      // Verdien skal legges mellom to verdier
            // Finn nodene den nye skal være mellom
            Node<T> p = finnNode(indeks);
            Node<T> q = finnNode(indeks-1);

            p.forrige = nyNode;
            q.neste = nyNode;
            nyNode.forrige = q;
            nyNode.neste = p;
        }
        antall++;
        endringer++;
    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }

    private Node<T> finnNode(int indeks) {
        Node<T> p;
        if(indeks < antall/2) {
            p = hode;
            for(int i = 0; i < indeks; i++) {
                p = p.neste;
            }
        }
        else {
            p = hale;
            for(int i = antall-1; i > indeks; i--) {
                p = p.forrige;
            }
        }
        return p;
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        Node<T> p = hode;
        for(int i = 0; i < antall; i++) {
            if(p.verdi.equals(verdi)) {      // Hvis noden sin verdi er lik verdien, så returneres indeksen
                return i;
            }
            p = p.neste;
        }
        return -1;      // Hvis ingen treffer, så returneres -1
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi, "Ikke legg inn null-verdier");  // null-verdier kan ikke legges inn
        indeksKontroll(indeks, false);      // indeks sjekkes

        Node<T> p = finnNode(indeks);   // finner noden som foreløpig ligger på plass indeks
        T gammelVerdi = p.verdi;        // sparer på den gamle verdien til noden
        p.verdi = nyverdi;              // setter inn ny verdi

        endringer++;        // endringer økes

        return gammelVerdi;
    }

    @Override
    public boolean fjern(T verdi) {
        Node<T> p = hode;

        for(int indeks = 0; indeks < antall; indeks++) {    // Går gjennom listen
            if(p.verdi.equals(verdi)) {     //  Finner riktig node
                if(indeks == 0) {       // Den første skal fjernes
                    if(hode.neste == null) {        // Det er kun en verdi i lista
                        hode = hale = null;
                    }
                    else {
                        Node<T> q = hode.neste;
                        q.forrige = null;
                        hode = q;
                    }
                }
                else if(indeks == antall - 1) {     // Den siste skal fjernes
                    Node<T> q = hale.forrige;
                    q.neste = null;
                    hale = q;
                }
                else {      // En verdi mellom to andre skal fjernes
                    Node<T> q = finnNode(indeks - 1);
                    Node<T> r = finnNode(indeks + 1);
                    q.neste = r;
                    r.forrige = q;
                }
                antall--;
                endringer++;
                return true;
            }
            p = p.neste;
        }
        return false;
    }

    @Override
    public T fjern(int indeks) {

        if(!(indeks >= 0 && indeks < antall)) {     // Sjekker indeks
            throw new IndexOutOfBoundsException("Posisjonen " + indeks + "finnes ikke i lista");
        }

        Node<T> p;

        if(indeks == 0) {       // Den første skal fjernes
            p = hode;
            if(hode.neste == null) {    // Det er kun en verdi i listen
                hode = hale = null;
            }
            else {
                Node<T> q = hode.neste;
                q.forrige = null;
                hode = q;
            }
        }

        else if(indeks == antall - 1) {     // Den siste skal fjernes
            p = hale;
            Node<T> q = hale.forrige;
            q.neste = null;
            hale = q;
        }

        else {     // En verdi mellom to andre skal fjernes
            p = finnNode(indeks);
            Node<T> q = finnNode(indeks - 1);
            Node<T> r = finnNode(indeks + 1);
            q.neste = r;
            r.forrige = q;
        }
        antall--;
        endringer++;
        return p.verdi;
    }

    @Override
    public void nullstill() {
        Node<T> p = hode;
        for(int i = 0; i < antall; i++) {
            p.verdi = null;
            p.forrige = null;
            p = p.neste;
        }
        hode = hale = null;
        antall = 0;
        endringer++;
    }

    @Override
    public String toString() {  // fra oppgave i kompendiet
        StringBuilder s = new StringBuilder();
        s.append("[");

        if(!tom()) {
            Node<T> p = hode;   // hvis lista ikke er tom, så har den et hode
            s.append(hode.verdi);
            p = p.neste;

            while (p != null) {     // tar med alle verdiene som ikke er null
                s.append(", ").append(p.verdi);
                p = p.neste;
            }
        }
        s.append("]");
        return s.toString();
    }

    public String omvendtString() {
        StringBuilder s =new StringBuilder();
        s.append("[");

        if(!tom()) {
            Node<T> p = hale;       // hvis lista ikke er tom, så har den en hale
            s.append(hale.verdi);
            p = p.forrige;

            while (p != null) {
                s.append(", ").append(p.verdi);
                p = p.forrige;
            }
        }
        s.append("]");
        return s.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);      // Indeks sjekkes
        return new DobbeltLenketListeIterator(indeks);      // Returnerer instans av klassen
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            denne = finnNode(indeks);
            fjernOK = false;
            iteratorendringer = endringer;
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            if(!(iteratorendringer == endringer)) {
                throw new ConcurrentModificationException("Lista har blitt endret");
            }

            if(!hasNext()) {
                throw new NoSuchElementException("Ingen noder i lista");
            }

            fjernOK = true;     // Passerer sjekkene, så nå kan man fjerne verdier

            T denneVerdi = denne.verdi;     // Lagrer verdien til denne
            denne = denne.neste;        // denne flyttes til neste node

            return denneVerdi;      // lagra verdi returneres
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


