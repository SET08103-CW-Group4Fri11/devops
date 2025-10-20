package com.napier.sem;

public class App
{
    public static void main(String[] args)
    {
//  initiate dbTools to connect with db
    dbTools dbTools = new dbTools();
    dbTools.connect();
    dbTools.disconnect();
    }
}
