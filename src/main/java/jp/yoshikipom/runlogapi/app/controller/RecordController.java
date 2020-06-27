package jp.yoshikipom.runlogapi.app.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import jp.yoshikipom.runlogapi.app.exception.BadRequestException;
import jp.yoshikipom.runlogapi.app.exception.NotFoundException;
import jp.yoshikipom.runlogapi.app.request.RecordRequest;
import jp.yoshikipom.runlogapi.domain.model.Record;
import jp.yoshikipom.runlogapi.domain.service.RecordService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/records"))
public class RecordController {

  private RecordService recordService;
  private ModelMapper modelMapper;

  public RecordController(RecordService recordService, ModelMapper modelMapper) {
    this.recordService = recordService;
    this.modelMapper = modelMapper;
  }

  @GetMapping("")
  List<Record> getRecords(@RequestParam(name = "month", required = false) String monthStr) {
    if (monthStr != null) {
      int year;
      int month;
      try {
        String[] parts = monthStr.split("-");
        year = Integer.parseInt(parts[0]);
        month = Integer.parseInt(parts[1]);
      } catch (Exception e) {
        throw new BadRequestException("month param in query is invalid");
      }
      return this.recordService.findRecordsByMonth(year, month);
    }
    return this.recordService.findRecords();
  }

  @GetMapping("/{date}")
  Record getRecord(@PathVariable("date") @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
    int year = date.getYear();
    int month = date.getMonthValue();
    int day = date.getDayOfMonth();
    Optional<Record> recordResult = this.recordService.findDayRecord(year, month, day);
    if (recordResult.isEmpty()) {
      throw new NotFoundException("Not found day record");
    }
    return recordResult.get();
  }

  @PutMapping("")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void putRecord(@Validated @RequestBody RecordRequest request,
      BindingResult result) {
    if (result.hasErrors()) {
      throw new BadRequestException(result.toString());
    }
    Record record = modelMapper.map(request, Record.class);
    this.recordService.register(record);
  }

  @DeleteMapping("/{date}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void deleteRecord(@PathVariable("date") @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
    try {
      this.recordService.unregister(date);
    } catch (EmptyResultDataAccessException e) {
      throw new BadRequestException("record is not found", e);
    }
  }
}
