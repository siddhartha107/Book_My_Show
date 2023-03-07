package com.example.book_my_show.Convertors;

import com.example.book_my_show.EntryDtos.UserEntryDto;
import com.example.book_my_show.Models.User;

public class UserConvertor {

    //Static is kept to avoid calling it via objects/instances
    public static User convertDtoToEntity(UserEntryDto userEntryDto){

        User user = User.builder().age(userEntryDto.getAge()).name(userEntryDto.getName())
                .address(userEntryDto.getAddress()).email(userEntryDto.getEmail()).mobNo(userEntryDto.getMobNo()).build();

        return user;
    }

}
