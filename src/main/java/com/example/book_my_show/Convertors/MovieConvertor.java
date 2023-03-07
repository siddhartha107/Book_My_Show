package com.example.book_my_show.Convertors;

import com.example.book_my_show.EntryDtos.MovieEntryDto;
import com.example.book_my_show.Models.Movie;

public class MovieConvertor {


    public static Movie convertEntryDtoToEntity(MovieEntryDto movieEntryDto){
        Movie movie = Movie.builder().movieName(movieEntryDto.getMovieName()).duration(movieEntryDto.getDuration())
                .genre(movieEntryDto.getGenre()).language(movieEntryDto.getLanguage())
                .rating(movieEntryDto.getRating()).build();

        return movie;


    }
}
