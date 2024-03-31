/*
 * Copyright (c) 2024 Ehsan Moradi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sohagroup.persiancalendar;

/**
 * @author : ehsan, moradi@sohagroup.org
 * @since : 1.0.0
 */
public class Constants {

  private Constants() {
    throw new IllegalStateException("Utility Class, cannot be instantiate");
  }

  public static final String PERSIAN_DATE_FORMAT = "fa_IR@calendar=persian";
  public static final String UTC_TIME_ZONE = "UTC";
  public static final String ASIA_TEHRAN_ZONE = "Asia/Tehran";
  public static final String DEFAULT_PERSIAN_DATE_TIME_FORMAT = "yyyy/MM/dd'T'HH:mm:ss";
  public static final String DEFAULT_PERSIAN_DATE_FORMAT = "yyyy/MM/dd";


  //Message
  public static final String LOCALDATE_MUST_NOT_BE_NULL_MESSAGE="localDate param must not be null";
  public static final String LOCALDATE_TIME_MUST_NOT_BE_NULL_MESSAGE="localDateTime param must not be null";
  public static final String INPUT_DATE_NUT_NULL_MESSAGE="Input date string cannot be null or empty.";
  public static final String ERROR_IN_PARSING_INPUT_DATE_MESSAGE="Error parsing the input date string: ";
  public static final String DATE_TIME_ZONE_MUST_NOT_BE_NULL_MESSAGE="dateTimeNoZone param must not be null";

}
