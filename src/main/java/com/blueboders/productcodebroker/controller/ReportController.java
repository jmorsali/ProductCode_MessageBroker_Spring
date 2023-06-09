package com.blueboders.productcodebroker.controller;

import com.blueboders.productcodebroker.dtos.DailyReportDto;
import com.blueboders.productcodebroker.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/report")
public class ReportController {
    final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DailyReportDto>> getDailyReport(Date reportDate) {
        try {
            List<DailyReportDto> result = reportService.getDailyReport(reportDate);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
