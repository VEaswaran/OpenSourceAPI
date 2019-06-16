package com.rally.spring.controller


import com.rally.spring.service.OpenSourceService
import groovy.util.logging.Slf4j
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Api(value = 'Its Number descriptive service')
@RestController
@Slf4j
class OpenSourceApiController {

    @Autowired
    OpenSourceService openSourceService

    @ApiOperation(value = 'Service to retrieve number descriptive value from third party system')
    @GetMapping(path = "/api/number/{numberValue}")
    ResponseEntity getNumberDetails(@PathVariable('numberValue') Integer number) {
        log.info("Entered Number..{}", number)
        String response = openSourceService.getNumberDescription(number)
        ResponseEntity responseEntity = new ResponseEntity(response, HttpStatus.OK)
        log.info("Request processed successfully")
        return responseEntity
    }


}
