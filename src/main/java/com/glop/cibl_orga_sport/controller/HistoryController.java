package com.glop.cibl_orga_sport.controller;

import com.glop.cibl_orga_sport.controller.api.HistoryApi;
import com.glop.cibl_orga_sport.dto.HistoryDto;
import com.glop.cibl_orga_sport.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.glop.cibl_orga_sport.utils.Constants.HISTORY_ENDPOINT;

@RequestMapping(HISTORY_ENDPOINT)
@RestController
public class HistoryController implements HistoryApi {
    private HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @Override
    public ResponseEntity<List<HistoryDto>> findAll() {
        return ResponseEntity.ok(historyService.findAll());
    }

    /*
    @Override
    public ResponseEntity<HistoryDto> save(UserDto userDto, HistoryDto historyDto) {
        return ResponseEntity.ok(historyService.saveUser(userDto,historyDto));
    }

     */
}