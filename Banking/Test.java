import java.util.ArrayList;
import java.util.List;

public class Test{
    public static void main(String[] args){
        //tworzymy tablicę
        Clients c1 = new Clients();
        Clients c2 = new Clients(1, 2, "3", "4", "5", 6);
        List<String> lista = new ArrayList<String>();
        //dodajemy elementy typu Object - czyli dowolne, my dodamy ciągi znaków
        lista.add("Asia");
        lista.add("Basia");
        lista.add("Krzysiek");
        lista.add("Wojtek");

        //sprawdzamy rozmiar listy
        System.out.println("Rozmiar listy to: "+lista.size());

        //usuwamy obiekt "Asia" i sprawdzamy rozmiar
        lista.remove("Asia");
        System.out.println("Rozmiar listy to: "+lista.size());

        //usuwamy obiekt zajmujący 2 miejsce, czyli o indeksie 1 - u nas "Krzysiek"
        lista.remove(1);
        System.out.println("Rozmiar listy to: "+lista.size());

        //na koniec przypiszmy pierwszy element listy zmiennej imie i wyświetlmy go
        //musimy użyć rzutowania, ponieważ inaczej próbowalibyśmy przypisać Object do String
        String imie = (String)lista.get(0);
        System.out.println("Pierwszy element listy to: "+imie);


        List<Clients> lista2 = new ArrayList<Clients>();
        lista2.add(c1);
        System.out.println("Rozmiar listy to: "+lista2.size());
        lista2.add(c2);
        System.out.println("Rozmiar listy to: "+lista2.size());
    }
}
