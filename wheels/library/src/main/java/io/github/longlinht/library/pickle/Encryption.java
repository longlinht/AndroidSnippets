package io.github.longlinht.library.pickle;

/**
 *
 * Created by Tao He on 18-4-27.
 * hetaoof@gmail.com
 *
 */

public interface Encryption {

  /**
   * Encrypt the given string and returns cipher text
   *
   * @param key   is the given key
   * @param value is the plain text
   *
   * @return cipher text as string
   */
  String encrypt(String key, String value) throws Exception;

  /**
   * Decrypt the given cipher text and return plain text
   *
   * @param key   is the given key
   * @param value is the cipher text
   *
   * @return plain text
   */
  String decrypt(String key, String value) throws Exception;

}
