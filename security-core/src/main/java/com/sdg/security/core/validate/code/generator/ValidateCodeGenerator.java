package com.sdg.security.core.validate.code.generator;

import com.sdg.security.core.validate.code.entity.Code;
import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {
    Code generate(ServletWebRequest request);
}
