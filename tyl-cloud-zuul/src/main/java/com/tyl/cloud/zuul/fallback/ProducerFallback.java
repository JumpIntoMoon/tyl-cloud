package com.tyl.cloud.zuul.fallback;

import com.tyl.cloud.zuul.filter.MyFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 实现类通过实现getRoute方法，告诉Zuul它是负责哪个route定义的熔断。
 * 而fallbackResponse方法则是告诉 Zuul 断路出现时，它会提供一个什么返回值来处理请求。
 * @author: tangYiLong
 * @create: 2018-05-04 14:28
 **/
@Component
public class ProducerFallback implements FallbackProvider {

    private static Logger logger = LoggerFactory.getLogger(ProducerFallback.class);

    /**
     * 指定路由对象，告诉Zuul它是负责哪个route定义的熔断
     * @return
     */
    @Override
    public String getRoute() {
        return "eureka-client";
    }

    /**
     * 路由熔断时，调用该方法处理
     * @param route
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause != null && cause.getCause() != null) {
            String reason = cause.getCause().getMessage();
            logger.info("Excption {}",reason);
        }
        return fallbackResponse();

    }

    /**
     * 路由熔断时的处理方法，返回一个ClientHttpResponse对象
     * @return
     */
    public ClientHttpResponse fallbackResponse(){
        return new ClientHttpResponse(){
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("The service is unavailable.".getBytes());
            }

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }
        };
    }
}
