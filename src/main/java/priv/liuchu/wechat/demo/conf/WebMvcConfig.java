package priv.liuchu.wechat.demo.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author liuchu
 * Date 2022/9/22
 * Time 0:59
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                ((MappingJackson2HttpMessageConverter)converter).setDefaultCharset(StandardCharsets.UTF_8);
            }

            if (converter instanceof MappingJackson2XmlHttpMessageConverter) {
                ((MappingJackson2XmlHttpMessageConverter)converter).setDefaultCharset(StandardCharsets.UTF_8);
            }
        }

        WebMvcConfigurer.super.extendMessageConverters(converters);
    }
}
