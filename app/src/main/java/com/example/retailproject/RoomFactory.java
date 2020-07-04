package com.example.retailproject;

import android.content.Context;

import com.example.retailproject.room.ProductsDatabase;

import androidx.room.Room;

public class RoomFactory {

    private static ProductsDatabase roomDatabase;

    private RoomFactory(){

    }

    public static ProductsDatabase createOrGetRoomObject(Context context){

        if (roomDatabase == null){

            roomDatabase = Room.databaseBuilder(context, ProductsDatabase.class, "Products_Database")
                    .build();
        }
        return roomDatabase;
    }
}
