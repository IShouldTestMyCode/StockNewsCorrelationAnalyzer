<div style="text-align:center">
![Line count](https://img.shields.io/tokei/lines/github/TheCreeperAPI/StockNewsCorrelationAnalyzer)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/e496f91f63f545baabfcc259f322875e)](https://www.codacy.com/gh/TheCreeperAPI/StockNewsCorrelationAnalyzer/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=TheCreeperAPI/StockNewsCorrelationAnalyzer&amp;utm_campaign=Badge_Grade)
</div>
# StockNewsCorrelationAnalyzer
This software can use Google's sentiment analysis to analyze a dataset in /ds/ and be able to output a .csv file with the date, sentiment, and magnitude of the "description" column from the dataset.
## Origional purpose
This software is a part of one of my science projects, which is to see if the sentiment of the news can correspond to the S&P 500 index. 
## Limitations
This software is platform dependant, so it will only work on mac and linux. Sorry!
## Installation and execution
1. Clone the repo
2. Get your google credentials and API
  - Enable the API at https://console.cloud.google.com/apis/library/language.googleapis.com. You will need to add billing.
  - Follow the instructions at https://cloud.google.com/iam/docs/creating-managing-service-accounts and create a service account. Download the json file, and save it.
3. Install Java 8

```bash
#macos, with homebrew
brew tap adoptopenjdk/openjdk
brew install --cask adoptopenjdk8
#linux, with apt-get
sudo apt-get install openjdk-8-jdk
```

4. Install Maven
```bash
# macos, with homebrew
brew install java --version 8
brew install maven
# linux, with apt-get
sudo apt-get install maven
```
5. Set up config.json.  Set DatasetEntries to the number of entires in the dataset, set DatasetLocation to the absolute path of the dataset, and set the OutputLocation ot the absolute path of the location where you want to save the output to.

6. Set the environment variable of your Google credentials.
```bash
export GOOGLE_APPLICATION_CREDENTIALS=<PathToTheJsonFileYouDownloadedInStep2>
```

7. Execute 
```bash
cd <InsertDirOfTheClone>
mvn clean compile exec:java -f pom.xml
```
This will install all of the dependencies and execute the software. On average, this usually takes a couple hours on Google's servers.
## Results
Once the system exits with code 0, go to /ds/out, and you should find your CSV.  Re-lable the CSV, and it should be ready to go.

## Licence
This project is released under a slightly-modified MIT licence.
