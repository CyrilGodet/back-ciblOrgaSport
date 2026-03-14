package com.glop.cibl_orga_sport.dto;

import com.glop.cibl_orga_sport.data.History;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class HistoryDto {
    private Long id;
    private String action;
    private String status;
    private UtilisateurDTO user;
    private String userConnected;
    private Instant lastModifiedDate;

    public static HistoryDto fromEntity(History history) {
        if (history == null) {
            return null;
        }

        HistoryDto historyDto = HistoryDto.builder()
                .id(history.getIdHistory())
                .action(history.getAction())
                .status(history.getStatus())
                .user(UtilisateurDTO.fromEntity(history.getUser()))
                .userConnected(history.getUserConnected())
                .lastModifiedDate(history.getLastModifiedDate())
                .build();
        return historyDto;
    }

    public static History toEntity(HistoryDto historyDto) {
        if (historyDto == null) {
            return null;
        }
        History history = new History();
        history.setIdHistory(historyDto.getId());
        history.setAction(historyDto.getAction());
        history.setStatus(historyDto.getStatus());
        history.setUser(UserDto.toEntity(historyDto.getUser()));
        history.setUserConnected(historyDto.getUserConnected());
        history.setLastModifiedDate(historyDto.getLastModifiedDate());
        return history;
    }

    @Override
    public String toString() {
        return "HistoryDto{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", status" + status +
                ", linelabel" + lineLabel +
                ", user" + user +
                '}';
    }

}
