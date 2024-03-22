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
}
