# IMDb UI Test 
Automated UI test for IMDb using Java 17, Selenide, TestNG and Allure. 

## Run tests 
Headless mode
```bash
./gradlew clean test -Dheadless=true
```

Headed mode
```bash
./gradlew clean test -Dheadless=false
```

## GitHub Actions
- Push or pull request to the `main` branch  
- Manual trigger from the [Actions tab](https://github.com/vingeflow/imdb-selenide-tests/actions/workflows/ci.yml) by clicking **“Run workflow”**

## Allure reports
If you downloaded the report artifact from GitHub Actions:
1. Go to the downloaded folder
   ```bash
   cd ~/path-to-your-folder/allureReport
   ```
2. Run a local server
   ```bash
   python3 -m http.server 8080
   ```
3. Open the report in your browser `http://localhost:8080`

Or after running tests locally:
 ```bash
allure serve build/allure-results
```

