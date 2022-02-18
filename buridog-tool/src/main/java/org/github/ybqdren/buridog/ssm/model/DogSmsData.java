package org.github.ybqdren.buridog.ssm.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <h1> 短信数据 </h1>
 *
 * @author zhao wen
 * @data 2022/2/18
 **/

@Data
public class DogSmsData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 短信内容
     */
    private String msg;

    /**
     * 是否包含动态参数
     */
    private Boolean isParas = Boolean.FALSE;

    /**
     * 动态参数，默认为空
     */
    private String[] paras = new String[]{};


}
