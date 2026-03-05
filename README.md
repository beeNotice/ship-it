# Ship It?

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=beeNotice_ship-it&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=beeNotice_ship-it)

An interactive demo app that puts you in the shoes of a developer making real-world decisions about code quality, security, and engineering practices.

Each screen presents a realistic scenario with two choices — and shows you the consequences. Should you ship it?

## Tech Stack

- Java 21 / Spring Boot 4
- Thymeleaf
- Maven

## Run Locally

```bash
./mvnw spring-boot:run
```

Then open [http://localhost:8080](http://localhost:8080).

## How It Works

The app cycles through a set of scenarios loaded from `src/main/resources/data/sanity-checks.json`. Each scenario has a context, a question, and two decisions with their respective consequences. The cycle is infinite — click through as many times as you like.

To add or edit scenarios, just update the JSON file.

## Sonar Integration

### Analysis

SonarCloud's automatic analysis does not run tests, so code coverage won't be reported. To enable it, disable automatic analysis and set up a CI pipeline that runs `mvn verify sonar:sonar`.

The `pom.xml` already includes JaCoCo and the required Sonar properties. See the [SonarCloud CI guide](https://docs.sonarsource.com/sonarcloud/advanced-setup/ci-based-analysis/sonarscanner-for-maven/) for setup instructions.

### MCP

```powershell
$env:SONAR_TOKEN = "SONAR_TOKEN"
$env:SONAR_ORG = "beeNotice"

claude mcp add sonarqube `
    --env SONARQUBE_TOKEN=$env:SONAR_TOKEN `
    --env SONARQUBE_ORG=$env:SONAR_ORG `
    -- `
    docker run -i --rm --init --pull=always -e SONARQUBE_TOKEN -e SONARQUBE_ORG mcp/sonarqube
```

Reference : https://docs.sonarsource.com/sonarqube-mcp-server/quickstart-guide
