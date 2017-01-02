package com.agora.db;

/**
 * Created by Ivan on 7/11/15.
 */
public interface DatabaseTable {

    String getCreateQuery();
    String getDropQuery();
}
