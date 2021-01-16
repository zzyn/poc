package com.spring.libs.bind;

import lombok.*;
import lombok.experimental.Accessors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class HttpContext implements Serializable {
    private HttpServletRequest request;
    private HttpServletResponse response;
}
