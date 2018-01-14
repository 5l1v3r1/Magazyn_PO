package pl.liebertyesterday.shorti1996.magazyn.Model;

import org.junit.Test;

import java.util.LinkedList;

import pl.liebertyesterday.shorti1996.magazyn.AnnealingSimulator;

/**
 * Created by wojciech.liebert on 13.01.2018.
 */
public class TravelPathTest {

    @Test
    public void t() {
//        LinkedList<Lokalizacja> lokalizacje = new LinkedList<Lokalizacja>() {{
//            add(new Lokalizacja(8, 1));
//            add(new Lokalizacja(2, 5));
//            add(new Lokalizacja(2, 3));
//            add(new Lokalizacja(7, 20));
//        }};
//        TravelPath travelPath = new TravelPath(lokalizacje);
//        Observable.just(new AnnealingSimulator(travelPath))
////                .subscribeOn(Schedulers.computation())
//                .subscribe(annealingSimulator ->
//                        annealingSimulator.simulateAnnealing(100000, 1000000000, 11111.8));

        LinkedList<Lokalizacja> lokalizacje = new LinkedList<Lokalizacja>() {{
            add(new Lokalizacja(1, 1));
            add(new Lokalizacja(8, 1));
            add(new Lokalizacja(2, 5));
            add(new Lokalizacja(3, 5));
            add(new Lokalizacja(4, 5));
            add(new Lokalizacja(6, 5));
            add(new Lokalizacja(3, 25));
            add(new Lokalizacja(3, 28));
            add(new Lokalizacja(7, 20));
            add(new Lokalizacja(8, 3));
        }};

//        lokalizacje = new LinkedList<Lokalizacja>() {{
//            add(new Lokalizacja(1, 1));
//            add(new Lokalizacja(3, 1));
//            add(new Lokalizacja(8, 22));
//            add(new Lokalizacja(5, 5));
//            add(new Lokalizacja(8, 29));
//            add(new Lokalizacja(8, 1));
//        }};

        lokalizacje = new LinkedList<Lokalizacja>() {{
            add(new Lokalizacja(1, 1));
            add(new Lokalizacja(7, 3));
            add(new Lokalizacja(5, 3));
            add(new Lokalizacja(2, 5));
            add(new Lokalizacja(8, 1));
        }};

        lokalizacje = new LinkedList<Lokalizacja>() {{
            add(new Lokalizacja(1, 1));
            add(new Lokalizacja(7, 3));
            add(new Lokalizacja(5, 3));
            add(new Lokalizacja(2, 55));
            add(new Lokalizacja(8, 1));
        }};


        double a = new AnnealingSimulator(new TravelPath(lokalizacje)).
                simulateAnnealingForDistance(100, 0.99999);
//        double b = new AnnealingSimulator(new TravelPath(lokalizacje)).
//                simulateAnnealingForDistance(1000000000, 0.5);
//        double c = new AnnealingSimulator(new TravelPath(lokalizacje)).
//                simulateAnnealingForDistance(5, 0.9999);
//        double d = new AnnealingSimulator(new TravelPath(lokalizacje)).
//                simulateAnnealingForDistance(20, 100000000, 0.1);
        int i = 0;
    }
}
