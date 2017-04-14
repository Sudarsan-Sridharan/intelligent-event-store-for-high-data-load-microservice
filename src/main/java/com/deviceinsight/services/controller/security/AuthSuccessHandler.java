package com.deviceinsight.services.controller.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthSuccessHandler.class);

  //  private final ObjectMapper mapper;

   /* @Autowired
    AuthSuccessHandler(MappingJackson2HttpMessageConverter messageConverter) {
        this.mapper = messageConverter.getObjectMapper();
    }*/

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);

       /* NuvolaUserDetails userDetails = (NuvolaUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        userDetails.setUser(user);

        LOGGER.info(userDetails.getUsername() + " got is connected ");

        PrintWriter writer = response.getWriter();
        mapper.writeValue(writer, user);
        writer.flush();*/
    }
}
