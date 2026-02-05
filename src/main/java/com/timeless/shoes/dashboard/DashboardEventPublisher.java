package com.timeless.shoes.dashboard;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DashboardEventPublisher {

    private final SimpMessagingTemplate socket;
    private final DashboardService dashboardService;

    public void broadcastUpdate() {
        socket.convertAndSend(
            "/topic/dashboard",
            dashboardService.metrics()
        );
    }
}
