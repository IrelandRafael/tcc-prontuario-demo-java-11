package com.unigranead.tcc.pages;

import java.util.Base64;

public class ImageUtil {

  public String getImgData(byte[] byteData) {
    return byteData == null ? "" : Base64.getMimeEncoder().encodeToString(byteData);
  }

}
