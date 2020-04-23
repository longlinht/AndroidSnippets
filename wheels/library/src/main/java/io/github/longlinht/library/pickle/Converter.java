
package io.github.longlinht.library.pickle;

import java.lang.reflect.Type;

/**
 *
 * Created by Tao He on 18-4-27.
 * hetaoof@gmail.com
 *
 */

public interface Converter {

  /**
   * Encodes the value
   *
   * @param value will be encoded
   *
   * @return the encoded string
   */
  <T> String toJson(T value);

  /**
   * Decodes
   *
   * @param json is the encoded data
   *
   * @return the plain value
   *
   */
  <T> T fromJson(String json, Type clazz);

}
