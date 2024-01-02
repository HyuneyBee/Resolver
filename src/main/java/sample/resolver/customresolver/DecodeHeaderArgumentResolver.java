package sample.resolver.customresolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Base64;

public class DecodeHeaderArgumentResolver implements HandlerMethodArgumentResolver {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(DecodeHeader.class);
        boolean hasUserType = UserInfo.class.isAssignableFrom(parameter.getParameterType());
        return hasAnnotation && hasUserType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String userInfoJson = webRequest.getHeader("userInfo");
        UserInfo userInfo = objectMapper.readValue(userInfoJson, UserInfo.class);

        String userName = new String(Base64.getDecoder().decode(userInfo.getName()));
        userInfo.setName(userName);

        webRequest.setAttribute("name",userInfo.getName(),0);
        return userInfo;
    }
}
