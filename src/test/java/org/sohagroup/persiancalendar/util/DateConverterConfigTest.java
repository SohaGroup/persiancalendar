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
import static org.sohagroup.persiancalendar.Constants.*;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.*;
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
    String result = dateConverter.toPersianDate(knownInstant);
    logger.debug("current date is : {}", result);
    // Assert
    assertEquals(
        expectedPersianDate, result, "The conversion did not produce the expected Persian date.");
  }
  @Test
  void convertToPersian_ReturnsExpectedDateFormatZoned() {
    // Prepare a known Gregorian date as Instant
    ZonedDateTime knownInstant = ZonedDateTime.of(2023, 3, 21, 0, 0, 0, 0, ZoneId.of(UTC_TIME_ZONE));
    // Expected Persian date for 2023-03-21.
    String expectedPersianDate = "1402/01/01";

    // Act
    String result = dateConverter.toPersianDate(knownInstant);
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
    String result = dateConverter.toPersianDateTimeWithZone(knownInstant);
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
    String result = dateConverter.toPersianDateTime(knownInstant);
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
    String result = dateConverter.toPersianLocalDateTime(localDate);
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
    String result = converter.toPersianLocalDateTime(localDate);
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
    String result = converter.toPersianLocalDateTime(localDate);
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
          dateConverter.toPersianLocalDate(localDate);
        });
  }

  @Test
  void checkDateAsSTring2Persian(){
      String date ="2024-03-20";
      String expectedPersianDate = "1403/01/01";
      String result = dateConverter.toPersianDate(date);
      logger.debug("converted date-time is : {}", result);
      assertEquals(expectedPersianDate, result);
  }
  @Test
  void checkDateTimeAsSTring2Persian(){
      String date ="2024-03-20T00:00:00";
      String expectedPersianDate = "1403/01/01T00:00:00";
      String result = dateConverter.toPersianDateTime(date);
      logger.debug("converted date-time is : {}", result);
      assertEquals(expectedPersianDate, result);
  }
  @Test
  void checkDateTimeAsSTring2PersianAsStartOfDay(){
      String date ="2024-03-20";
      String expectedPersianDate = "1403/01/01T00:00:00";
      String result = dateConverter.toPersianDateTimeStartOfDay(date);
      logger.debug("converted date-time is : {}", result);
      assertEquals(expectedPersianDate, result);
  }

    @Test
    void checkDateAsSTring2Persian_ExceptionCheck(){
        String date ="2024-3-20";
        assertThrows(java.lang.IllegalArgumentException.class, () ->dateConverter.toPersianDate(date));
    }
    @Test
    void checkDateAsSTring2Persian_ExceptionCheck2(){
        String date =null;
        assertThrows(java.lang.IllegalArgumentException.class, () ->dateConverter.toPersianDate(date));
    }

    @Test
    void convertToPersian_LocalDate2PersianDate() {
        // Prepare a known Gregorian date as Instant
        LocalDate localDate = LocalDate.of(2023, 3, 21);
        // Expected Persian date for 2023-03-21.
        String expectedPersianDate = "1402/01/01";
        DateConverter converter = new DateConverter();
        // Act
        String result = converter.toPersianLocalDate(localDate);
        logger.debug("converted date-time is : {}", result);
        // Assert
        assertEquals(
            expectedPersianDate, result, "The conversion did not produce the expected Persian date.");
    }
    @Test
    void convert2Persian_MinusDaysTest() {
        // Prepare a known Gregorian date as String
        String startDate = "1403/01/01";
        // Expected Persian date for 2023-03-21.
        String expectedPersianDate = "1402/12/29";
        // Act
        String result = dateConverter.minusDays(startDate, 1);
        logger.debug("converted date-time is : {}", result);
        // Assert
        assertEquals(
            expectedPersianDate, result, "The conversion did not produce the expected Persian date.");
    }
    @Test
    void convert2Persian_MinusDaysTest_withFormatter() {
        // Prepare a known Gregorian date as String
        String startDate = "1403-01-01";
        // Expected Persian date for 2023-03-21.
        String expectedPersianDate = "1402-12-29";
        DateConverter converter = new DateConverter(new DateConverterConfig.Builder()
            .withDateFormat("yyyy-MM-dd")
            .withDateTimeFormat("yyyy-MM-dd HH:mm:ss")
            .build());
        // Act

        String result = converter.minusDays(startDate, 1);
        logger.debug("converted date-time is : {}", result);
        // Assert
        assertEquals(
            expectedPersianDate, result, "The conversion did not produce the expected Persian date.");
    }
    @Test
    void convert2Persian_PlusDaysTest_withFormatter() {
        // Prepare a known Gregorian date as String
        String startDate = "1403-12-29";
        // Expected Persian date for 2023-03-21.
        String expectedPersianDate = "1403-12-30";
        DateConverter converter = new DateConverter(new DateConverterConfig.Builder()
            .withDateFormat("yyyy-MM-dd")
            .withDateTimeFormat("yyyy-MM-dd HH:mm:ss")
            .build());
        // Act

        String result = converter.plusDays(startDate, 1);
        logger.debug("converted date-time is : {}", result);
        // Assert
        assertEquals(
            expectedPersianDate, result, "The conversion did not produce the expected Persian date.");
    }
    @Test
    void findDurationBetwwenTwoPersianDates() {
        // Prepare a known Gregorian date as String
        String startDate = "1403-12-29";
        // Expected Persian date for 2023-03-21.
        String endDate= "1403-12-30";
        Long expectedPersianDate = 1l;

        DateConverter converter = new DateConverter(new DateConverterConfig.Builder()
            .withDateFormat("yyyy-MM-dd")
            .withDateTimeFormat("yyyy-MM-dd HH:mm:ss")
            .build());
        // Act

        Long numberOfDays = converter.localDateDuration(startDate, endDate, ChronoUnit.DAYS);
        logger.debug("duration is : {}", numberOfDays);
        // Assert
        assertEquals(
            expectedPersianDate, numberOfDays, "The conversion did not produce the expected Persian date.");
    }
    @Test
    void findDurationBetwwenTwoPersianDates_Seconds() {
        // Prepare a known Gregorian date as String
        String startDate = "1403-12-29";
        // Expected Persian date for 2023-03-21.
        String endDate= "1403-12-30";
        Long expectedPersianDate = 86400l;

        DateConverter converter = new DateConverter(new DateConverterConfig.Builder()
            .withDateFormat("yyyy-MM-dd")
            .withDateTimeFormat("yyyy-MM-dd HH:mm:ss")
            .build());
        // Act

        Long numberOfDays = converter.localDateDuration(startDate, endDate, ChronoUnit.SECONDS);
        logger.debug("duration is : {}", numberOfDays);
        // Assert
        assertEquals(
            expectedPersianDate, numberOfDays, "The conversion did not produce the expected Persian date.");
    }
    @Test
    void findDurationBetwwenTwoPersianDatesTimes_Seconds() {
        // Prepare a known Gregorian date as String
        String startDate = "1403-12-29 23:59:59";
        // Expected Persian date for 2023-03-21.
        String endDate= "1403-12-30 00:00:00";
        Long expectedPersianDate = 1l;

        DateConverter converter = new DateConverter(new DateConverterConfig.Builder()
            .withDateFormat("yyyy-MM-dd")
            .withDateTimeFormat("yyyy-MM-dd HH:mm:ss")
            .build());
        // Act

        Long numberOfDays = converter.localDateTimeDuration(startDate, endDate, ChronoUnit.SECONDS);
        logger.debug("duration is : {}", numberOfDays);
        // Assert
        assertEquals(
            expectedPersianDate, numberOfDays, "The conversion did not produce the expected Persian date.");
    }
    @Test
    void findDurationBetwwenTwoPersianDatesTimes_Days() {
        // Prepare a known Gregorian date as String
        String startDate = "1403-12-29 23:59:58";
        // Expected Persian date for 2023-03-21.
        String endDate= "1403-12-30 00:00:00";
        Long expectedPersianDate = 0l;

        DateConverter converter = new DateConverter(new DateConverterConfig.Builder()
            .withDateFormat("yyyy-MM-dd")
            .withDateTimeFormat("yyyy-MM-dd HH:mm:ss")
            .build());
        // Act

        Long numberOfDays = converter.localDateTimeDuration(startDate, endDate, ChronoUnit.DAYS);
        logger.debug("duration is : {}", numberOfDays);
        // Assert
        assertEquals(
            expectedPersianDate, numberOfDays, "The conversion did not produce the expected Persian date.");
    }
    @Test
    void findDurationBetwwenTwoPersianDatesTimes_CENTURIES() {
        // Prepare a known Gregorian date as String
        String startDate = "621-12-29 23:59:58";
        // Expected Persian date for 2023-03-21.
        String endDate= "1403-12-30 00:00:00";
        Long expectedPersianDate = 7l;

        DateConverter converter = new DateConverter(new DateConverterConfig.Builder()
            .withDateFormat("yyyy-MM-dd")
            .withDateTimeFormat("yyyy-MM-dd HH:mm:ss")
            .build());
        // Act
        Long numberOfDays = converter.localDateTimeDuration(startDate, endDate, ChronoUnit.CENTURIES);
        logger.debug("duration is : {}", numberOfDays);
        // Assert
        assertEquals(
            expectedPersianDate, numberOfDays, "The conversion did not produce the expected Persian date.");
    }
    @Test
    void findGregorianDateFromPersianDate() {
        // Prepare a known Gregorian date as String
        String startDate = "1403/01/01";
        // Expected Persian date for 2023-03-21.
        LocalDate expectedGregorianDate = LocalDate.of(2024, 3, 20);
        // Act
        LocalDate result = dateConverter.toGregorianDate(startDate, ZoneId.of(ASIA_TEHRAN_ZONE));
        logger.debug("Calculate date is : {}", result);
        // Assert
        assertEquals(
            expectedGregorianDate, result, "The conversion did not produce the expected Persian date.");
    }
    @Test
    void findGregorianDateTimeFromPersianDate() {
        // Prepare a known Gregorian date as String
        String startDate = "1403/01/01 00:00:00";
        // Expected Persian date for 2023-03-21.
        LocalDateTime expectedGregorianDate = LocalDateTime.of(2024, 3, 20,0,0,0);
        // Act
        LocalDateTime result = dateConverter.toGregorianDateTime(startDate, ZoneId.of(ASIA_TEHRAN_ZONE));
        logger.debug("Calculate date is : {}", result);
        // Assert
        assertEquals(
            expectedGregorianDate, result, "The conversion did not produce the expected Persian date.");
    }
    @Test
    void testUsage() {
        // Prepare a known Gregorian date as String
        String startDate = "1403/01/01 00:00:00";
        // Expected Persian date for 2023-03-21.
        LocalDateTime expectedGregorianDate = LocalDateTime.of(2024, 3, 20,0,0,0);
        // Act
        LocalDateTime result = new DateConverter().toGregorianDateTime(startDate, ZoneId.of(ASIA_TEHRAN_ZONE));
        DateConverter converter = new DateConverter(new DateConverterConfig.Builder()
            .withDateFormat("yyyy-MM-dd")
            .withDateTimeFormat("yyyy-MM-dd HH:mm:ss")
            .build());
        System.out.println(converter.toPersianLocalDate(LocalDate.now()));
        System.out.println(converter.toPersianLocalDateTime(LocalDateTime.now()));
        System.out.println(converter.localDateDuration("1400-01-01","1410-01-01", ChronoUnit.SECONDS));
        System.out.println(converter.localDateTimeDuration("1400-01-01 00:10:34","1400-02-01 00:00:00", ChronoUnit.SECONDS));
        logger.debug("Calculate date is : {}", result);
        // Assert
        assertEquals(
            expectedGregorianDate, result, "The conversion did not produce the expected Persian date.");
    }

    @Test
    public void testConvertGregorianToPersianThreadSafetyLocalDateTime() throws InterruptedException {
        String expectedOutput = "1403/01/01T00:00:00";
        // Expected Persian date for 2023-03-21.
        long time = System.currentTimeMillis();
        LocalDateTime inputDate = LocalDateTime.of(2024, 3, 20,0,0,0);
        int numberOfThreads = 2000;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        List<String> outputs = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
      executorService.submit(
          () -> {
            try {
              // Call the method from multiple threads
                String output = dateConverter.toPersianLocalDateTime(inputDate);
                logger.info("Converted date-time is : {}", output);
                  outputs.add(output);
            } finally {
              latch.countDown();
            }
          });
        }

        latch.await(); // Wait for all threads to finish

        // Check that all outputs are as expected
        outputs.forEach(output -> assertEquals(expectedOutput, output));

        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        time = System.currentTimeMillis() - time;
        System.out.println("time = " + time + " ms");
    }
    @Test
    public void testConvertGregorianToPersianThreadSafety() throws InterruptedException {
        final LocalDate inputDate = LocalDate.of(2023, 3, 21);
        final String expectedOutput = "1402/01/01"; // Expected output, assuming this is the correct conversion
        long time = System.currentTimeMillis();
        int numberOfThreads = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        List<String> outputs = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
        executorService.submit(
          () -> {
            try {
              // Call the method from multiple threads
                String output = dateConverter.toPersianDate(inputDate);
                logger.info("Converted date-time is : {}", output);
                  outputs.add(output);
            } finally {
              latch.countDown();
            }
          });
        }

        latch.await(); // Wait for all threads to finish
        time = System.currentTimeMillis() - time;

        // Check that all outputs are as expected
        outputs.forEach(output -> assertEquals(expectedOutput, output));
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        System.out.println("time = " + time + " ms");
    }
    @Test
    public void testConvertGregorianToPersianThreadSafety_3rd() throws InterruptedException {
        final LocalDate inputDate = LocalDate.of(2023, 3, 21);
        final String expectedOutput = "1402/01/01"; // Expected output, assuming this is the correct conversion
        Collection<Future<String>> futures = Collections.synchronizedCollection(new ArrayList<>(10000));
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            long time = System.currentTimeMillis();
            for(int i = 0; i < 100_000; i++) {
                Future<String> f =  executor.submit(()-> dateConverter.toPersianDate(inputDate));
                futures.add(f);
            }
            Thread.sleep(1000l);
            long sum = 0;
            for (Future<String> future : futures) {
                String output = future.get();
                logger.info("The output threadid {} is : {} ",Thread.currentThread().threadId(), output);
                assertEquals(expectedOutput, output);
            }
            time = System.currentTimeMillis() - time;
            System.out.println("sum = " + sum + "; time = " + time + " ms");
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
