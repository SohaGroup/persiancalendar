# Persian Calendar Library

The Persian Calendar Library (`persiancalendar`) is a Java library designed to facilitate conversions between the Persian calendar and the Gregorian calendar. It offers utilities for converting dates,
formatting Persian dates, and handling time zones specifically for applications that require dual calendar support.

## Features

- Convert dates from Gregorian to Persian calendar and vice versa.
- Format Persian dates with customizable patterns.
- Support for time zone specifications, particularly for conversions that do not involve time zones.

## Getting Started

### Prerequisites

- Java 8 or higher

### Installation

To use `persianCalendar` in your Maven project, add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>org.sohagroup</groupId>
    <artifactId>persianCalendar</artifactId>
    <version>1.0.1-SNAPSHOT</version>
</dependency>
```

## Usage Examples

After setting up `persianCalendar` in your project, you can use it to perform various date conversions and calculations. Here's how you can get started:

### Configuring the Converter

First, create a `DateConverter` instance with custom date and time formats:

```java
public class Test{
    public static void main(String[]atgs){
        DateConverter converter = new DateConverter(new DateConverterConfig.Builder()
        .withDateFormat("yyyy-MM-dd")
        .withDateTimeFormat("yyyy-MM-dd HH:mm:ss")
        .build());
    }
}
```
### Converting Dates to Persian
Convert the current LocalDate and LocalDateTime to their Persian equivalents:
```
    System.out.println(converter.toPersianLocalDate(LocalDate.now())); // Convert current LocalDate
    System.out.println(converter.toPersianLocalDateTime(LocalDateTime.now())); // Convert current LocalDateTime
```
### Calculating Duration Between Dates
Calculate the duration between two Persian dates, both for LocalDate and LocalDateTime inputs:
```
// Calculate duration in seconds between two LocalDate values
System.out.println(converter.localDateDuration("1400-01-01", "1410-01-01", ChronoUnit.SECONDS));

// Calculate duration in seconds between two LocalDateTime values
System.out.println(converter.localDateTimeDuration("1400-01-01 00:10:34", "1400-02-01 00:00:00", ChronoUnit.SECONDS));
```
This quick start guide should help you integrate and make the most of the persianCalendar library in your projects.
### Building from Source

Clone the repository and navigate to the project directory:

```bash
git clone git@github.com:SohaGroup/persiancalendar.git
cd persiancalendar
```

### Contributing

We welcome contributions! Please read our CONTRIBUTING.md for details on how to submit pull requests, report issues, and contribute to the code.

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

### Acknowledgments

* Thanks to everyone who has contributed to this project!
* Special thanks to the community for the valuable feedback and suggestions.
  arduino


