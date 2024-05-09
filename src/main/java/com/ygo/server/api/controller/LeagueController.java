package com.ygo.server.api.controller;

import com.ygo.server.api.service.interfaces.LeagueService;
import com.ygo.server.api.service.vo.LeagueVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/league")
public class LeagueController {

    private final LeagueService leagueService;

    // GET
    @RequestMapping(value = "/find/{name}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable String name) {
        boolean isExist = leagueService.isExistLeague(name);

        if (isExist) return ResponseEntity.status(409).body("이미 존재하는 대회명입니다.");
        return ResponseEntity.ok().body("생성 가능한 대회명입니다.");
    }
    // POST
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseEntity<Object> insert(@RequestBody LeagueVO leagueVo) {
        if (!leagueVo.isValid()) return ResponseEntity.badRequest().body("아이디와 대회명은 필수 입력 값입니다.");

        boolean isInsertDone = leagueService.insertLeague(leagueVo);

        if (isInsertDone) {
            return ResponseEntity.ok().body(leagueVo.getSeq());
        } else {
            return ResponseEntity.badRequest().body("입력에 실패하였습니다. 잠시 후 다시 이용해주세요.");
        }
    }
    // PATCH
    // DELETE
}
