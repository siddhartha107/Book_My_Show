package com.example.book_my_show.Services;

import com.example.book_my_show.Convertors.TheaterConvertors;
import com.example.book_my_show.EntryDtos.TheaterEntryDto;
import com.example.book_my_show.Enums.SeatType;
import com.example.book_my_show.Models.Theater;
import com.example.book_my_show.Models.TheaterSeat;
import com.example.book_my_show.Repository.TheaterRepository;
import com.example.book_my_show.Repository.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {



    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    @Autowired
    TheaterRepository theaterRepository;

    public String addTheater(TheaterEntryDto theaterEntryDto) throws Exception{


        if(theaterEntryDto.getName()==null || theaterEntryDto.getLocation()==null){
            throw new Exception("Name and location should be valid");
        }


        /*
        1.create theaterSeats
        2.I need to save theater : I need theaterEntity
        3.Always set the attribute before saving

         */

        Theater theater = TheaterConvertors.convertDtoToEntity(theaterEntryDto);

        List<TheaterSeat> theaterSeatList = createTheaterSeats(theaterEntryDto,theater);

        theater.setTheaterSeatList(theaterSeatList);

        theaterRepository.save(theater);

        return "Theater Added successfully";
        

    }
    private List<TheaterSeat> createTheaterSeats(TheaterEntryDto theaterEntryDto,Theater theater){

        int noClassicSeats = theaterEntryDto.getClassicSeatsCount();
        int noPremiumSeats = theaterEntryDto.getPremiumSeatsCount();

        List<TheaterSeat> theaterSeatList = new ArrayList<>();



        //Created the classic Seats
        for(int count = 1;count<=noClassicSeats;count++){


            //We need to make a new TheaterSeat

            TheaterSeat theaterSeat = TheaterSeat.builder().seatType(SeatType.CLASSIC)
                    .seatNo(count+"C").theater(theater).build();


            theaterSeatList.add(theaterSeat);
        }

        //Created the premium seats

        for(int count = 1;count<=noPremiumSeats;count++){


            //We need to make a new TheaterSeat

            TheaterSeat theaterSeat = TheaterSeat.builder().seatType(SeatType.PREMIUM)
                    .seatNo(count+"P").theater(theater).build();


            theaterSeatList.add(theaterSeat);
        }

        
        return theaterSeatList;
        
    }
}
