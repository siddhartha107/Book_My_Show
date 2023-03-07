package com.example.book_my_show.Convertors;

import com.example.book_my_show.EntryDtos.ShowEntryDto;
import com.example.book_my_show.Models.Show;

public class ShowConvertor {

    public static Show convertEntrytoEntity(ShowEntryDto showEntryDto){

        Show show = Show.builder()
                .showDate(showEntryDto.getShowDate())
                .showTime(showEntryDto.getShowTime())
                .showType(showEntryDto.getShowType()).build();

        return  show;
        
    }
}
