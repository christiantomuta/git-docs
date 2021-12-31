package com.tomuta.swenoop.countstringfromweb;

import java.io.IOException;
import java.util.List;

public interface SecureWebContentReaderInterface {
    String getWebsiteDomain();
    int getPort();

    String getHttpsContent();
    List<String> getContentStringsFromRegex(String pattern, int groupNr);
    List<String> getContentStringsFromRegex(String pattern) ;
}
