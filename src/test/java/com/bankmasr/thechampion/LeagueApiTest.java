package com.bankmasr.thechampion;

import com.bankmasr.thechampion.api.LeagueApi;
import com.bankmasr.thechampion.model.MatchPOJO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(LeagueApi.class)
class LeagueApiTest {



  private final MockMvc mvc;

  @MockBean
  private LeagueApi leagueApi;

  @Autowired
  LeagueApiTest(MockMvc mvc) {
    this.mvc = mvc;
  }


  @Test
  void submitNewMatch() throws Exception {

    List<Long> teamsIds=new ArrayList<>();
    teamsIds.add(101L);
    teamsIds.add(102L);

    MatchPOJO matchPOJO= new MatchPOJO(1,new Timestamp(System.currentTimeMillis()),teamsIds);
    ResponseEntity<String> response =new ResponseEntity<>("Match Submitted", HttpStatus.ACCEPTED);

    given(leagueApi.submitNewMatch(matchPOJO)).willReturn(response);

    mvc.perform(post("/league/newMatch")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("{\n" +
                    "    \"roundId\":1,\n" +
                    "    \"matchRealTime\":\"1639156727\",\n" +
                    "    \"teamsId\":[101,109]\n" +
                    " \n" +
                    "}"))
    ;

    Assertions.assertNotNull(response);
    Assertions.assertTrue(StringUtils.isNotBlank(String.valueOf(response)));
  }

  @Test
  void submitLeagueChampion() throws Exception {


    ResponseEntity<String> response =new ResponseEntity<>("Champion Submitted", HttpStatus.ACCEPTED);

    given(leagueApi.submitLeagueChampion(6L)).willReturn(response);

    mvc.perform(post("/league/champ/6")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isAccepted())
            .andExpect(status().isNoContent());
    ;

    Assertions.assertNotNull(response);
    Assertions.assertTrue(StringUtils.isNotBlank(String.valueOf(response)));
  }

}
