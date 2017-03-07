package services

import java.math.BigInteger

object encryption {

  def encrypt(s:String) = {
    val enc = java.security.MessageDigest.getInstance("MD5")
    val locle = s.getBytes("UTF-8")
    enc.update(locle, 0, locle.length)
    new BigInteger(1, enc.digest()).toString(16)
  }

}
