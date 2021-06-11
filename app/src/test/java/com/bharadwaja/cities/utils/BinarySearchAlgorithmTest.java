package com.bharadwaja.cities.utils;

import com.bharadwaja.cities.data.CityInformation;
import com.bharadwaja.cities.data.Coordinates;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class BinarySearchAlgorithmTest {
    LinkedList<CityInformation> list;

    @Before
    public void setUp() throws Exception {

        list = new LinkedList<>();
        list.add(new CityInformation("IN", "Araku", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("IN", "Avuku", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "AZAM", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Bethamcherla", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Beemunipadu", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Boogundam", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Chintakuntla", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Dornala", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Dornipadu", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Ernapadu", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Etalapuram", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Gorantla", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "H.Kottala", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Ivorry", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Jakka", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Jupadubangla", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Kalugotla", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Kondapuram", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Lingala", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Lingapuram", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Manikantapuram", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Nandikotkur", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Nandyala", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Obulampalli", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Owk", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Pampalli", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Pasurapadu", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Rayalaseema", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Rollapadu", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Road", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Royal house", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Sanjamala", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Tuvvur", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Umbrella town", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Vellore", 12345, new Coordinates(78.28, 87.98)));
        list.add(new CityInformation("US", "Zombia", 12345, new Coordinates(78.28, 87.98)));

        Collections.sort(list, new CityNameComparator());
    }

    class CityNameComparator implements Comparator<CityInformation> {
        @Override
        public int compare(CityInformation o1, CityInformation o2) {
            if (o1 == null || o2 == null) {
                return 0;
            }
            return o1.getCityName().compareTo(o2.getCityName());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void cityBinarySearch() {

        BinarySearchAlgorithm searchobj = new BinarySearchAlgorithm();

        //test case 1
        LinkedList<CityInformation> filteredlist = searchobj.cityBinarySearch(list, "H");
        assertEquals("H.Kottala", filteredlist.get(0).getCityName());

        //test case 2
        LinkedList<CityInformation> filteredlist2 = searchobj.cityBinarySearch(list, "h ");
        assertEquals(0, filteredlist2.size());

        //test case 3
        LinkedList<CityInformation> filteredlist3 = searchobj.cityBinarySearch(list, "B");
        assertTrue(filteredlist3.size() == 3 && filteredlist3.get(0).getCityName().equals("Beemunipadu") && filteredlist3.get(1).getCityName().equals("Bethamcherla") && filteredlist3.get(2).getCityName().equals("Boogundam"));

        //test case 4
        LinkedList<CityInformation> filteredlist4 = searchobj.cityBinarySearch(list, "be");
        assertTrue(filteredlist4.size() == 2 && filteredlist4.get(0).getCityName().equals("Beemunipadu") && filteredlist4.get(1).getCityName().equals("Bethamcherla"));


        //test case 5
        LinkedList<CityInformation> filteredlist5 = searchobj.cityBinarySearch(list, "bet");
        assertTrue(filteredlist5.size() == 1 && filteredlist5.get(0).getCityName().equals("Bethamcherla"));


        //test case 6  no cities starting with a letter
        LinkedList<CityInformation> filteredlist6 = searchobj.cityBinarySearch(list, "x");
        assertEquals(0, filteredlist6.size());


    }


}