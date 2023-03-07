package com.example.book_my_show.Services;

import com.example.book_my_show.Convertors.TicketConvertor;
import com.example.book_my_show.EntryDtos.ShowEntryDto;
import com.example.book_my_show.EntryDtos.TicketEntryDto;
import com.example.book_my_show.Models.Show;
import com.example.book_my_show.Models.ShowSeat;
import com.example.book_my_show.Models.Ticket;
import com.example.book_my_show.Models.User;
import com.example.book_my_show.Repository.ShowRepository;
import com.example.book_my_show.Repository.TicketRepository;
import com.example.book_my_show.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    public String addTicket(TicketEntryDto ticketEntryDto) throws Exception {

        Ticket ticket = TicketConvertor.convertEntrytoEntity(ticketEntryDto);


        //Validations :  Check if requested seats are available or not?

        boolean isValidRequest = checkValidityOfRequestedSeats(ticketEntryDto);

        if (isValidRequest == false) {

             throw new Exception("Requested seats are not available");
        }

        //Calculate the total amount

         Show show = showRepository.findById(ticketEntryDto.getShowId()).get();
        List<ShowSeat> seatList = show.getListOfShowSeats();
        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        int totalAmount = 0;
        for (ShowSeat showSeat : seatList){
            if(requestedSeats.contains(showSeat.getSeatNo())){
                totalAmount = totalAmount + showSeat.getPrice();
                showSeat.setBooked(true);
                showSeat.setBookedAt(new Date());
            }
            
        }
        ticket.setTotalAmount(totalAmount);

        //Setting the other attributes

        ticket.setMovieName(show.getMovie().getMovieName());
        ticket.setShowTime(show.getShowTime());
        ticket.setShowDate(show.getShowDate());
        ticket.setTheaterName(show.getTheater().getName());


        //We need to set String that talked about requested seats
        String allotedSeats = getAllotedSeatsfromShowSeats(requestedSeats);
        ticket.setBookedSeats(allotedSeats);

        //Setting the foreign key attributes

        User user = userRepository.findById(ticketEntryDto.getUserId()).get();
        ticket.setUser(user);
        ticket.setShow(show);


        //Save the parent

        ticket = ticketRepository.save(ticket);


        
        List<Ticket> ticketList = show.getListOfBookedTickets();
        ticketList.add(ticket);
        show.setListOfBookedTickets(ticketList);


        showRepository.save(show);


        List<Ticket> ticketList1 = user.getBookedTickets();
        ticketList1.add(ticket);
        user.setBookedTickets(ticketList1);


        userRepository.save(user);


        return "Ticket has been booked successfully";

        

    }


    private String getAllotedSeatsfromShowSeats(List<String> requestedSeats){

        String result = "";
        for(String seat : requestedSeats){
            result = result + seat +", ";
        }
        return result;
    }

    private boolean checkValidityOfRequestedSeats(TicketEntryDto ticketEntryDto){

        int showId = ticketEntryDto.getShowId();

        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        Show show = showRepository.findById(showId).get();

        List<ShowSeat> listOfSeats = show.getListOfShowSeats();

        //Iterating over the list of seats for that particular show
        for(ShowSeat showSeat : listOfSeats){

           String seatNo = showSeat.getSeatNo();
           if(requestedSeats.contains(seatNo)){

               if(showSeat.isBooked()==true){
                   return false; //Since this seat cant be occupied : returning false;
               }
           }
            
        }
        //All the seats requested were available
        return true;

    }
}
