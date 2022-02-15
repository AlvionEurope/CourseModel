package example.controller;

import example.service.TeacherReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("reports")
public class ReportController {
    private static final String MEDIA_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private final TeacherReportService teacherReportService;

    @GetMapping(value = "teacher-workload",
            produces = MEDIA_TYPE_XLSX)
    public ResponseEntity<byte[]> generateTeacherWorkloadReport() {
        return ResponseEntity.ok(teacherReportService.generateWorkLoadReport());
    }
}
