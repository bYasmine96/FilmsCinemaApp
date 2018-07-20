package com.tdm1.esi.kachfilm.FilmEntity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Yasmine BOUDJELI Yas on 22/06/2018.
 */

@Entity(tableName = "FilmAssocies",
        foreignKeys = arrayOf(ForeignKey(entity = FilmFavories::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("film_id"),
                onDelete = ForeignKey.CASCADE)))
public class ListeFilmAssocie (
        @PrimaryKey (autoGenerate = true)
        @ColumnInfo (name ="id") var  id:Int? = null,
        @ColumnInfo (name ="film_id") var  filmid:String? = null,
        @ColumnInfo (name ="affiche") var  affiche:Int? = null
)
{
    override  fun toString(): String {
        return this.filmid+" "+ this.affiche
    }

}