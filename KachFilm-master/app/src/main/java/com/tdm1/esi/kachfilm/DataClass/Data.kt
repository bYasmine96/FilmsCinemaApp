package com.tdm1.esi.kachfilm.DataClass

import android.os.Parcel
import android.os.Parcelable
import com.tdm1.esi.kachfilm.R
import java.io.Serializable

/**
 * Created by mac on 13/04/2018.
 *
 * Clé de l'API (v3 auth): fd1d0a4dbf1c4f531e9eb5287111c92f
 * Jeton d'accès en lecture à l'API (v4 auth): eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmZDFkMGE0ZGJmMWM0ZjUzMWU5ZWI1Mjg3MTExYzkyZiIsInN1YiI6IjViMjYyMTg1OTI1MTQxMGQ1YzAwZDQ1NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.JYhN4gZq2UKUT2nJ34gG3Z4mPbOUtE0ajR_CfdxEpVI
 *
 *
 */


data class FilmEnProjection  ( val poster_film: Int, val titre_film:String) : Serializable


data class SeanceProjection( val date:String , val heure:String) : Serializable

data class RdvDiffusion( val jour:String , val heure:String) : Serializable

data class SalleDeProjection  ( val photo:Int,val nom_salle: String, val adresse_salle:String): Serializable {

}


data class Film  (val affiche: Int, val titre:String, val description:String, val date:String,
                  val starLies:List<Star>, val commentaies:List<Commentaire>,
                  val salleProjection:List<SalleDeProjection>): Serializable {

}

/**
 * Nouvelle classe de données
 *  */

data class FilmData  (val adult: Boolean,
                      val backdrop_path: String,
                      val belongs_to_collection: String?,
                      val budget: Double?,
                      val genres:Array<Int>?,
                      val id: Int,
                      val original_language: String?,
                      val original_title: String,
                      val overview: String,
                      val popularity: Double,
                      val poster_path: String,
                      val production_companies:Array<ProductionCompanie>?,
                      val production_countries:Array<ProductionCountrie>?,
                      val release_date: String,
                      val revenue: Double?,
                      val runtime: String?,
                      val spoken_languages:Array<SpokenLanguage>?,
                      val status: String?,
                      val tagline: String?,
                      val title: String,
                      val video: Boolean,
                      val vote_average: Double,
                      val vote_count: Int)  : Serializable

data class StarData(
        val id: Int,
        val known_for:Array<FilmData?>,
        val popularity: Double,
        val name: String
): Serializable

data class SerieData  (
                      val adult: Boolean,
                      val backdrop_path: String,
                      val genres:Array<Int>?,
                      val id: Int,
                      val original_language: String?,
                      val original_name: String,
                      val overview: String,
                      val popularity: Double,
                      val poster_path: String,
                      val first_air_date: String,
                      val name: String,
                      val video: Boolean,
                      val vote_average: Double,
                      val vote_count: Int)  : Serializable


/**  Fin nouvelles classes*/
data class Star ( val photo:Int, val prenom:String, val nom:String,val dateNaissance:String,
                  val Biographie:String,  val type:String): Serializable {}


data class Serie (val poster_series:Int, val titre:String, val description: String, val NbSaison:Int, val dateProduction:String, val saisons:List <Saison>?,
                  val note:Double, val commentaies: List<Commentaire>?, val starLies: List<Star>?):Serializable {

}


data class Commentaire (val photo:Int, val utilisateur:String , val contenu:String) :Serializable {

}


data class Saison (val titre:String, val description: String, val photo:Int, val note:Double, val commentaies:List<Commentaire>,
                   val starLies:List<Star>, val episode:List <Episode>):Serializable {

}


data class Episode (val num:Int, val description: String, val note:Double, val commentaies:List<Commentaire>, val diffusion:List<ChaineTv> ):Serializable {


}


data class ChaineTv( val logo:Int, val nom:String):Serializable {

}


/// _______________________________ COMMENTAIRES __________________________________________________

var commentaire1: Commentaire = Commentaire(R.drawable.angelinajolie,"Smaili Naziha", "Bon j' imagine c 'est pas la premier film qui parle de Pablo Escobar , soit. Mais Javier Bardem est un très bon acteur , et au vu des premières images , le film donne vraiment envie. Le film ayant l air très réaliste et violent dans ses propos comme dans ses images , je pense que ce film vas me plaire")
var commentaire2: Commentaire = Commentaire(R.drawable.bradpitt,"Lotfi Star", "J'adore Pénélope cruz je la trouve tres jolis j'irais certainement le voir rien que pour sa jespère que le film seras une réussite également")
var commentaire3: Commentaire = Commentaire(R.drawable.emmawatson,"Boudjeli Yasmine", "Ohlala ce synopsis pompeux, faudrait qu'ils changent un peu de registre au bout d'un moment ces deux là, c'est encore et toujours des mélo-drama-bobo parisiens")

