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
import java.text.ParseException;
import java.time.*;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**This calss convert  Persian to GregorianCalendar and vice-versa.
 * It's mainly base on IBM ICU4j and Java Internal java.time
 * Main goal of this is to ease of use of ICU4j and cover its problem in thread-safety.
 * @author : moradi, moradi@sohagroup.org
 * @since : 1.0.0
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
  public synchronized String getCurrentDateTime() {
    return datetimeFormat.format(java.util.Date.from(Instant.now()));
  }

  /**
   * Get Current Date of Persian Calendar in Default Format as defined via dateFormat
   *
   * @return persian date in format yyyy/MM/dd for example datetime 2024-03-21 converted to
   *     1403/01/02
   */
  public synchronized  String getCurrentDate() {
    return datetimeFormat.format(java.util.Date.from(Instant.now()));
  }

  /**
   * This method convert the input Date and Time to Persian Calendar with default format yyyy/MM/dd
   *
   * @param dateTimeNoZone get Instant as input, with no-zone, calculated at UTC zone time
   * @return Persian date in format yyyy/MM/dd
   * @exception NullPointerException if dateTimeNoZone is null
   */
  public synchronized String toPersianDate(Instant dateTimeNoZone) {
    Objects.requireNonNull(dateTimeNoZone, "dateTimeNoZone param must not be null");
    return dateFormat.format(java.util.Date.from(dateTimeNoZone));
  }

    /**
     * This method convert the input Date and Time to Persian Calendar with default format yyyy/MM/dd
     *
     * @param dateTimeNoZone input in {@link ZonedDateTime} in order to cnvert to persian
     * @return Persian date in format yyyy/MM/dd
     */
    public synchronized String toPersianDate(ZonedDateTime dateTimeNoZone) {
        Objects.requireNonNull(dateTimeNoZone, "dateTimeNoZone param must not be null");
        return toPersianDate(dateTimeNoZone.toInstant());
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
  public synchronized String toPersianDateTime(@NotNull Instant dateTimeWithZone) {
    Objects.requireNonNull(dateTimeWithZone, "dateTimeWithZone param must not be null");
    return datetimeFormat.format(java.util.Date.from(dateTimeWithZone));
  }

    /**
     * Converts the provided {@link Instant} representing a date and time to a string formatted according
     * to the Persian calendar system, taking into account a specific time zone.
     *
     * This method accepts an {@link Instant} which represents a point on the time-line not tied
     * to any specific time zone. It then converts this {@code Instant} into a {@link ZonedDateTime}
     * in the Asia/Tehran time zone, and formats it as a string according to the Persian calendar system.
     *
     * The output format of the date-time string is determined by the {@code datetimeFormat} which should
     * be initialized to format dates in the Persian calendar system. Ensure that {@code datetimeFormat}
     * is properly set up for Persian date formatting prior to invoking this method.
     *
     * @param dateTimeWithZone The {@link Instant} to be converted, representing a specific moment
     *                         in time in the UTC time zone.
     * @return A {@code String} representing the date and time of the provided {@code Instant}
     *         in the Persian calendar system and Asia/Tehran time zone. The format of the returned
     *         string is dependent on the configuration of {@code datetimeFormat}.
     * @throws NullPointerException if {@code dateTimeWithZone} is {@code null}.
     */
  public synchronized String toPersianDateTimeWithZone(@NotNull Instant dateTimeWithZone) {
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
  public synchronized String  toPersianDateTimeNoZone(@NotNull LocalDateTime localDateTime) {
    Objects.requireNonNull(localDateTime, "localDateTime param must not be null");
    return datetimeFormat.format(
        java.util.Date.from(Instant.from(localDateTime.atZone(ZoneId.of(ASIA_TEHRAN_ZONE)))));
  }
  /**
   * Convert the LocalDateTime to its Persian Equivalent, for example 2023-03-21T00:00:00 or 2023-03-21 to
   * 1402/01/01T00:00:00 Attention to Time, when using localdatetime, there is no localization
   * needed so if you Enter 00:00 you got 00:00 in Tehran Time
   *
   * @param localDate in {@link LocalDateTime} the input as localDateTime
   * @return Persian Date of input
   * @since 1.0.0
   */
  public synchronized String toPersianDateTime(@NotNull LocalDate localDate) {
    Objects.requireNonNull(localDate, "localDateTime param must not be null");
    return toPersianDateTimeNoZone(localDate.atStartOfDay());
  }
  public synchronized String toPersianDate(@NotNull LocalDate localDate) {
    Objects.requireNonNull(localDate, "localDateTime param must not be null");
    return dateFormat.format(java.util.Date.from(localDate.atStartOfDay(ZoneId.of(ASIA_TEHRAN_ZONE)).toInstant()));
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
  public synchronized String toPersianLocalDate(@NotNull LocalDate localDate) {
    Objects.requireNonNull(localDate, "localDate param must not be null");
    return dateFormat.format(
        java.util.Date.from(Instant.from(localDate.atStartOfDay(ZoneId.of(ASIA_TEHRAN_ZONE)))));
  }
  public synchronized String toPersianLocalDateTime(@NotNull LocalDate localDate) {
    Objects.requireNonNull(localDate, "localDate param must not be null");
    return datetimeFormat.format(
        java.util.Date.from(Instant.from(localDate.atStartOfDay(ZoneId.of(ASIA_TEHRAN_ZONE)))));
  }
  public synchronized String toPersianLocalDateTime(@NotNull LocalDateTime localDateTime) {
    Objects.requireNonNull(localDateTime, "localDate param must not be null");
    return datetimeFormat.format(
        java.util.Date.from(Instant.from(localDateTime.atZone(ZoneId.of(ASIA_TEHRAN_ZONE)))));
  }

    /**
     * This method convert the Date, get  from input as {@link String} and return persian equivalent
     * The default format for date is {@link DateTimeFormatter}.ISO_LOCAL_DATE
     * @param gregorianDate date as String like 2023-11-01 (ISO_LOCAL_DATE)
     * @return Persian equivalent of date as format of yyyy/MM/dd
     */
  public synchronized String toPersianDate(String gregorianDate) {
      if (gregorianDate == null || gregorianDate.trim().isEmpty()) {
          throw new IllegalArgumentException("Input date string cannot be null or empty.");
      }
      LocalDate date;
      try {
          date = LocalDate.parse(gregorianDate, DateTimeFormatter.ISO_LOCAL_DATE);
          logger.info("Converted  string date to : {}", date);
      } catch (DateTimeParseException e) {
          logger.error("Error parsing the input date string: " + gregorianDate,e);
          throw new IllegalArgumentException("Error parsing the input date string: " + gregorianDate, e);
      }
      return toPersianDate(date);
  }
  /**
     * This method convert the Date, get  from input as {@link String} and return persian equivalent
     * The default format for date is {@link DateTimeFormatter}.ISO_LOCAL_DATE_TIME
     * @param gregorianDateTime date as String like 2023-11-01 (ISO_LOCAL_DATE_TIME)
     * @return Persian equivalent of date as format of yyyy/MM/dd
     */
  public synchronized String toPersianDateTime(String gregorianDateTime) {
      if (gregorianDateTime == null || gregorianDateTime.trim().isEmpty()) {
          throw new IllegalArgumentException("Input date string cannot be null or empty.");
      }
      LocalDate date;
      try {
          date = LocalDate.parse(gregorianDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
          logger.info("Converted  string date to : {}", date);
      } catch (DateTimeParseException e) {
          logger.error("Error parsing the input date string: " + gregorianDateTime,e);
          throw new IllegalArgumentException("Error parsing the input date string: " + gregorianDateTime, e);
      }
      return toPersianLocalDateTime(date);
  }

    /**
     * This method convert the Date, get  from input as {@link String} and return persian equivalent
     * The default format for date is {@link DateTimeFormatter}.ISO_LOCAL_DATE
     * @param gregorianDateTime date as String like 2023-11-01 (ISO_LOCAL_DATE)
     * @return Persian equivalent of date as format of yyyy/MM/ddTHH:mm:ss or any format specified in datetimeFormat
     */
  public synchronized String toPersianDateTimeStartOfDay(String gregorianDateTime) {
      if (gregorianDateTime == null || gregorianDateTime.trim().isEmpty()) {
          throw new IllegalArgumentException("Input date string cannot be null or empty.");
      }
      LocalDate date;
      try {
          date = LocalDate.parse(gregorianDateTime, DateTimeFormatter.ISO_DATE);
          logger.info("Converted  string date to : {}", date);
      } catch (DateTimeParseException e) {
          logger.error("Error parsing the input date string: " + gregorianDateTime,e);
          throw new IllegalArgumentException("Error parsing the input date string: " + gregorianDateTime, e);
      }
      return toPersianLocalDateTime(date);
  }

    /**
     * This method calculate number of Days before param persianDate
     * @param persianDate as reference to minus days before it
     * @param days as {@link Long} to minus start of current day
     * @return Persian date formatted by dateFormat calculated persianDate-days
     */
  public synchronized String minusDays(String persianDate, long days){

      ZonedDateTime zonedDateTime = null;
      try {
          zonedDateTime = ZonedDateTime.ofInstant(dateFormat.parse(persianDate).toInstant(), ZoneId.systemDefault());
          Instant minus = zonedDateTime.minusDays(days).toInstant();
          return dateFormat.format(java.util.Date.from(minus));
      } catch (ParseException e) {
          throw new IllegalArgumentException("Date as specified is not parsable "+persianDate);
      }
  }
  /**
     * This method calculate number of Days before param persianDate
     * @param persianDate as reference to minus days before it
     * @param days as {@link Long} to plus start of current day
     * @return Persian date formatted by dateFormat calculated persianDate+days
     */
  public synchronized String plusDays(String persianDate, long days){

      ZonedDateTime zonedDateTime = null;
      try {
          zonedDateTime = ZonedDateTime.ofInstant(dateFormat.parse(persianDate).toInstant(), ZoneId.systemDefault());
          Instant minus = zonedDateTime.plusDays(days).toInstant();
          return dateFormat.format(java.util.Date.from(minus));
      } catch (ParseException e) {
          throw new IllegalArgumentException("Date as specified is not parsable "+persianDate);
      }
  }
  /**
     * This method calculate number of Days before param persianDate
     * @param startPersianDate as reference to check the date from with yyyy/MM/dd format
     * @param endPersianDate as reference to check the date to with yyyy/MM/dd format
     * @param unit as Unit to fetch Difference which defined in {@link ChronoUnit}
     * @return number of Days between startPersianDate and endPersianDate
     */
  public synchronized Long localDateDuration(String startPersianDate, String endPersianDate, ChronoUnit unit){
      try {
          ZonedDateTime startZonedDateTime = ZonedDateTime.ofInstant(dateFormat.parse(startPersianDate).toInstant(), ZoneId.systemDefault());
          ZonedDateTime endZoneDateTime = ZonedDateTime.ofInstant(dateFormat.parse(endPersianDate).toInstant(), ZoneId.systemDefault());
          return unit.between(startZonedDateTime, endZoneDateTime);
      } catch (ParseException e) {
          throw new IllegalArgumentException("Date as specified is not parsable either "+startPersianDate+" or "+endPersianDate);
      }
  }
  /**
     * This method calculate number of Days before param persianDate
     * @param startPersianDate as reference to check the date from with yyyy/MM/dd HH:mm:ss format
     * @param endPersianDate as reference to check the date to with yyyy/MM/dd HH:mm:ss format
     * @param unit as Unit to fetch Difference which defined in {@link ChronoUnit}
     * @return number of Days between startPersianDate and endPersianDate
     */
  public synchronized Long localDateTimeDuration(String startPersianDate, String endPersianDate, ChronoUnit unit){
      try {
          ZonedDateTime startZonedDateTime = ZonedDateTime.ofInstant(datetimeFormat.parse(startPersianDate).toInstant(), ZoneId.systemDefault());
          ZonedDateTime endZoneDateTime = ZonedDateTime.ofInstant(datetimeFormat.parse(endPersianDate).toInstant(), ZoneId.systemDefault());
          return unit.between(startZonedDateTime, endZoneDateTime);
      } catch (ParseException e) {
          throw new IllegalArgumentException("Date as specified is not parsable either "+startPersianDate+" or "+endPersianDate);
      }
  }


    /**
     * Converts a Persian date string to its Gregorian counterpart as a {@link LocalDate}.
     * The input string is expected to be in the Persian calendar format "yyyy/MM/dd".
     *
     * This method parses the provided Persian date string, taking into account the specified time zone
     * for any time zone related adjustments, and converts it to a {@link LocalDate} in the Gregorian calendar system.
     * Note that the conversion accuracy is dependent on the implementation details of the {@code dateFormat} used
     * for parsing and might require customization to handle the Persian calendar correctly.
     *
     * @param persianDate The Persian date string to be converted, in the format "yyyy/MM/dd".
     * @param zoneId The {@link ZoneId} representing the time zone to consider during the conversion process.
     *               This is necessary to accurately determine the date, especially around time zone shifts and
     *               daylight saving time changes.
     * @return A {@link LocalDate} representing the equivalent Gregorian date.
     * @throws NullPointerException If the {@code zoneId} parameter is {@code null}.
     * @throws IllegalArgumentException If the {@code persianDate} cannot be parsed into a valid date according
     *                                  to the expected format, indicating that the string is malformed or does not
     *                                  accurately represent a valid Persian date.
     */
    public synchronized LocalDate toGregorianDate(String persianDate, ZoneId zoneId) {
        Objects.requireNonNull(zoneId, "zoneId param must not be null");
        try {
            ZonedDateTime date = ZonedDateTime.ofInstant(dateFormat.parse(persianDate).toInstant(), zoneId);
            return date.toLocalDate();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date as specified is not parsable "+persianDate);
        }
    }
    /**
     * Converts a Persian date and time string to its Gregorian counterpart as a {@link LocalDateTime}.
     * The input string is expected to be in the format "yyyy/MM/dd HH:mm:ss".
     *
     * This method parses the provided Persian date and time string, taking into account the specified time zone,
     * and converts it to a {@link LocalDateTime} in the Gregorian calendar system.
     *
     * @param persianDate The Persian date and time string to be converted. Must be in the format "yyyy/MM/dd HH:mm:ss".
     * @param zoneId The {@link ZoneId} to be considered for this conversion. This is necessary to accurately
     *               account for time zone differences during the conversion process.
     * @return A {@link LocalDateTime} representing the Gregorian counterpart of the given Persian date and time.
     * @throws NullPointerException If the {@code persianDate} parameter is {@code null}.
     * @throws IllegalArgumentException If the {@code persianDate} cannot be parsed according to the expected format,
     *                                  indicating the string is either malformed or does not represent a valid Persian date.
     */
    public synchronized LocalDateTime toGregorianDateTime(String persianDate, ZoneId zoneId) {
        Objects.requireNonNull(persianDate, "dateTimeNoZone param must not be null");
        try {
            ZonedDateTime date = ZonedDateTime.ofInstant(datetimeFormat.parse(persianDate).toInstant(), zoneId);
            return date.toLocalDateTime();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date as specified is not parsable "+persianDate);
        }
    }






}
