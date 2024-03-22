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
package org.sohagroup.persiancalendar.util;

import java.util.Objects;

/**
 * @author ehsan
 * @version 1
 * @since 1.0.0
 */
public class DateConverterConfig {
  private final String dateFormat;
  private final String datetimeFormat;
  private final String findDateFormat;

  private DateConverterConfig(Builder builder) {
    this.dateFormat = builder.dateFormat;
    this.datetimeFormat = builder.datetimeFormat;
    this.findDateFormat = builder.findDateFormat;
  }

  // Builder class
  public static class Builder {
    private String dateFormat = "yyyy/MM/dd";
    private String datetimeFormat = "yyyy/MM/dd'T'HH:mm:ss";
    private String findDateFormat = "yyyyMMdd";

    public Builder withDateFormat(String format) {
      this.dateFormat = format;
      return this;
    }

    public Builder withDateTimeFormat(String format) {
      this.datetimeFormat = format;
      return this;
    }

    public Builder withFindDateFormat(String format) {
      this.findDateFormat = format;
      return this;
    }

    public DateConverterConfig build() {
      return new DateConverterConfig(this);
    }
  }

  // Getters
  public String getDateFormat() {
    return dateFormat;
  }

  public String getDatetimeFormat() {
    return datetimeFormat;
  }

  public String getFindDateFormat() {
    return findDateFormat;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DateConverterConfig that = (DateConverterConfig) o;
    return Objects.equals(dateFormat, that.dateFormat)
        && Objects.equals(datetimeFormat, that.datetimeFormat);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dateFormat, datetimeFormat);
  }

  @Override
  public String toString() {
    return "DateConverterConfig{"
        + "dateFormat='"
        + dateFormat
        + '\''
        + ", datetimeFormat='"
        + datetimeFormat
        + '\''
        + ", findDateFormat='"
        + findDateFormat
        + '\''
        + '}';
  }
}
