package com.ygo.server.api.controller;

import com.ygo.server.api.service.interfaces.LogService;
import com.ygo.server.api.service.vo.LogVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/log")
public class LogController {

    private final LogService logService;

    // GET
    // POST
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseEntity<Object> insert(@RequestBody Map<String, List<LogVO>> param) {
        List<LogVO> logList = new ArrayList<>();
        for (LogVO vo : param.get("logList")) {
            if (vo.isValid()) {
                logList.add(vo);
            }
        }

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("logList", logList);

        int result = logService.insertLogList(paramMap);

        if (result > 0) {
            return ResponseEntity.ok().body(result + "건 입력 되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("유효하지 않은 요청입니다.");
        }
    }
    // PATCH
    // DELETE
}
