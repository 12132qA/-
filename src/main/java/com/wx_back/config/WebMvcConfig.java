package com.wx_back.config;
import com.wx_back.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import java.util.List;

//@Configuration
@Slf4j
public class WebMvcConfig extends WebMvcConfigurationSupport {
/*
* 静态资源映射
*
* */
/**
 * 扩展 mvc框架的消息转换器
 * @param converters
 *
 * */
protected void extendMessageConverters(List<HttpMessageConverter<?>> converters){
    // 创建消息转换器对象
    log.info("扩展消息转换器...");
    MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
    // 设置对象转换器，底层使用JackSon将Java 对象转为 json
    messageConverter.setObjectMapper(new JacksonObjectMapper());
    // 将上面的消息转换器对象追加的mvc框架的转换器集合中
//    super.extendMessageConverters(converters);
    converters.add(0,messageConverter);
}

}
