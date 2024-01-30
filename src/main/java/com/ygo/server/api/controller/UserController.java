package com.ygo.server.api.controller;

import com.ygo.server.api.service.interfaces.UserService;
import com.ygo.server.api.service.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    //GET
    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable String id) {
        boolean isExist = userService.isExistID(id);

        if (isExist) return ResponseEntity.status(409).body("이미 존재하는 아이디입니다.");
        return ResponseEntity.ok().body("생성 가능한 아이디입니다.");
    }
    //POST
    @RequestMapping(value = "/validate", method = RequestMethod.POST) // 상태 변경이 없지만, 예외적으로 POST
    public ResponseEntity<Object> validate(@RequestBody UserVO userVo) {
//        boolean isExist = userService.isExistID(userVo);
//        if (!isExist) {
//            return ResponseEntity.badRequest().body("존재하지 않는 아이디입니다.");
//        }

        return ResponseEntity.ok().body("isExist");
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<Object> signup(@RequestBody UserVO userVo) {
        if (!userVo.isValid()) return ResponseEntity.badRequest().body("아이디와 비밀번호는 필수 입력 값입니다.");
        boolean isSignUpDone = userService.signUp(userVo);

        if (isSignUpDone) {
            return ResponseEntity.ok().body(userVo);
        } else {
            return ResponseEntity.badRequest().body("입력에 실패하였습니다. 잠시 후 다시 이용해주세요.");
        }
    }
    //PATCH
    //DELETE
}
