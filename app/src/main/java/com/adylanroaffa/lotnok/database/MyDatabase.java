package com.adylanroaffa.lotnok.database;

/**
 * Created by ffahleraz on 7/19/17.
 */

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {

    public static final String NAME = "MyDatabase";

    public static final int VERSION = 1;

}
