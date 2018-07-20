package com.tdm1.esi.kachfilm.DaoFilm

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.tdm1.esi.kachfilm.FilmEntity.FilmFavories
import com.tdm1.esi.kachfilm.FilmEntity.ListeFilmAssocie

/**
 * Created by Yasmine BOUDJELI Yas on 22/06/2018.
 */
@Dao
interface DaofilmsAssocies : Daofilms {

    @Query("SELECT * FROM FilmAssocies")
    fun getFilmAssocies(): List<ListeFilmAssocie>

    @Insert
    fun ajouter_film_associe(id_film_associe : ListeFilmAssocie)
}