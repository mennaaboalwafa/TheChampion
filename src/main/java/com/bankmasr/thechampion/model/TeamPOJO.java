package com.bankmasr.thechampion.model;



import com.bankmasr.thechampion.domain.entity.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamPOJO {
    @JsonIgnore
    private Long teamId;
    private long teamScore;
    private String teamName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PlayerPOJO> playerPOJO;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorMessage;
    private Boolean isWinner;



}
