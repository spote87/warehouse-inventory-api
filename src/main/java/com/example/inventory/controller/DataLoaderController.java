package com.example.inventory.controller;

import com.example.inventory.strategy.DataLoaderContext;
import com.example.inventory.strategy.DataLoaderStrategy;
import com.example.inventory.strategy.DataLoaderStrategyFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

/**
 * @author Shivaji Pote
 **/
@Log4j2
@RestController
@RequiredArgsConstructor
public class DataLoaderController {

  private final DataLoaderStrategyFactory strategyFactory;

  private DataLoaderContext dataLoaderContext;

  @PostMapping(value = "load", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<String> loadData(@RequestParam("file") final MultipartFile multipartFile) throws IOException {
    log.debug("Loading inventory");
    final String fileName = multipartFile.getOriginalFilename();
    if (!fileName.endsWith(".json")) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file extension");
    }
    final DataLoaderStrategy strategy = strategyFactory.getStrategy(fileName.substring(0, fileName.lastIndexOf('.')));
    if (strategy == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file name. Allowed file names are products.json and inventory.json");
    }
    dataLoaderContext = new DataLoaderContext(strategy);
    dataLoaderContext.executeStrategy(multipartFile.getInputStream());
    return ResponseEntity.ok("Data loaded successfully!!");
  }
}
