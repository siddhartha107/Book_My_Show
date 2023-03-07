package com.example.book_my_show.Services;

import com.example.book_my_show.Convertors.UserConvertor;
import com.example.book_my_show.EntryDtos.UserEntryDto;
import com.example.book_my_show.Models.User;
import com.example.book_my_show.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    public String addUser(UserEntryDto userEntryDto) throws Exception, NullPointerException{

        User user = UserConvertor.convertDtoToEntity(userEntryDto);

        userRepository.save(user);

        return "User Added successfully";



    }


}
