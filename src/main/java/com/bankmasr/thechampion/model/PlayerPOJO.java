package com.bankmasr.thechampion.model;

import com.bankmasr.thechampion.domain.entity.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
public class PlayerPOJO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long playerId;
    @Size(min = 2, max = 100)
    private String playerName;
    @Size(min = 2, max = 200)
    private String playerJobTitle;
    @NotNull
    private Long playerStaffId;
    @Email
    private String playerEmail;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorMessage;
    private boolean isChamp;

    public PlayerPOJO(Player playerByPlayerId) {
        this.playerId= playerByPlayerId.getPlayerId();
    }
}
