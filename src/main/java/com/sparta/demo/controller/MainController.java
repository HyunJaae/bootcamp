package com.sparta.demo.controller;

import com.sparta.demo.dto.main.MainDetailResponseDto;
import com.sparta.demo.dto.main.MainResponseDto;
import com.sparta.demo.dto.main.OneClickRequestDto;
import com.sparta.demo.model.OneClick;
import com.sparta.demo.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    private final MainService mainService;

    @GetMapping("/")
    public ResponseEntity<MainResponseDto> getMain() throws IOException {
        return mainService.getMain();
    }

    @GetMapping("/category/{catName}")
    public ResponseEntity<MainResponseDto> getCatMain(@PathVariable String catName) throws IOException {
        log.info("controller catName: {}", catName);
        return mainService.getCatMain(catName);
    }

    @GetMapping("/detail/{debateId}")
    public ResponseEntity<MainDetailResponseDto> getMainDetail(@PathVariable Long debateId) throws IOException {
        log.info("controller debateId: {}", debateId);
        return mainService.getMainDetail(debateId);
    }

    @GetMapping("/one-click")
    public ResponseEntity<List<OneClick>> getOneClick() {
        return mainService.getOneClick();
    }

    @PutMapping("/one-click")
    public ResponseEntity<OneClick> sumOneClick(@RequestBody OneClickRequestDto oneClickRequestDto,
                                                HttpServletRequest request) {
        return mainService.sumOneClick(oneClickRequestDto, request);
    }
}
