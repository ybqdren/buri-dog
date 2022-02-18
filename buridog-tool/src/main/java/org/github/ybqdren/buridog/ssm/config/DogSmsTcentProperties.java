package org.github.ybqdren.buridog.ssm.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <h1> 腾讯云短信相关配置 </h1>
 *
 * @author zhao wen
 * @data 2022/2/18
 **/

@Getter
@Setter
public class DogSmsTcentProperties {
    /**
     * code
     */
    private String code;

    /**
     * area info
     */
    private String areaInfo;

    /**
     * sender id
     */
    private String senderId;

    /**
     * 腾讯云请求地址
     */
    private String originUrl;

    /**
     * secret id
     */
    private String secretId;

    /**
     * secret key
     */
    private String secretKey;

    /**
     * sdk app id
     */
    private String sdkAppId;

    /**
     * template id
     */
    private String templateId;

    /**
     * 模板名称前缀
     */
    private String templatePrefix;

    /**
     * sign name
     */
    private String signName;

    /**
     * session context
     */
    private String sessionContext;
}
