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

import static org.sohagroup.persiancalendar.Constants.*;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;
import jakarta.validation.constraints.NotNull;
import java.time.*;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @service-package: org.sohagroup.persiancalendar.util
 * @service-name:
 * @author: moradi, moradi@sohagroup.org
 * @added-date: 3/21/24 : 8:19 PM
 * @since: 1.0.0
 */
public class DateConverter {
  private final SimpleDateFormat dateFormat;
  private final SimpleDateFormat datetimeFormat;
  private final SimpleDateFormat findDateFormat;
  private static final Logger logger = LoggerFactory.getLogger(DateConverter.class);

  public DateConverter(DateConverterConfig config) {
    logger.trace("Init the DateConverter {}", config);
    ULocale persianLocale = new ULocale(PERSIAN_DATE_FORMAT);
    this.dateFormat = new SimpleDateFormat(config.getDateFormat(), ULocale.forLocale(Locale.US));
    this.datetimeFormat =
        new SimpleDateFormat(config.getDatetimeFormat(), ULocale.forLocale(Locale.US));
    this.findDateFormat =
        new SimpleDateFormat(config.getFindDateFormat(), ULocale.forLocale(Locale.US));
    Calendar persianCalendar = Calendar.getInstance(persianLocale);
    this.dateFormat.setCalendar(persianCalendar);
    this.datetimeFormat.setCalendar(persianCalendar);
    this.findDateFormat.setCalendar(persianCalendar);
  }

  public DateConverter() {
    ULocale persianLocale = new ULocale(PERSIAN_DATE_FORMAT);
    this.dateFormat =
        new SimpleDateFormat(DEFAULT_PERSIAN_DATE_FORMAT, ULocale.forLocale(Locale.US));
    this.datetimeFormat =
        new SimpleDateFormat(DEFAULT_PERSIAN_DATE_TIME_FORMAT, ULocale.forLocale(Locale.US));
    this.findDateFormat =
        new SimpleDateFormat(DEFAULT_PERSIAN_DATE_TIME_FORMAT, ULocale.forLocale(Locale.US));
    Calendar persianCalendar = Calendar.getInstance(persianLocale);
    this.dateFormat.setCalendar(persianCalendar);
    this.datetimeFormat.setCalendar(persianCalendar);
    this.findDateFormat.setCalendar(persianCalendar);
  }

  /**
   * Get Current Date of Persian Calendar in Default Format as defined via dateFormat
   *
   * @return persian date in format yyyy/MM/dd'T'HH:mm:ss for example datetime 2024-03-21 19:58:58
   *     converted to 1403/01/02T18:58:58
   */
  public String getCurrentDateTime() {
    return datetimeFormat.format(java.util.Date.from(Instant.now()));
  }

  /**
   * Get Current Date of Persian Calendar in Default Format as defined via dateFormat
   *
   * @return persian date in format yyyy/MM/dd for example datetime 2024-03-21 converted to
   *     1403/01/02
   */
  public String getCurrentDate() {
    return datetimeFormat.format(java.util.Date.from(Instant.now()));
  }

  /**
   * This method convert the input Date and Time to Persian Calendar with default format yyyy/MM/dd
   *
   * @param dateTimeNoZone
   * @return Persian date in format yyyy/MM/dd
   */
  public String convertToPersianDate(Instant dateTimeNoZone) {
    Objects.requireNonNull(dateTimeNoZone, "dateTimeNoZone param must not be null");
    return dateFormat.format(java.util.Date.from(dateTimeNoZone));
  }

  /**
   * This method convert the input Date and Time to Persian Calendar with default format
   * yyyy/MM/dd'T'HH:mm:ss
   *
   * @param dateTimeWithZone the input value base on {@link Instant} attention the Instant have zone
   *     of UTC then if you are not in the Zone, for example in Tehran then Equivalent of 00:00:00
   *     (midnight) is (03:30:00) in Tehran.
   * @return Persian date in format yyyy/MM/dd'T'HH:mm:ss
   */
  public String convertToPersianDateTime(@NotNull Instant dateTimeWithZone) {
    Objects.requireNonNull(dateTimeWithZone, "dateTimeWithZone param must not be null");
    return datetimeFormat.format(java.util.Date.from(dateTimeWithZone));
  }

  /**
   * @param dateTimeWithZone
   * @return
   */
  public String toPersianDateTimeNoZone(@NotNull Instant dateTimeWithZone) {
    Objects.requireNonNull(dateTimeWithZone, "dateTimeWithZone param must not be null");
    ZonedDateTime zonedDateTime =
        ZonedDateTime.ofInstant(dateTimeWithZone, ZoneId.of(ASIA_TEHRAN_ZONE));
    return datetimeFormat.format(java.util.Date.from(zonedDateTime.toInstant()));
  }

  /**
   * Convert the LocalDateTime to its Persian Equivalent, for example 2023/03/21T00:00:00 to
   * 1402/01/01T00:00:00 Attention to Time, when using localdatetime, there is no localization
   * needed so if you Enter 00:00 you got 00:00 in Tehran Time
   *
   * @param localDateTime in {@link LocalDateTime} the input as localDateTime
   * @return Persian Date of input
   * @since 1.0.0
   */
  public String toPersianDateTimeNoZone(@NotNull LocalDateTime localDateTime) {
    Objects.requireNonNull(localDateTime, "localDateTime param must not be null");
    return datetimeFormat.format(
        java.util.Date.from(Instant.from(localDateTime.atZone(ZoneId.of(ASIA_TEHRAN_ZONE)))));
  }

  /**
   * Convert the LocalDateTime to its Persian Equivalent, for example 2023/03/21T00:00:00 to
   * 1402/01/01T00:00:00 Attention to Time, when using localdatetime, there is no localization
   * needed so if you Enter 00:00 you got 00:00 in Tehran Time
   *
   * @param localDate in {@link LocalDateTime} the input as localDateTime
   * @return Persian Date of input
   * @since 1.0.0
   */
  public String toPersianDateTimeNoZone(@NotNull LocalDate localDate) {
    Objects.requireNonNull(localDate, "localDate param must not be null");
    return datetimeFormat.format(
        java.util.Date.from(Instant.from(localDate.atStartOfDay(ZoneId.of(ASIA_TEHRAN_ZONE)))));
  }
}