val listeCommentaires:List<Commentaire> = listOf(commentaire1, commentaire2, commentaire3)


/// _______________________________ Salle de projection ____________________________________________________


var salle1: SalleDeProjection = SalleDeProjection(R.drawable.salle_ibnkhaldoun, "Ibn Khaldoun", "Alger Centre")
var salle2: SalleDeProjection = SalleDeProjection(R.drawable.salle_ibnkhaldoun, "Cosmos", "Riadh ELFAth")
var salle3: SalleDeProjection = SalleDeProjection(R.drawable.salle_ibnkhaldoun, "Ifriquia", "El Biar")
var salle4: SalleDeProjection = SalleDeProjection(R.drawable.salle_ibnkhaldoun, "EL Djazayar", "El Biar")
var salle5: SalleDeProjection = SalleDeProjection(R.drawable.salle_ibnkhaldoun, "Esi", "Oued Smar")

val listeSalles:List<SalleDeProjection> =listOf(salle1, salle2, salle3, salle4, salle5)

val listeSallesFav:List<SalleDeProjection> = listOf(salle1, salle2)

/// _______________________________ Liste de Stars ____________________________________________________

var star_angelina: Star = Star(R.drawable.angelinajolie, "Angelina", "Jolie", "12/04/1980", "Angelina Jolie, née Angelina Jolie Voight le 4 juin 1975 à Los Angeles, est une actrice, réalisatrice, scénariste, productrice, mannequin, philanthrope, écrivaine et ambassadrice de bonne volonté américano-cambodgienne. ",
        "Actrice")
var star_bradpitt: Star = Star(R.drawable.bradpitt, "Brad ", "Pitt", "12/04/1980", "William Bradley Pitt, dit Brad Pitt, est un acteur et producteur de cinéma américain, né le 18 décembre 1963 à Shawnee.",
        "Acteur")
var star_emmarobert: Star = Star(R.drawable.emmarobert, "Emma", "Roberts", "12/04/1980", "Emma Roberts, née le 10 février 1991 à Rhinebeck, New York, est une actrice et chanteuse américaine. Roberts se fait premièrement connaître en tant que vedette de la série Allie Singe",
        "Actrice")
var star_emmawatson: Star = Star(R.drawable.emmawatson, "Emma", "Watson", "12/04/1980", "Emma Watson, née le 15 avril 1990 à Paris, en France, est une actrice britannique. Elle est devenue célèbre en jouant le rôle d'Hermione Granger, l'un des trois rôles principaux dans la série des films Harry Potter.",
        "Actrice")
var star_jamestoback: Star = Star(R.drawable.jamestoback, "James", "Back", "12/04/1980", "Découvrez le profil de James Back sur LinkedIn, la plus grande communauté professionnelle au monde. James indique 9 postes postes sur son profil.",
        "Producteur")
var star_johnnydepp: Star = Star(R.drawable.johnnydepp, "Johnny", "Deep", "12/04/1980", "John Christopher Depp II, dit Johnny Depp, est un acteur, réalisateur, guitariste, scénariste et producteur de cinéma américain, né le 9 juin 1963 à Owensboro.",
        "Acteur/Réalisateur")
var star_juliaroberts: Star = Star(R.drawable.juliaroberts, "Julia", "Roberts", "12/04/1980", " Julia Roberts est une actrice et productrice américaine, née le 28 octobre 1967 à Atlanta. Elle est révélée par la populaire comédie romantique Pretty Woman en 1990, et devient une star planétaire.",
        "Actrice")
var star_leonardodicaprio: Star = Star(R.drawable.leonardodicaprio, "Leonardo", "Dicaprio", "12/04/1980", "Leonardo DiCaprio, né le 11 novembre 1974 à Los Angeles, est un acteur, scénariste et producteur de cinéma américain.",
        "Acteur")
var star_woodyallen: Star = Star(R.drawable.woodyallen, "Woddy", "Allan", "Allan Stewart Konigsberg, dit Woody Allen, est un réalisateur, scénariste, acteur et humoriste américain, né le 1ᵉʳ décembre 1935 à New York.", "12/04/1980",
        "Réalisateur")

val listeStarLies:List<Star> =  listOf(star_angelina, star_bradpitt, star_emmarobert, star_emmawatson, star_jamestoback)
val listeStars:List<Star> = listOf(star_angelina, star_bradpitt, star_emmarobert, star_emmawatson, star_jamestoback, star_johnnydepp, star_juliaroberts, star_leonardodicaprio, star_woodyallen)


