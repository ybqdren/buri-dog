package org.github.ybqdren.buridog.ssm.service;

import org.github.ybqdren.buridog.ssm.enums.DogSmsSendStateEnum;
import org.github.ybqdren.buridog.ssm.model.DogSmsData;
import org.github.ybqdren.buridog.ssm.model.DogSmsResponseResult;

import java.util.Collections;

/**
 * <h1> 短信发送器接口 </h1>
 *
 * @author zhao wen
 * @data 2022/2/18
 **/
public interface DogISmsSender {

    default DogSmsResponseResult sendMsgBySingle(DogSmsData data , String phones){
        DogSmsResponseResult result = new DogSmsResponseResult();
        result.setState(DogSmsSendStateEnum.SEND_FAIL);
        result = this.sendMsg(data , new String[]{phones});
        return result;
    }

    DogSmsResponseResult sendMsg(DogSmsData data , String[] phones);
}
