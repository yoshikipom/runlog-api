package jp.yoshikipom.runlogapi.app.controller;

import java.util.List;
import jp.yoshikipom.runlogapi.domain.model.Record;
import jp.yoshikipom.runlogapi.domain.service.RecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class RecordController {

  private RecordService recordService;

  public RecordController(RecordService recordService) {
    this.recordService = recordService;
  }

  @GetMapping("/records")
  List<Record> getRecords() {
    return this.recordService.findRecords();
  }

  @GetMapping("/monthRecords")
  List<Record> getMonthRecords(@RequestParam(name = "year") int year,
      @RequestParam(name = "month") int month) {
    return this.recordService.findMonthRecords(year, month);
  }
}
