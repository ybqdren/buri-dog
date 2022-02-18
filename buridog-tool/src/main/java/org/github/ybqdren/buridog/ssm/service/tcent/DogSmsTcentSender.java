package org.github.ybqdren.buridog.ssm.service.tcent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20210111.models.SendStatus;

import org.github.ybqdren.buridog.ssm.config.DogSmsTcentProperties;
import org.github.ybqdren.buridog.ssm.enums.DogSmsSendStateEnum;
import org.github.ybqdren.buridog.ssm.model.DogSmsData;
import org.github.ybqdren.buridog.ssm.model.DogSmsResponseResult;
import org.github.ybqdren.buridog.ssm.service.DogISmsSender;

import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * <h1> 腾讯云短信发送器 </h1>
 *
 * @author zhao wen
 * @data 2022/2/18
 **/

@Component
public class DogSmsTcentSender implements DogISmsSender {
    private String code;
    private String areaInfo;
    private String senderId;
    private String originUrl;
    private String secretId;
    private String secretKey;
    private String sdkAppId;
    private String templateId;
    private String templatePrefix;
    private String signName;
    private String sessionContext;


    public DogSmsTcentSender(DogSmsTcentProperties properties) {
        DogSmsTcentProperties config = properties;

        this.code = config.getCode();
        this.areaInfo = config.getAreaInfo();
        this.senderId = config.getSenderId();
        this.originUrl = config.getOriginUrl();
        this.secretId = config.getSecretId();
        this.secretKey = config.getSecretKey();
        this.sdkAppId = config.getSdkAppId();
        this.templateId = config.getTemplateId();
        this.templatePrefix = config.getTemplatePrefix();
        this.signName = config.getSignName();
        this.sessionContext = config.getSessionContext();
    }


    @Override
    public DogSmsResponseResult sendMsgBySingle(DogSmsData data, String phones) {
        return DogISmsSender.super.sendMsgBySingle(data, phones);
    }

    @Override
    public DogSmsResponseResult sendMsg(DogSmsData data, String[] phones) {
        DogSmsResponseResult result = new DogSmsResponseResult();
        Optional<SendStatus[]> statuses = Optional.empty();

        try {
            Credential cred = new Credential(this.secretId, this.secretKey);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setReqMethod("POST");
            httpProfile.setConnTimeout(60);
            httpProfile.setEndpoint(originUrl);

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256");
            clientProfile.setHttpProfile(httpProfile);

            SendSmsRequest req = new SendSmsRequest();
            req.setSmsSdkAppId(this.sdkAppId);
            req.setSignName(this.signName);
            req.setSenderId(this.senderId);
            req.setSessionContext(this.sessionContext);
            req.setTemplateId(this.templateId);
            req.setPhoneNumberSet(phones);

            // 传入参数
            if (data.getIsParas()) {
                req.setTemplateParamSet(data.getParas());
            }

            SmsClient client = new SmsClient(cred, this.areaInfo, clientProfile);
            SendSmsResponse resp = client.SendSms(req);
            statuses = Optional.ofNullable(resp.getSendStatusSet());
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }


        if (statuses.isPresent() && !statuses.isEmpty()) {
            SendStatus sendStatus = statuses.get()[0];

            if ("ok".equals(sendStatus.getCode())) {
                result.setState(DogSmsSendStateEnum.SEND_SUCCESS);
            } else {
                result.setState(DogSmsSendStateEnum.SEND_FAIL);
            }

            result.setMessage(sendStatus.getMessage());
        }

        return result;
    }
}
