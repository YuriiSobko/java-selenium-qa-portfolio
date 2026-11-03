# Java Selenium UI Testing — Code Portfolio Sample

**Java · Selenium WebDriver · JUnit 4 · Maven · Page Object Model**

This repository demonstrates historical UI automation design patterns: page objects, reusable element actions, search scenarios, and smoke/regression test organization. It is a **code sample, not a runnable end-to-end demo**. Original environment-specific element identifiers were replaced with **illustrative placeholder selectors**, and all credentials and private URLs were removed.

## Example test scenarios
- Open a search view
- Search by item code
- Search by item name
- Combine search filters
- Open a matching detail view

## Project structure
- `src/test/java/org/mainToolBarTests/MaterialSearchTests.java` — five example UI tests
- `src/main/java/org/pages/search/MaterialSearch.java` — Page Object
- `src/main/java/org/actionWithElements/` — reusable UI actions
- `src/test/java/org/suites/` — smoke / regression configuration

## Configuration
The sample reads `QA_BASE_URL`, `QA_USERNAME`, and `QA_PASSWORD` from environment variables. **Do not use real credentials in publicly shared examples.** The sample cannot perform a useful browser test without a compatible application, valid authorization, and adjusted locators/test data.

## What this portfolio proves
Code organization and familiarity with Java, Selenium, Page Objects, JUnit, and functional web test design. It **does not** claim an independently verified green test run, working demo application, or CI integration.

## Publication note
Derived from the author's historical source. The removal of identifiers and credentials does not establish ownership of source code or authorization to disclose it. Publish only if you own the code or have the necessary rights.
