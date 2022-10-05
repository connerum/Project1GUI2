package edu.bsu.cs222.project1gui2;

import java.util.ArrayList;

public class Formatter {
    public void formatted(ArrayList<String> usersList, ArrayList<String> timestampsList) {
        int count = 0;
        for (String user : usersList) {
            System.out.printf("%s %s\n", user, timestampsList.get(count));
            count += 1;
        }
    }
}