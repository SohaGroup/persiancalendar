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

import static org.junit.jupiter.api.Assertions.*;
import static org.sohagroup.persiancalendar.Constants.UTC_TIME_ZONE;

import java.time.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @service-package: org.sohagroup.persiancalendar.util
 * @author: ehsan, moradi@sohagroup.org
 * @added-date: 3/21/24 : 6:43 PM
 * @since: 1.0.0
 */
class DateConverterConfigTest {
  private static final Logger logger = LoggerFactory.getLogger(DateConverterConfig.class);
  private DateConverter dateConverter;

  @BeforeEach
  public void init() {
    dateConverter =
        new DateConverter(
            new DateConverterConfig.Builder()
                .withDateFormat("yyyy/MM/dd")
                .withDateTimeFormat("yyyy/MM/dd'T'HH:mm:ss")
                .build());
  }

  @Test
  void getCurrentDateTime_ReturnsNonNullAndNonEmpty() {
    // Act

    String result = dateConverter.getCurrentDateTime();
    logger.debug("current date is : {}", result);
    // Assert
    assertNotNull(result, "The current date should not be null");
    assertFalse(result.isEmpty(), "The current date should not be empty");
  }

  @Test
  void getCurrentDate_ReturnsNonNullAndNonEmpty() {
    // Act
    String result = dateConverter.getCurrentDate();
    logger.debug("current date is : {}", result);
    // Assert
    assertNotNull(result, "The current date should not be null");
    assertFalse(result.isEmpty(), "The current date should not be empty");
  }

  @Test
  void convertToPersian_ReturnsExpectedDateFormat() {
    // Prepare a known Gregorian date as Instant
    ZonedDateTime zdt = ZonedDateTime.of(2023, 3, 21, 0, 0, 0, 0, ZoneId.of(UTC_TIME_ZONE));
    Instant knownInstant = zdt.toInstant();

    // Expected Persian date for 2023-03-21.
    String expectedPersianDate = "1402/01/01";

    // Act
    String result = dateConverter.convertToPersianDate(knownInstant);
    logger.debug("current date is : {}", result);
    // Assert
    assertEquals(
        expectedPersianDate, result, "The conversion did not produce the expected Persian date.");
  }

  @Test
  void convertToPersian_ReturnsExpectedDateTimeFormat() {
    // Prepare a known Gregorian date as Instant
    ZonedDateTime zdt = ZonedDateTime.of(2023, 3, 21, 0, 0, 0, 0, ZoneId.of(UTC_TIME_ZONE));
    Instant knownInstant = zdt.toInstant();

    // Expected Persian date for 2023-03-21.
    String expectedPersianDate = "1402/01/01T03:30:00";

    // Act
    String result = dateConverter.convertToPersianDateTime(knownInstant);
    logger.debug("converted date-time is : {}", result);
    // Assert
    assertEquals(
        expectedPersianDate, result, "The conversion did not produce the expected Persian date.");
  }

  @Test
  void convertToPersian_ReturnsExpectedDateTimeFormatNoZone() {
    // Prepare a known Gregorian date as Instant
    ZonedDateTime zdt = ZonedDateTime.of(2023, 3, 21, 0, 0, 0, 0, ZoneId.of(UTC_TIME_ZONE));
    Instant knownInstant = zdt.toInstant();

    // Expected Persian date for 2023-03-21.
    String expectedPersianDate = "1402/01/01T03:30:00";

    // Act
    String result = dateConverter.toPersianDateTimeNoZone(knownInstant);
    logger.debug("converted date-time is : {}", result);
    // Assert
    assertEquals(
        expectedPersianDate, result, "The conversion did not produce the expected Persian date.");
  }

  @Test
  void convertToPersian_ReturnsExpectedLocalDateTimeFormat() {
    // Prepare a known Gregorian date as Instant
    LocalDate localDate = LocalDate.of(2023, 3, 21);
    LocalTime localTime = LocalTime.of(0, 0, 0);
    LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
    // Expected Persian date for 2023-03-21.
    String expectedPersianDate = "1402/01/01T00:00:00";

    // Act
    String result = dateConverter.toPersianDateTimeNoZone(localDateTime);
    logger.debug("converted date-time is : {}", result);
    // Assert
    assertEquals(
        expectedPersianDate, result, "The conversion did not produce the expected Persian date.");
  }

  @Test
  void convertToPersian_ReturnsExpectedLocalDateFormat() {
    // Prepare a known Gregorian date as Instant
    LocalDate localDate = LocalDate.of(2023, 3, 21);
    // Expected Persian date for 2023-03-21.
    String expectedPersianDate = "1402/01/01T00:00:00";

    // Act
    String result = dateConverter.toPersianDateTimeNoZone(localDate);
    logger.debug("converted date-time is : {}", result);
    // Assert
    assertEquals(
        expectedPersianDate, result, "The conversion did not produce the expected Persian date.");
  }

  @Test
  void convertToPersian_ReturnsExpectedLocalDateWithFormat() {
    // Prepare a known Gregorian date as Instant
    LocalDate localDate = LocalDate.of(2023, 3, 21);
    // Expected Persian date for 2023-03-21.
    String expectedPersianDate = "1402-01-01 00:00:00";
    DateConverter converter =
        new DateConverter(
            new DateConverterConfig.Builder()
                .withDateFormat("yyyy-MM-dd")
                .withDateTimeFormat("yyyy-MM-dd HH:mm:ss")
                .build());
    // Act
    String result = converter.toPersianDateTimeNoZone(localDate);
    logger.debug("converted date-time is : {}", result);
    // Assert
    assertEquals(
        expectedPersianDate, result, "The conversion did not produce the expected Persian date.");
  }

  @Test
  void convertToPersian_ReturnsExpectedLocalDateWithFormat_NoBuilder() {
    // Prepare a known Gregorian date as Instant
    LocalDate localDate = LocalDate.of(2023, 3, 21);
    // Expected Persian date for 2023-03-21.
    String expectedPersianDate = "1402/01/01T00:00:00";
    DateConverter converter = new DateConverter();
    // Act
    String result = converter.toPersianDateTimeNoZone(localDate);
    logger.debug("converted date-time is : {}", result);
    // Assert
    assertEquals(
        expectedPersianDate, result, "The conversion did not produce the expected Persian date.");
  }

  @Test
  void convertToPersian_ReturnsExpectedLocalDateWithFormat_NullChecking() {
    // check null pointer
    LocalDate localDate = null;
    // Assert
    assertThrows(
        NullPointerException.class,
        () -> {
          dateConverter.toPersianDateTimeNoZone(localDate);
        });
  }
}
