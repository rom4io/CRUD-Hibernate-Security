package crud.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        //String role = authentication.getAuthorities().toString();

        Set<String> role = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (role.contains("ROLE_ADMIN")){
            httpServletResponse.sendRedirect("/admin/users");
        }
        else if(role.contains(("ROLE_USER"))){
            httpServletResponse.sendRedirect("/user/userinfo");
        }
        else {httpServletResponse.sendRedirect("/unsuccessful");}
        //httpServletResponse.sendRedirect("/hello");
    }
}