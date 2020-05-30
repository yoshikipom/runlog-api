package jp.yoshikipom.runlogapi;

import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

public class TestUtil {

  public String readFile(final String path) {
    try {
      return IOUtils
          .toString(new ClassPathResource(path).getInputStream(), Charset.defaultCharset());
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}
