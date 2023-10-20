package yukiii.mangayukiii.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "hello")
@Tag(name = "Hello", description = "Controller for hello")
public class HelloController {

  @RequestMapping(method = RequestMethod.GET)
  @Operation(summary = "Hello", operationId = "Hello")
  public String hello(){
    return "Hello, welcome to mangayukii";
  }

}