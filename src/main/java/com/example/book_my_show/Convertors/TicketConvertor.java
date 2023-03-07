package com.example.book_my_show.Convertors;


import com.example.book_my_show.EntryDtos.TicketEntryDto;
import com.example.book_my_show.Models.Ticket;

public class TicketConvertor {

    public static Ticket convertEntrytoEntity(TicketEntryDto ticketEntryDto){


        Ticket ticket = new Ticket();
        return ticket;
    }
}
