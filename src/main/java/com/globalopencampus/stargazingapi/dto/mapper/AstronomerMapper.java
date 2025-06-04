package com.globalopencampus.stargazingapi.dto.mapper;

import com.globalopencampus.stargazingapi.dto.model.AstronomerDto;
import com.globalopencampus.stargazingapi.model.Astronomer;

public class AstronomerMapper {
    public static Astronomer maptoUser(AstronomerDto astronomerDto){
        Astronomer astronomer = new Astronomer();
        astronomer.setUsername(astronomerDto.username());
        astronomer.setEmail(astronomerDto.email());
        astronomer.setFirstName(astronomerDto.firstName());
        astronomer.setLastName(astronomerDto.lastName());
        astronomer.setRoles(astronomerDto.roles());

        return astronomer;
    }
}
