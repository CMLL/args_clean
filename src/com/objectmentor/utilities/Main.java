package com.objectmentor.utilities;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            CleanArgs arg = new CleanArgs("l,p#,d*,f##,s[*]", args);
            boolean logging = arg.getBoolean('l');
            int port = arg.getInteger('p');
            String directory = arg.getString('d');
            double percent = arg.getDouble('f');
            ArrayList listOfPlaces = arg.getList('s');
            System.out.printf("Logging: %b - Port: %d - Directory: %s - Percent: %f - Places: %s\n",
                    logging, port, directory, percent, listOfPlaces.toString());
            System.out.println(arg.has('o'));
        }
        catch (ArgsException e) {
            System.out.printf("Argument error: %s\n", e.errorMessage());
        }
    }
}
