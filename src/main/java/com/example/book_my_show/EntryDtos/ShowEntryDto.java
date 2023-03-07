package com.example.book_my_show.EntryDtos;

import com.example.book_my_show.Enums.SeatType;
import com.example.book_my_show.Enums.ShowType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowEntryDto {

    private LocalTime showTime;

    private LocalDate showDate;


    private ShowType showType;


    private int classicSeatPrice;

    private int premiumSeatPrice;


    private int movieId;

    private int theaterId;


}
