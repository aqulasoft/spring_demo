package com.example.test212.security;

import com.example.test212.database.entities.User;
import com.example.test212.database.repositories.UserRepository;
import com.example.test212.security.models.OurAuthToken;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
public class MainAuthFilter implements Filter {

    private static final String LOGIN_HEADER = "x-access-login";
    private static final String PASSWORD_HEADER = "x-access-password";

    protected final AuthenticationFailureHandler failureHandler;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public MainAuthFilter(AuthenticationFailureHandler failureHandler,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.failureHandler = failureHandler;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        OurAuthToken token = tryAuth((HttpServletRequest) req, (HttpServletResponse) res);
        if (token == null) {
            failureHandler.onAuthenticationFailure(
                    (HttpServletRequest) req,
                    (HttpServletResponse) res,
                    new AuthenticationServiceException("Invalid user name or password")
            );
        } else {
            SecurityContextHolder.getContext().setAuthentication(token);
            chain.doFilter(req, res);
        }
    }

    @Nullable
    protected OurAuthToken tryAuth(HttpServletRequest req, HttpServletResponse res) {
        String login = req.getHeader(LOGIN_HEADER);
        String password = req.getHeader(PASSWORD_HEADER);
        String passwordHash = passwordEncoder.encode(password + "sada");

        Optional<User> optionalUser = userRepository.findOptionalByEmailAndPassword(login, passwordHash);

        if (optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();
        Collection<? extends GrantedAuthority> authorities = Collections.singleton(
                new SimpleGrantedAuthority("BASE_USER")
        );

        return new OurAuthToken(user.getId(), user, authorities);
    }
}
