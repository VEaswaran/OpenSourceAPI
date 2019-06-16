package com.rally.spring.controller

import com.rally.spring.service.OpenSourceService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class OpenSourceApiControllerSpec extends Specification {
    OpenSourceApiController openSourceApiController
    OpenSourceService openSourceService

    def setup() {
        openSourceService = Mock(OpenSourceService)
        openSourceApiController = new OpenSourceApiController(openSourceService: openSourceService)
    }

    def "[TestCase] Method to test OpenSourceApiController"() {

        when:
        ResponseEntity  result = openSourceApiController.getNumberDetails(85)
        then:
        1 * openSourceService.getNumberDescription(_ as Integer) >> "Welcome to the World"
        result.statusCode == HttpStatus.OK
        result.body.equals("Welcome to the World")

    }
}
