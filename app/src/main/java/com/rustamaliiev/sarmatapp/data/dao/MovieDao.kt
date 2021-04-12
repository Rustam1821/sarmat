package com.rustamaliiev.sarmatapp.data.dao

import androidx.room.*
import com.rustamaliiev.sarmatapp.data.entity.GenreDB
import com.rustamaliiev.sarmatapp.data.entity.MovieDB
import com.rustamaliiev.sarmatapp.data.entity.MovieGenrePair
import com.rustamaliiev.sarmatapp.data.entity.MovieWithGenre
import com.rustamaliiev.sarmatapp.domain.entity.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies WHERE movie_group LIKE :movieGroup")
    suspend fun getMovies(movieGroup: String): List<MovieGenrePair>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGenres(items: List<GenreDB>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieGenrePairs(movieWithGenre: List<MovieWithGenre>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(items: List<MovieDB>)

    @Transaction
    suspend fun insertMoviesInDb(moviesFromNet: List<Movie>, filmGroup: String) {
        val genres: MutableList<GenreDB> = mutableListOf()
        val pairs: MutableList<MovieWithGenre> = mutableListOf()
        val movies: MutableList<MovieDB> = mutableListOf()

        moviesFromNet.forEach { movie ->
            movies.add(
                MovieDB(
                    id = movie.id,
                    title = movie.title,
                    imageUrl = movie.imageUrl,
                    rating = movie.rating,
                    reviewCount = movie.reviewCount,
                    ageLimit = movie.ageLimit,
                    runningTime = movie.runningTime,
                    isLiked = movie.isLiked,
                    filmGroups = filmGroup
                )
            )
            movie.genres.forEach { genre ->

//      region collect genres
                genres.add(
                    GenreDB(
                        id = genre.id,
                        name = genre.name
                    )
                )
//      endregion collect genres

//      region collect pairs
                pairs.add(
                    MovieWithGenre(
                        movieId = movie.id,
                        genreId = genre.id
                    )
                )
//      endregion collect pairs
            }
        }

        insertGenres(genres)
        insertMovieGenrePairs(pairs)
        insertMovies(movies)
    }
}