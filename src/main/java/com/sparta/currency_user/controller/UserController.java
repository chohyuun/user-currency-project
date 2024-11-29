package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.StatusResponse;
import com.sparta.currency_user.dto.UserRequestDto;
import com.sparta.currency_user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final StatusResponse statusResponse = new StatusResponse("success", 200, null);

    /**
     * 유저 정보 출력
     */
    @GetMapping
    public ResponseEntity<StatusResponse> findUsers() {

        statusResponse.setStatusCode(200);
        statusResponse.setData(userService.findAll());

        return new ResponseEntity<>(statusResponse, HttpStatus.OK);
    }

    /**
     * 유저 단건 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<StatusResponse> findUser(@PathVariable Long id) {
        if(id == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "검색할 유저를 입력해 주세요.");
        }

        statusResponse.setStatusCode(200);
        statusResponse.setData(userService.findById(id));

        return new ResponseEntity<>(statusResponse, HttpStatus.OK);
    }

    /**
     * 유저 생성
     */
    @PostMapping
    public ResponseEntity<StatusResponse> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {

        statusResponse.setStatusCode(201);
        statusResponse.setData(userService.save(userRequestDto));

        return new ResponseEntity<>(statusResponse, HttpStatus.CREATED);
    }

    /**
     * 유저 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);

        if(id == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제할 유저를 입력해 주세요.");
        }

        return ResponseEntity.ok().body("정상적으로 삭제되었습니다.");
    }
}