//_________________________________ liste de film ____________________________________________________

var Film_black_panther: Film = Film(R.drawable.filmblackpanther, "Black Panther", "Black Panther est un film réalisé par Ryan Coogler avec Chadwick Boseman, Michael B. Jordan. Synopsis : Après les événements qui se sont déroulés dans Captain America : Civil War, T'Challa revient chez lui prendre sa place sur le trône du Wakanda, une nation africaine technologiqueme", "2018",
        listeStarLies, listeCommentaires, listeSalles)

var Film_Frank: Film = Film(R.drawable.filmfrank, "Frank", "anai Gurira. Sociétés de production, Marvel Studios. Pays d'origine, Drapeau des États-Unis États-Unis. Genre, Super-héros.", "2008",
        listeStarLies, listeCommentaires, listeSalles)

var Film_harryPoter1: Film = Film(R.drawable.filmharrypoter, "Harry Poter 1", "Harry Potter à l'école des sorciers est un film fantastique britannico-américain réalisé par Chris Columbus, sorti en 2001. Il est adapté du roman du même nom de J. K.", "2000",
        listeStarLies, listeCommentaires, listeSalles)

var Film_harryPoter8: Film = Film(R.drawable.filmharry8, "Harry Poter 8", "Harry Potter à l'école des sorciers est un film fantastique britannico-américain réalisé par Chris Columbus, sorti en 2001. Il est adapté du roman du même nom de J. K.", "2016",
        listeStarLies, listeCommentaires, listeSalles)

var Film_host: Film = Film(R.drawable.filmhost, "Host", "e fichier hosts est un fichier utilisé par le système d'exploitation d'un ordinateur lors de l'accès à un réseau, comme Internet par exemple. Son rôle est d'associer des noms d'hôtes à des adresses IP.", "2001",
        listeStarLies, listeCommentaires, listeSalles)

var Film_lordOfRings: Film = Film(R.drawable.filmlordoftherings, "Lord of the rings", "Le Seigneur des anneaux : La Communauté de l'anneau est un film américano-néo-zélandais réalisé par Peter Jackson, sorti en 2001. C'est le premier volet de la trilogie Le Seigneur des anneaux et l'adaptation du livre La Fraternité de l'Anneau", "2000",
        listeStarLies, listeCommentaires, listeSalles)

var Film_oblivior: Film = Film(R.drawable.filmoblivior, "Oblivior", "Le Seigneur des anneaux : La Communauté de l'anneau est un film américano-néo-zélandais réalisé par Peter Jackson, sorti en 2001. C'est le premier volet de la trilogie Le Seigneur des anneaux et l'adaptation du livre La Fraternité de l'Anneau", "2002",
        listeStarLies, listeCommentaires, listeSalles)

var Film_titanic: Film = Film(R.drawable.filmtitanic, "Titanic", "Le RMS Titanic est un paquebot transatlantique britannique de la White Star Line construit à l'initiative de Joseph Bruce Ismay en 1907.", "2004",
        listeStarLies, listeCommentaires, listeSalles)

val listeFilm:List<Film> = listOf(Film_black_panther, Film_Frank, Film_harryPoter1, Film_harryPoter8, Film_host, Film_lordOfRings, Film_oblivior, Film_titanic)
val listeFilmLies:List<Film> = listOf(Film_black_panther, Film_harryPoter8, Film_host, Film_lordOfRings, Film_titanic)
val listeFilmFav:List<Film> = listOf(Film_black_panther, Film_harryPoter8, Film_lordOfRings)


/// _______________________________ Chaines TV ____________________________________________________

var TV_TF1: ChaineTv = ChaineTv(R.drawable.tv_tf1, "TF1")

var TV_Netflix: ChaineTv = ChaineTv(R.drawable.tv_netflix, "Netflix")

val listeChaine:List<ChaineTv> = listOf(TV_TF1, TV_Netflix)

/// _______________________________ Episodes ____________________________________________________

var episode1: Episode = Episode(1, "", 3.5, listeCommentaires, listeChaine)
var episode2: Episode = Episode(2, "", 2.5, listeCommentaires, listeChaine)
var episode3: Episode = Episode(3, "", 4.5, listeCommentaires, listeChaine)
var episode4: Episode = Episode(4, "", 3.0, listeCommentaires, listeChaine)
var episode5: Episode = Episode(5, "", 4.5, listeCommentaires, listeChaine)
var episode6: Episode = Episode(6, "", 3.5, listeCommentaires, listeChaine)
var episode7: Episode = Episode(7, "", 3.0, listeCommentaires, listeChaine)
var episode8: Episode = Episode(8, "", 2.5, listeCommentaires, listeChaine)
var episode9: Episode = Episode(9, "", 1.5, listeCommentaires, listeChaine)

