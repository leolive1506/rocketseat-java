package br.com.leo.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.leo.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// toda req passa por esse filtro
@Component
public class FilterTaskAuth extends OncePerRequestFilter {
  @Autowired
  private IUserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {
      var servletPath = request.getServletPath();

      if (!servletPath.startsWith("/tasks/")) {
        filterChain.doFilter(request, response);
        return;
      }

      // pegar user e senha
      var authorization = request.getHeader("Authorization");
      // Base 79871u4jdas
      var authEncoded = authorization.substring("Basic".length()).trim();
      byte[] authDecode = Base64.getDecoder().decode(authEncoded);
      // leolive:123123
      var authString = new String(authDecode);
      String[] credentials = authString.split(":");
      String username = credentials[0];
      String password = credentials[1];

      // validar usuario
      var user = this.userRepository.findByUsername(username);
      if (user == null) {
        response.sendError(401);
        return;
      }
      // validar senha
      var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
      if (passwordVerify.verified) {
        request.setAttribute("idUser", user.getId());
        // continua
        filterChain.doFilter(request, response);
      } else {
        response.sendError(401);
      }
    }
  
}
