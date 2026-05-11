package com.marvisa.logistic.dashboard;

import com.marvisa.logistic.dashboard.dto.DashboardResumenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/resumen")
    public DashboardResumenResponse resumen() {
        return dashboardService.resumen();
    }
}