package com.napier.sem;

public class App
{
    public static void main(String[] args)
    {
//  initiate dbTools to connect with db
    DbTools dbTools = new DbTools();
    dbTools.connect();
    System.out.println("Connected to database");
    dbTools.disconnect();
    }
}
