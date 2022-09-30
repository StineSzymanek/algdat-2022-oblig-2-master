package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;


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
            hode = hale = p;
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

    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
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
            hale.neste.forrige = hale;
            hale = nyNode;
        }
        antall++;
        endringer++;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    private Node<T> finnNode(int indeks) {
        if(indeks < antall/2) {
            Node<T> p = hode;
            for(int i = 0; i < indeks; i++) {
                p = p.neste;
            }
            return p;
        }
        else {
            Node<T> p = hale;
            for(int i = antall-1; i > indeks; i--) {
                p = p.forrige;
            }
            return p;
        }
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
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
                s.append(", " + p.verdi);
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
                s.append(", " + p.verdi);
                p = p.forrige;
            }
        }
        s.append("]");
        return s.toString();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
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
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
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


