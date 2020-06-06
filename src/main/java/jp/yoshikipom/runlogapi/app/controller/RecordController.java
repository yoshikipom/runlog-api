package jp.yoshikipom.runlogapi.app.controller;

import java.util.List;
import jp.yoshikipom.runlogapi.app.exception.BadRequestException;
import jp.yoshikipom.runlogapi.domain.model.Record;
import jp.yoshikipom.runlogapi.domain.service.RecordService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecordController {

  private RecordService recordService;

  public RecordController(RecordService recordService) {
    this.recordService = recordService;
  }

  @GetMapping("/records")
  List<Record> getRecords() {
    return this.recordService.findRecords();
  }

  @PostMapping("/records")
  @ResponseStatus(HttpStatus.CREATED)
  Record postRecord(@Validated @RequestBody Record request, BindingResult result) {
    if (result.hasErrors()) {
      throw new BadRequestException(result.toString());
    }
    return this.recordService.register(request);
  }

  @GetMapping("/monthRecords")
  List<Record> getMonthRecords(@RequestParam(name = "year") int year,
      @RequestParam(name = "month") int month) {
    return this.recordService.findMonthRecords(year, month);
  }
}
