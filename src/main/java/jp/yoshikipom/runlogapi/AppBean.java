package jp.yoshikipom.runlogapi;

import java.util.List;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBean {

  private List<Converter<?, ?>> converters;

  public AppBean(List<Converter<?, ?>> converters) {
    this.converters = converters;
  }

  @Bean
  public ModelMapper modelMapper() {
    var modelMapper = new ModelMapper();
    for (Converter<?, ?> converter : converters) {
      modelMapper.addConverter(converter);
    }
    return modelMapper;
  }
}
