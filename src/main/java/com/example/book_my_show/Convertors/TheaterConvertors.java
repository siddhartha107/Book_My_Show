package com.example.book_my_show.Convertors;

import com.example.book_my_show.EntryDtos.TheaterEntryDto;
import com.example.book_my_show.Models.Theater;

public class TheaterConvertors {

    public static Theater convertDtoToEntity(TheaterEntryDto theaterEntryDto){


        return Theater.builder().location(theaterEntryDto.getLocation()).name(theaterEntryDto.getName()).build();

    }
}
