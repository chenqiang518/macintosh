package com.vernon.poppy.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

    // getLogger("com.vernon") 必须与  <logger name="com.vernon" level="DEBUG"/> 中的name保持一致
    public static Logger log = LoggerFactory.getLogger("com.vernon");

}
