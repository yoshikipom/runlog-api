package jp.yoshikipom.runlogapi.app.controller;

import java.util.List;
import jp.yoshikipom.runlogapi.domain.model.MonthRecord;
import jp.yoshikipom.runlogapi.domain.service.RecordService;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/monthRecords"))
public class MonthRecordController {

  private RecordService recordService;
  private ModelMapper modelMapper;

  public MonthRecordController(RecordService recordService, ModelMapper modelMapper) {
    this.recordService = recordService;
  }

  @GetMapping("")
  List<MonthRecord> getMonthRecords(@Validated @RequestParam(name = "year") Integer year) {
    return this.recordService.findMonthRecords(year);
  }
}
