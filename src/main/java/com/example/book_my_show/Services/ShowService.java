package com.example.book_my_show.Services;

import com.example.book_my_show.Convertors.ShowConvertor;
import com.example.book_my_show.EntryDtos.ShowEntryDto;
import com.example.book_my_show.Enums.SeatType;
import com.example.book_my_show.Models.*;
import com.example.book_my_show.Repository.MovieRepository;
import com.example.book_my_show.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;


    public String addShow(ShowEntryDto showEntryDto){

        Show show = ShowConvertor.convertEntrytoEntity(showEntryDto);

        int movieId = showEntryDto.getMovieId();
        int theaterId = showEntryDto.getTheaterId();

        Movie movie = movieRepository.findById(movieId).get();

        Theater theater = theaterRepository.findById(theaterId).get();

        show.setMovie(movie);
        show.setTheater(theater);


        List<ShowSeat> showSeatList = createShowSeat(showEntryDto,show);
        show.setListOfShowSeats(showSeatList);


        List<Show> showList =  movie.getShowList();
        showList.add(show);
        movie.setShowList(showList);

        movieRepository.save(movie);


        List<Show> showList1 = theater.getShowList();
        showList1.add(show);
        theater.setShowList(showList1);

        theaterRepository.save(theater);



        return "The Show has been added successfully";




    }

   private List<ShowSeat> createShowSeat(ShowEntryDto showEntryDto, Show show){

        Theater theater = show.getTheater();

        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();

        List<ShowSeat> showSeatList = new ArrayList<>();

        for (TheaterSeat theaterSeat : theaterSeatList){

            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatNo(theaterSeat.getSeatNo());
            showSeat.setSeatType(theaterSeat.getSeatType());

            if(theaterSeat.getSeatType().equals(SeatType.CLASSIC))
                showSeat.setPrice(showEntryDto.getClassicSeatPrice());

            else
                showSeat.setPrice(showEntryDto.getPremiumSeatPrice());

            showSeat.setBooked(false);
            showSeat.setShow(show);

            showSeatList.add(showSeat);

        }
        return showSeatList;

   }

}
