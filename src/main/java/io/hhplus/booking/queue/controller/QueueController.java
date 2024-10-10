package io.hhplus.booking.queue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/queue")
public class QueueController {

    /**
     * queue toke 생성(대기)
     * */
    @PostMapping("/getUserToken")
    public Map<String, Object> getUserToken(@RequestParam Map<String, Object> requestUser) {
        Map<String, Object> userQueueInfo = new HashMap<>();

        userQueueInfo.put("userQueueToken", UUID.randomUUID().toString());
        userQueueInfo.put("userId", requestUser.get("userId"));
        userQueueInfo.put("queueStatus", "WAIT");
        userQueueInfo.put("regDt", LocalDateTime.now());
        userQueueInfo.put("updDt", LocalDateTime.now());
        return userQueueInfo;
    }

    /**
     * queue toke 생성(대기 종료)
     * */
    @PostMapping("/updateUserTokenInfo")
    public Map<String, Object> updateUserTokenInfo(@RequestParam Map<String, Object> requestUser) {
        Map<String, Object> userQueueInfo = new HashMap<>();
        userQueueInfo.put("userId", requestUser.get("userId"));
        userQueueInfo.put("queueStatus", "TURN");
        userQueueInfo.put("updDt", LocalDateTime.now());
        return userQueueInfo;
    }
}
