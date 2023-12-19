package io.github.xiechanglei.base.web.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义配置项目
 */
@Data
@Component
@ConfigurationProperties(prefix = "io.github.xiechanglei.base.web")
public class XieBaseWebConfigProperties {
    private int pageDefaultSize = 20; //分页默认大小
    private int pageDefaultPage = 1; //分页默认页码
    private boolean responseAdvice = true; //是否开启统一响应处理
    private boolean exceptionAdvice = true; //是否开启统一异常处理
    private boolean useDateResolver = true; // 是否开启日期解析器
    private boolean usePageResolver = true; // 是否开启分页解析器
    private boolean cors = true; // 是否开启跨域
    private String[] crossOrigins = new String[]{"*"}; // 跨域的域名
    private String[] crossMethods = new String[]{"*"}; // 跨域的方法
    private String[] crossHeaders = new String[]{}; // 跨域的请求头
}
