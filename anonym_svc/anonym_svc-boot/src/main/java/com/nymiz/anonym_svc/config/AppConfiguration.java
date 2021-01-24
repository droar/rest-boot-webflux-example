package com.nymiz.anonym_svc.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({
  "classpath:/spring/pmed_svc-boot.xml",
  "classpath:/spring/pmed_svc-env.xml",
  "classpath:/spring/pmed_svc-base-web.xml", 
  "classpath:/spring/pmed_svc-base-core.xml", 
  "classpath:/spring/pmed_svc-base-core-persistence-jpa.xml",
  "classpath:/spring/pmed_svc-base-common.xml"
    })
@EntityScan(basePackages = {
    "com.everis.pmed_svc.base.entity", 
    "com.everis.pmed_fwk.base.entity"
    })
public class AppConfiguration {

  /**
   * Si queremos configurar algún bean adicional, podemos hacerlo en este espacio.
   * @return
   */
  
  @Bean
  public String soyUnBeanDummy() {
    return "soyUnBeanDummy";
  }
}
