package com.tdm1.esi.kachfilm.DaoFilm

import android.arch.persistence.room.*
import com.tdm1.esi.kachfilm.FilmEntity.FilmFavories

/**
 * Created by Yasmine BOUDJELI Yas on 20/06/2018.
 */

@Dao
interface Daofilms{

   @Query("SELECT * FROM FilmFavories")
    fun getFilmsFavories(): List<FilmFavories>

   @Insert
    fun ajouter(nomprenom : FilmFavories)

}
