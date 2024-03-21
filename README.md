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
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### Usage

Here's a quick example of how to convert a Gregorian date to a Persian date:

```Java
import org.sohagroup.persianCalendar.DateConverter;

public class Main {
    public static void main(String[] args) {
        String persianDate = DateConverter.gregorianToPersian("2021-03-21");
        System.out.println(persianDate); // Output will be the equivalent Persian date
    }
}
```

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


