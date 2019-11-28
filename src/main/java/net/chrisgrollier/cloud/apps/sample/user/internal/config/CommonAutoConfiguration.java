package net.chrisgrollier.cloud.apps.sample.user.internal.config;

import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import net.chrisgrollier.cloud.apps.sample.user.i18n.MessageManager;
import net.chrisgrollier.cloud.apps.sample.user.internal.i18n.DefaultMessageManager;

/**
 * Auto configuration class responsible to create a {@link LogServiceFactory}
 * and {@link DefaultMessageManager} beans if not present in the application
 * context. Is also responsible to populate logging system global environment
 * properties based on an instance of a spring {@link Environment} object.
 */
@Configuration
public class CommonAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public MessageManager messageManager(MessageSource messageSource) {
		LoggerFactory.getLogger(CommonAutoConfiguration.class).info("autoConfService",
				"instanciating DefaultMessageManager");
		return new DefaultMessageManager(messageSource);
	}

}
