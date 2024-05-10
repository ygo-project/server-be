package com.ygo.server.api.controller;

import com.ygo.server.api.service.interfaces.FighterService;
import com.ygo.server.api.service.vo.FighterVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/fighter")
public class FighterController {

    private final FighterService fighterService;

    // GET
    @RequestMapping(value = "/list/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Object> list (@PathVariable("userId") String userId) {
        if (userId == null || userId.isEmpty()) return ResponseEntity.badRequest().body("아이디는 필수 입력 값입니다.");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        List<FighterVO> fighterList = fighterService.getFighterByUser(paramMap);

        if (fighterList != null && !fighterList.isEmpty()) {
            return ResponseEntity.ok().body(fighterList);
        } else {
            return ResponseEntity.ok().body(null);
        }
    }

    @RequestMapping(value = "/validList/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Object> validList (@PathVariable("userId") String userId) {
        if (userId == null || userId.isEmpty()) return ResponseEntity.badRequest().body("아이디는 필수 입력 값입니다.");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        paramMap.put("status", "A");
        List<FighterVO> fighterList = fighterService.getFighterByUser(paramMap);

        if (fighterList != null && !fighterList.isEmpty()) {
            return ResponseEntity.ok().body(fighterList);
        } else {
            return ResponseEntity.ok().body(null);
        }
    }

    // POST
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseEntity<Object> insert(@RequestBody FighterVO fighterVO) {
        if (!fighterVO.isValid()) return ResponseEntity.badRequest().body("아이디와 이름은 필수 입력 값입니다.");

        boolean isInsertDone = fighterService.insertFighter(fighterVO);

        if (isInsertDone) {
            return ResponseEntity.ok().body("ok");
        } else {
            return ResponseEntity.badRequest().body("입력에 실패하였습니다. 잠시 후 다시 이용해주세요.");
        }
    }
    // PATCH
    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    public ResponseEntity<Object> update(@RequestBody FighterVO fighterVO) {
        if (!fighterVO.isValid()) return ResponseEntity.badRequest().body("시퀀스는 필수 입력 값입니다.");

        boolean isUpdateDone = fighterService.updateFighter(fighterVO);

        if (isUpdateDone) {
            return ResponseEntity.ok().body("ok");
        } else {
            return ResponseEntity.badRequest().body("수정에 실패하였습니다. 잠시 후 다시 이용해주세요.");
        }
    }
    // DELETE
}
