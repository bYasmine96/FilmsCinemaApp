package com.tdm1.esi.kachfilm.Fragments

/**
 * Created by Yasmine BOUDJELI Yas on 20/06/2018.
 */

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import android.content.Context
import android.util.Log
import com.tdm1.esi.kachfilm.APIService.InterfaceAPIService.Companion.instance
import com.tdm1.esi.kachfilm.DaoFilm.Daofilms
import com.tdm1.esi.kachfilm.DaoFilm.DaofilmsAssocies
import com.tdm1.esi.kachfilm.FilmEntity.FilmFavories
import com.tdm1.esi.kachfilm.FilmEntity.ListeFilmAssocie

@Database(entities = arrayOf(FilmFavories::class,
        ListeFilmAssocie::class), version = 1)

abstract class FilmFavoriesDataBase(): RoomDatabase() {
    abstract fun filmsdao(): Daofilms
    abstract fun filmassociedao(): DaofilmsAssocies

    companion object {
        private var instance: FilmFavoriesDataBase? = null

        fun getInstance(context: Context): FilmFavoriesDataBase? {
            if (null == instance) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        FilmFavoriesDataBase::class.java,"FilmFavories.db")
                        .allowMainThreadQueries()
                        .build()
                Log.i("test","bd built" )
            }
            return instance
        }
    }


}