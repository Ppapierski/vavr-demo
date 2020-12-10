package com.redbend.vavr.intro.client;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class CreateClientRequest {

    private final String firstName;
    private String middleName;
    private final String lastName;
    private final String idNumber;
}





