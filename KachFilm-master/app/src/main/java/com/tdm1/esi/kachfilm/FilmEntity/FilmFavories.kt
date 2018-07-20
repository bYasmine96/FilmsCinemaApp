package com.tdm1.esi.kachfilm.FilmEntity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Yasmine BOUDJELI Yas on 20/06/2018.
 */

@Entity(tableName = "FilmFavories")
public class FilmFavories (
    @PrimaryKey(autoGenerate = false ) var id: Int = 0,
    @ColumnInfo(name = "fiche_film_titre") var fiche_film_titre: String? = null,
    @ColumnInfo(name = "fiche_film_date") var  fiche_film_date: String? = null,
    @ColumnInfo(name = "fiche_film_synopsis") var  fiche_film_synopsis: String? = null
)
{

    override  fun toString(): String {
        return this.fiche_film_titre + " "+ this.fiche_film_date+" "+ this.fiche_film_synopsis
    }
}
