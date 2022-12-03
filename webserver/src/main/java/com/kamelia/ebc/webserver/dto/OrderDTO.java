package com.kamelia.ebc.webserver.dto;

import com.kamelia.ebc.common.base.ReturnState;

import java.time.Instant;

public record OrderDTO(
    Instant date,
    String ordererName,
    String comment,
    String returnState
) {
}
