package com.xyzcorp.stringtemplates;

import org.junit.jupiter.api.Test;

import static java.util.FormatProcessor.FMT;
import static org.assertj.core.api.Assertions.assertThat;

public class StringTemplatesTest {

    @Test
    void testStringTemplates() {
        String name = "Joan";
        String info = STR."My name is \{name}";
        assertThat(info).isEqualTo("My name is Joan");
    }

    @Test
    void testMultilineTemplateExpressions() {
        String title = "My Web Page";
        String text  = "Hello, world";
        String html = STR."""
        <html>
          <head>
            <title>\{title}</title>
          </head>
          <body>
            <p>\{text}</p>
          </body>
        </html>
        """;
        assertThat(html).isEqualTo("""
        <html>
          <head>
            <title>My Web Page</title>
          </head>
          <body>
            <p>Hello, world</p>
          </body>
        </html>
        """);
    }

    @Test
    void testFormatProcessor() {
        record Rectangle(String name, double width, double height) {
            double area() {
                return width * height;
            }
        }
        Rectangle[] zone = new Rectangle[] {
            new Rectangle("Alfa", 17.8, 31.4),
            new Rectangle("Bravo", 9.6, 12.4),
            new Rectangle("Charlie", 7.1, 11.23),
        };
        String table = FMT."""
            Description     Width    Height     Area
            %-12s\{zone[0].name}  %7.2f\{zone[0].width}  %7.2f\{zone[0].height}     %7.2f\{zone[0].area()}
            %-12s\{zone[1].name}  %7.2f\{zone[1].width}  %7.2f\{zone[1].height}     %7.2f\{zone[1].area()}
            %-12s\{zone[2].name}  %7.2f\{zone[2].width}  %7.2f\{zone[2].height}     %7.2f\{zone[2].area()}
            \{" ".repeat(28)} Total %7.2f\{zone[0].area() + zone[1].area() + zone[2].area()}
            """;

        String expected = """
            Description     Width    Height     Area
            Alfa            17.80    31.40      558.92
            Bravo            9.60    12.40      119.04
            Charlie          7.10    11.23       79.73
                                         Total  757.69
            """;
        assertThat(table).isEqualTo(expected);
    }
}
