package com.pokemonreview.api.controllerEndPoints;

public class BlackListControllerEndPoints {
    public static String[] blackControllers(){
        return new String[]{"pokemon/{id}","getAll/pokemon","/pokemon/{id}update","/pokemon/{id}delete"};
    }


}
