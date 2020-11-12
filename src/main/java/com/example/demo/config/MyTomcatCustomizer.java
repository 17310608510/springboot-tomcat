package com.example.demo.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * @author 作者 zuoruibo:
 * @date 创建时间：2020年11月12日 下午4:21:38
 * @version 1.0
 * @parameter
 * @since 自定义实现Tomcat配置
 *        当和配置application.yml一起都配置时，以配置文件为准。想要代码生效，可把文件重命名为application.yml.bak
 * @return
 */
public class MyTomcatCustomizer {
	@Bean
	public ConfigurableServletWebServerFactory configurableServletWebServerFactory() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addAdditionalTomcatConnectors(createConnector());
		return tomcat;
	}

	private Connector createConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
		connector.setPort(8090);
		// 最大线程数
		protocol.setMaxThreads(2);
		// 最大连接数
		protocol.setMaxConnections(10);
		return connector;
	}
}
