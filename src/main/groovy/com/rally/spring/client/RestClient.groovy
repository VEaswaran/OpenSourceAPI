package com.rally.spring.client

import com.rally.spring.exception.exception.ThirdPartyException
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
@Slf4j
class RestClient {

    @Value('${server.servlet.context-path}')
    String applicationContext

    @Autowired
    RestTemplate restTemplate

    Object getRestClient(String uri, Map headerParams, Map uriParams) {
        try {
            return makeClientCall(uri, headerParams, uriParams, HttpMethod.GET, null)
        } catch (Exception ex) {
            log.error("Service Failed ", uri)
            log.error("Failure Cause", ex.cause)
            throw new ThirdPartyException()
        }
    }


    Object postRestClient(String uri, Map headerParams, Map uriParams, String requestBody) {
        try {
            return makeClientCall(uri, headerParams, uriParams, HttpMethod.POST, requestBody)
        } catch (Exception ex) {
            throw ex
        }

    }


    Object putRestClient(String uri, Map headerParams, Map uriParams, String requestBody) {
        try {
            return makeClientCall(uri, headerParams, uriParams, HttpMethod.PUT, requestBody as String)
        } catch (Exception ex) {
            throw ex
        }

    }

    Object patchRestClient(String uri, Map headerParams, Map uriParams, String requestBody) {
        try {
            return makeClientCall(uri, headerParams, uriParams, HttpMethod.PATCH, requestBody)
        } catch (Exception ex) {
            throw ex
        }

    }

    private Object makeClientCall(String uri, Map headerParams, Map uriParams, HttpMethod httpMethod, String requestBody) {
        HttpHeaders headers = new HttpHeaders()
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON))
        addHeaders(headers, headerParams)
        buildUrlParameters(uriParams, uri)
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers)
        return restTemplate.exchange(uri, httpMethod, entity, String.class).getBody()
    }

    private String buildUrlParameters(Map uriParams, String uri) {
        StringBuffer url = new StringBuffer(uri)
        uriParams = uriParams.findAll { it.value != null }
        if (uriParams) {
            url.append("?")
            url.append(uriParams.collect { k, v -> "$k=$v" }?.join("&"))
        }
        return applicationContext + url.toString()
    }

    private void addHeaders(HttpHeaders headers, Map headerParams) {
        (headerParams != null && headerParams.size() > 0) ? headerParams?.each {
            headers.add(it.key as String, it.value as String)
        } : null
    }

}
