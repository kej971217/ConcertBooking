package io.hhplus.booking.point.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/point")
public class PointController {

    /**
     * getUserPoint - 사용자 포인트 조회
     */
    @PostMapping("/selectUserPoint")
    public Map<String, Object> selectUserPoint(@RequestParam Map<String, Object> requestInfo) {
        Map<String, Object> userPointInfo = new HashMap<>();

        userPointInfo.put("userId", requestInfo.get("userId"));
        userPointInfo.put("userPoint", 100000);  // 임시 포인트 잔액

        return userPointInfo;
    }

    /**
     * 포인트 충전 - 사용자 포인트 충전
     */
    @PostMapping("/chargePoint")
    public Map<String, Object> chargePoint(@RequestParam Map<String, Object> requestInfo) {
        Map<String, Object> userPointInfo = new HashMap<>();

        userPointInfo.put("userId", requestInfo.get("userId"));
        userPointInfo.put("requestPoint", requestInfo.get("requestPoint")); // 요청된 포인트 충전
        userPointInfo.put("userPoint", 150000);  // 임시로 충전 후 총 포인트
        userPointInfo.put("pointType", "POINT_CHARGE");
        userPointInfo.put("updDt", LocalDateTime.now());

        return userPointInfo;
    }

    /**
     * 포인트 사용 - 사용자 포인트 사용
     */
    @PostMapping("/usePoint")
    public Map<String, Object> usePoint(@RequestParam Map<String, Object> requestInfo) {
        Map<String, Object> userPointInfo = new HashMap<>();

        // MOCK 데이터를 사용하여 임시 사용자 포인트 사용 처리
        userPointInfo.put("userId", requestInfo.get("userId"));
        userPointInfo.put("requestPoint", requestInfo.get("requestPoint")); // 요청된 포인트 차감
        userPointInfo.put("userPoint", 90000);  // 임시로 남은 포인트
        userPointInfo.put("pointType", "POINT_USE");
        userPointInfo.put("updDt", LocalDateTime.now());

        return userPointInfo;
    }
}
