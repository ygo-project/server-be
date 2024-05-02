package com.ygo.server.api.controller;

import com.ygo.server.api.service.interfaces.UserService;
import com.ygo.server.api.service.vo.TokenVO;
import com.ygo.server.api.service.vo.UserVO;
import com.ygo.server.constants.AuthConstants;
import com.ygo.server.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    @RequestMapping(value = "/login", method = RequestMethod.POST) // 상태 변경이 없지만, 암호화를 위해 POST
    public ResponseEntity<Object> login(@RequestBody UserVO userVo) {
        UserVO user = userService.getMatchedUser(userVo);
        if (user == null) {
            return ResponseEntity.badRequest().body("일치하는 정보가 존재하지 않습니다.");
        }

        TokenVO result = TokenVO.builder()
                .grantType(AuthConstants.TOKEN_TYPE)
                .auth(user.getUserAuth())
                .accessToken(TokenUtils.generateAccessToken(user))
                .refreshToken(TokenUtils.generateRefreshToken(user))
                .build();
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.POST) // 상태 변경이 없지만, 암호화를 위해 POST
    public ResponseEntity<Object> refresh(HttpServletRequest request, @RequestBody UserVO userVo) {
        UserVO user = userService.getMatchedUserById(userVo);
        if (user == null) {
            return ResponseEntity.badRequest().body("일치하는 정보가 존재하지 않습니다.");
        }

        String header = request.getHeader(AuthConstants.AUTH_HEADER);
        String token = TokenUtils.getTokenFromHeader(header);
        String userId = TokenUtils.getUserIdFromToken(token);

        if (userId.equals(user.getUserId())) {
            TokenVO result = TokenVO.builder()
                    .grantType(AuthConstants.TOKEN_TYPE)
                    .auth(user.getUserAuth())
                    .accessToken(TokenUtils.generateAccessToken(user))
                    .refreshToken(TokenUtils.generateRefreshToken(user))
                    .build();

            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.badRequest().body("일치하는 정보가 존재하지 않습니다.");
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<Object> signup(@RequestBody UserVO userVo) {
        if (!userVo.isValid()) return ResponseEntity.badRequest().body("아이디와 비밀번호는 필수 입력 값입니다.");
        boolean isSignUpDone = userService.signUp(userVo);

        if (isSignUpDone) {
            UserVO user = userService.getMatchedUser(userVo);

            TokenVO result = TokenVO.builder()
                    .grantType(AuthConstants.TOKEN_TYPE)
                    .auth(user.getUserAuth())
                    .accessToken(TokenUtils.generateAccessToken(user))
                    .refreshToken(TokenUtils.generateRefreshToken(user))
                    .build();

            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.badRequest().body("입력에 실패하였습니다. 잠시 후 다시 이용해주세요.");
        }
    }
    //PATCH
    //DELETE
}
