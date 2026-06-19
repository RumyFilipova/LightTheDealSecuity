package bg.softuni.lightthedeal.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Set;

@Component
public class SessionCheckInterceptor implements HandlerInterceptor {

    public static final Set<String> UNAUTHENTICATED_ENDPOINTS= Set.of("/login","/register","/home","/");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(UNAUTHENTICATED_ENDPOINTS.contains(request.getServletPath())){
            return true;
        }

      HttpSession session = request.getSession(false);

      if(session == null) {
          response.sendRedirect("/login");
          return false;
      }

      Object userId =session.getAttribute("userId");

      if(userId==null){
          response.sendRedirect("/login");
          return false;
      }

        return true;
    }
}
