package com.glop.cibl_orga_sport.handlers;

import com.glop.cibl_orga_sport.exception.ErrorCodes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {
    private Integer httpCode;

    private ErrorCodes codes;

    private String message;

    @Builder.Default
    private List<String> errors = new ArrayList<>();
}