val listeEpisode:List<Episode> = listOf(episode1, episode2, episode3, episode4, episode5, episode6, episode7, episode8, episode9)
/// _______________________________ Saison ___________________________________________________________

var saison1: Saison = Saison("Casa 1", "Casa est une enseigne belge de magasins de décoration et d’ameublement. Créée en 1975 en Belgique, elle appartient au groupe Blokker depuis 1988. Depuis fin 2016, CASA ne fait plus partie de Blokker Holding.", R.drawable.serie_saison1_casa, 4.0, listeCommentaires, listeStarLies, listeEpisode)
var saison2: Saison = Saison("Casa 2", "Casa est une enseigne belge de magasins de décoration et d’ameublement. Créée en 1975 en Belgique, elle appartient au groupe Blokker depuis 1988. Depuis fin 2016, CASA ne fait plus partie de Blokker Holding.", R.drawable.serie_saison2_casa, 4.5, listeCommentaires, listeStarLies, listeEpisode)

val listeSaisons: List<Saison> = listOf(saison1, saison2)

/// _______________________________ Liste de Series ____________________________________________________


var serie_dexter: Serie = Serie(R.drawable.serie_dexter, "Dexter", "STEP 1: Setup Icon Font\n" +
        "Follow the instructions to embed the icon font in your site and learn how to style your icons using CSS.", 13, "23/04/2010", null, 4.0, listeCommentaires, listeStarLies)
var serie_fri: Serie = Serie(R.drawable.serie_thelastkingdom, "The last Kingdom", "STEP 1: Setup Icon Font\n" +
        "Follow the instructions to embed the icon font in your site and learn how to style your icons using CSS.", 3, "04/08/2017", null, 4.0, listeCommentaires, listeStarLies)
var Serie_pretty: Serie = Serie(R.drawable.serie_prettylittlelyers, "The Pretty Littele Lyers", "STEP 1: Setup Icon Font\n" +
        "Follow the instructions to embed the icon font in your site and learn how to style your icons using CSS.", 5, "2009", null, 4.0, listeCommentaires, listeStarLies)
var serie_casa: Serie = Serie(R.drawable.serie_fri, "FRIKJENT", "La casa de papel ou La maison de papier est une mini-série télévisée espagnole en quinze épisodes réalisée par Álex Pina et diffusée entre le 2 mai 2017 et le 23 novembre 2017 sur la chaîne Antena 3 en Espagne.", 3, "31/03/2003", null, 4.0, listeCommentaires, listeStarLies)
var serie_lastKing: Serie = Serie(R.drawable.serie_saison2_casa, "La casa de papele", "La casa de papel ou La maison de papier est une mini-série télévisée espagnole en quinze épisodes réalisée par Álex Pina et diffusée entre le 2 mai 2017 et le 23 novembre 2017 sur la chaîne Antena 3 en Espagne.", 2, "03/12/2018", null, 4.0, listeCommentaires, listeStarLies)

val ListeSerie:List<Serie> = listOf(serie_dexter, serie_fri, Serie_pretty, serie_casa, serie_lastKing)


val ListeSerieFav:List<Serie> = listOf(Serie_pretty, serie_casa, serie_lastKing)

/// _______________________________ Liste seance de projection ____________________________________________________

var seance1: SeanceProjection = SeanceProjection("Aujourd'hui", "14:00")
var seance2: SeanceProjection = SeanceProjection("Aujourd'hui", "17:00")
var seance3: SeanceProjection = SeanceProjection("Aujourd'hui", "20:00")
var seance4: SeanceProjection = SeanceProjection("23/04/2018", "14:00")
var seance5: SeanceProjection = SeanceProjection("29/04/2018", "14:00")
var seance6: SeanceProjection = SeanceProjection("20/05/2018", "14:00")

val ListeSeance:List<SeanceProjection> = listOf(seance1, seance2, seance3, seance4, seance5, seance6)

/// _______________________________ Liste RDV ____________________________________________________

var dif1: RdvDiffusion = RdvDiffusion("Lundi", "14:00")
var dif2: RdvDiffusion = RdvDiffusion("Mardi", "17:00")
var dif3: RdvDiffusion = RdvDiffusion("Mercredi", "20:00")
var dif4: RdvDiffusion = RdvDiffusion("Jeudi", "14:00")
var dif5: RdvDiffusion = RdvDiffusion("Vendredi", "14:00")

val listeRDV:List<RdvDiffusion> = listOf(dif1, dif2, dif3, dif4, dif5)


