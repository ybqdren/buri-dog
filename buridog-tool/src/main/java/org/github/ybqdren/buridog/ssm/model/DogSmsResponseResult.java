package org.github.ybqdren.buridog.ssm.model;

import lombok.Data;
import org.github.ybqdren.buridog.ssm.enums.DogSmsSendStateEnum;

import java.io.Serializable;

/**
 * <h1> 短信返回结果对象 </h1>
 *
 * @author zhao wen
 * @data 2022/2/18
 **/

@Data
public class DogSmsResponseResult implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 发送状态
     */
    private DogSmsSendStateEnum state = DogSmsSendStateEnum.SEND_FAIL;

    /**
     * 返回消息
     */
    private String message = "";

}
