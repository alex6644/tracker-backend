package tracker.trackerback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tracker.trackerback.dto.WorkoutTemplateDto;
import tracker.trackerback.model.WorkoutTemplate;
import tracker.trackerback.service.WorkoutTemplateService;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
@RequiredArgsConstructor
public class WorkoutTemplateController {
    private final WorkoutTemplateService templateService;

    @PostMapping
    public ResponseEntity<WorkoutTemplate> createTemplate(@RequestBody WorkoutTemplateDto dto) {
        return ResponseEntity.ok(templateService.createTemplate(dto));
    }

    @GetMapping("/my-templates")
    public ResponseEntity<List<WorkoutTemplate>> getMyTemplates() {
        return ResponseEntity.ok(templateService.getMyTemplates());
    }
}
