package wsiz.groupproject.demo.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import wsiz.groupproject.demo.model.User;
import wsiz.groupproject.demo.model.UserLoginType;
import wsiz.groupproject.demo.service.UserRepository;

import java.time.LocalDateTime;

@Configuration
@EnableOAuth2Sso

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfiguration.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/index.html")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
        return map -> {
            String principalId = (String) map.get("id");
            User user = userRepository.findByEmail(map.get("email"));
            if (user == null) {
                LOGGER.info("No user found, generating profile for {}", principalId);
                user = new User();
                user.setPrincipalId(principalId);
                user.setCreated(LocalDateTime.now());
                user.setEmail((String) map.get("email"));
                user.setFullName((String) map.get("name"));
                user.setPhoto((String) map.get("picture"));
                user.setLoginType(UserLoginType.GOOGLE);
                user.setLastLogin(LocalDateTime.now());

            } else {
                user.setLastLogin(LocalDateTime.now());
            }
            userRepository.save(user);
            return user;
        };
    }
}
