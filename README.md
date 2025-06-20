
# MixQA roadmap

A simple Java project using Gradle that contains logic implementation and basic tests — developed as a technical task or demonstration.

## Project Structure

This project is divided into two main testing areas:

### • UI Testing
UI tests are performed on the main page of [DuckDuckGo](https://duckduckgo.com).

### • Functional Testing
Functional testing is performed using the [DuckDuckGo](https://duckduckgo.com) search engine instead of Google, due to frequent CAPTCHA challenges from Google that interfere with automated testing.

## Stack Used

- Java (global version 17.0.8)
- Selenide (version 7.5.1)
- TestNG (7.10.2)
- Allure (version 2.12.0)

## Running Tests

You can run the automated tests using Gradle:
```bash
./gradlew test
```
Alternatively, you can run TestNG suites individually.

By default, the following TestNG suites are configured:

• functional_test.xml  
• ui_test.xml  
• test_age.xml

You can edit or comment them in the build.gradle file under the test {} block to control which test suites run.
