package jp.yoshikipom.runlogapi.app.controller;

import jp.yoshikipom.runlogapi.domain.model.YearRecords;
import jp.yoshikipom.runlogapi.domain.service.RecordService;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/yearRecords"))
public class YearRecordController {

  private RecordService recordService;
  private ModelMapper modelMapper;

  public YearRecordController(RecordService recordService, ModelMapper modelMapper) {
    this.recordService = recordService;
  }

  @GetMapping("")
  YearRecords getRecords(@Validated @RequestParam(name = "year") Integer year) {
    return this.recordService.findYearRecords(year);
  }
}
