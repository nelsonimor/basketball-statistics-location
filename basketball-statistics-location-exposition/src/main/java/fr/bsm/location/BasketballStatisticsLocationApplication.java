package fr.bsm.location;

import java.net.InetAddress;
import java.net.UnknownHostException;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
@Slf4j
@EnableSwagger2
public class BasketballStatisticsLocationApplication {

  /**
   * Application main method
   *
   * @param args main aguments
   * @throws UnknownHostException
   */
  public static void main(String[] args) throws UnknownHostException {
    Environment env = SpringApplication.run(BasketballStatisticsLocationApplication.class, args)
        .getEnvironment();
    logApplicationStartup(env);
  }

  /**
   * Log application urls and used profiles
   *
   * @param env the spring environment object
   * @throws UnknownHostException
   */
  private static void logApplicationStartup(Environment env) throws UnknownHostException {
    var protocol = "http";
    if (env.getProperty("server.ssl.key-store") != null) {
      protocol = "https";
    }
    String serverPort = env.getProperty("server.port");
    String contextPath = env.getProperty("server.servlet.context-path");
    if (StringUtils.isBlank(contextPath)) {
      contextPath = "/";
    }
    String hostAddress = InetAddress.getLocalHost().getHostAddress();

    log.info("\n----------------------------------------------------------\n\t" //
            + "Application '{}' is running! Access URLs:\n\t" //
            + "Local: \t\t{}://localhost:{}{}swagger-ui/\n\t" //
            + "External: \t{}://{}:{}{}swagger-ui/\n\t" //
            + "Profile(s): \t{}\n----------------------------------------------------------", //
        env.getProperty("spring.application.name"), protocol, serverPort, contextPath, protocol,
        hostAddress,
        serverPort, contextPath, env.getActiveProfiles());
  }
  
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("fr.bsm")).build();
	}
}
