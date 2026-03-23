package com.glop.cibl_orga_sport.controller.api;

import com.glop.cibl_orga_sport.dto.HistoryDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.glop.cibl_orga_sport.utils.Constants.HISTORY_ENDPOINT;

public interface HistoryApi {
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<HistoryDto>> findAll();

    /*
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<HistoryDto> save(@RequestBody UserDto userDto, HistoryDto historyDto);

     */
}
