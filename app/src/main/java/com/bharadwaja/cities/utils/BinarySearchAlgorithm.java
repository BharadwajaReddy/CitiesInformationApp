package com.bharadwaja.cities.utils;

import com.bharadwaja.cities.data.CityInformation;
import com.bharadwaja.cities.data.Coordinates;

import java.util.LinkedList;
import java.util.Locale;

import static java.util.Arrays.binarySearch;

public class BinarySearchAlgorithm {

    public LinkedList<CityInformation> cityBinarySearch(LinkedList<CityInformation> list, String chars) {

        String target = chars.toLowerCase();
        LinkedList<CityInformation> filteredcityList = new LinkedList<>();
        if (list == null)
            return filteredcityList;


        int low = 0, high = list.size() - 1;
        // get the start index of target number
        int startIndex = -1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;

            int citynamelength = list.get(mid).getCityName().length();
            if (citynamelength >= target.length()) {
                String fromList = list.get(mid).getCityName().toLowerCase().substring(0, target.length());

                int res = fromList.compareTo(target);
                if (res > 0) {
                    high = mid - 1;
                } else if (res == 0) {
                    startIndex = mid;
                    high = mid - 1;
                } else
                    low = mid + 1;

            } else {
                String fromList = list.get(mid).getCityName().toLowerCase();
                int res = fromList.compareTo(target);
                if (res > 0) {
                    high = mid - 1;
                } else if (res == 0) {
                    startIndex = mid;
                    high = mid - 1;
                } else
                    low = mid + 1;

            }
        }

        // get the end index of target number
        int endIndex = -1;
        low = 0;
        high = list.size() - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int citynamelength = list.get(mid).getCityName().length();
            if (citynamelength >= target.length()) {
                String fromList = list.get(mid).getCityName().toLowerCase().substring(0, target.length());
                int res = fromList.compareTo(target);
                if (res > 0) {
                    high = mid - 1;
                } else if (res == 0) {
                    endIndex = mid;
                    low = mid + 1;
                } else
                    low = mid + 1;
            } else {
                String fromList = list.get(mid).getCityName().toLowerCase();
                int res = fromList.compareTo(target);
                if (res > 0) {
                    high = mid - 1;
                } else if (res == 0) {
                    endIndex = mid;
                    low = mid + 1;
                } else
                    low = mid + 1;
            }
        }


        if (startIndex != -1 && endIndex != -1) {
            for (int i = startIndex; i <= endIndex; i++) {

                filteredcityList.add(list.get(i));
            }
        }

        return filteredcityList;
    }

}