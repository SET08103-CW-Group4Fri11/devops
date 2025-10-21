package com.napier.sem;

public class App
{
    public static void main(String[] args)
    {
//  initiate dbTools to connect with db
    DbTools dbTools = new DbTools();
    dbTools.connect();
    dbTools.printCountries();
    dbTools.disconnect();
    }
}
