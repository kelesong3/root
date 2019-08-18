package com.sdg.security.core.validate.code;

import com.sdg.security.core.exception.ValidateCodeException;
import com.sdg.security.core.validate.code.processor.ValidateCodeProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class ValidateCodeProcessorHolder {

    /**
     * spring 自动注入validateCodeProcessors接口的所有示例，查找模式
     * key = 接口实现类名字
     * value = 接口实例
     */
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

/*    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }*/

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        //log.info("获取验证码处理器的名字,name = {}",name);
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        //log.info("获取验证码处理器,processor = {}",processor);
        if (processor == null) {
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }

}