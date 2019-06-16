package com.rally.spring.service

import com.rally.spring.client.RestClient
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
@Slf4j
class OpenSourceService {


    @Value('${openSourceService.host}')
    String hostName

    @Value('${openSourceService.path}')
    String path

    @Autowired
    RestClient restClient


    String getNumberDescription(Integer number) {
        String url = (hostName + path).replace("{number}", number.toString())
        String response = restClient.getRestClient(url, [:], [:])
        response
    }


}
