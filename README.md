# Sentiment Analyzer

## Description

The **Sentiment Analyzer** is a Java-based tool that evaluates the sentiment of textual reviews by identifying key features and associating them with positive or negative opinions. This tool is designed to help users understand the sentiment expressed in a given review by analyzing specific features mentioned within the text.

## Features

- **Feature Detection**: Scans the review for predefined features and determines their presence.
- **Sentiment Analysis**: Associates features with either positive or negative sentiment based on predefined opinion words and patterns.
- **Customizable**: Allows for the configuration of feature sets and opinion words to tailor the sentiment analysis to specific needs.

## How It Works

1. **Input**: The tool takes in a textual review as input.
2. **Feature Matching**: It scans the review for predefined features, which are categorized in the `featureSet`.
3. **Sentiment Detection**: For each detected feature, the tool determines the sentiment by checking for predefined patterns (e.g., "was [positive/negative word]" or "[positive/negative word] [feature]").
4. **Output**: Returns an array indicating the sentiment associated with each feature.

## Usage

1. **Setup**: Ensure that your Java environment is set up.
2. **Configuration**: Modify the `featureSet`, `posOpinionWords`, and `negOpinionWords` arrays to match the features and opinion words relevant to your use case.
3. **Execution**: Run the `SentimentAnalyzer` class with a review string as input.

## Example

```java
String[] posOpinionWords = {"good", "great", "excellent", "positive", "satisfactory"};
String[] negOpinionWords = {"bad", "poor", "terrible", "negative", "unsatisfactory"};

String[][] featureSet = {
    {"battery", "battery life"},
    {"screen", "display"},
    {"performance", "speed"}
};

String review = "The battery life is great, but the screen quality is poor.";

int[] sentiments = SentimentAnalyzer.detectProsAndCons(review, featureSet, posOpinionWords, negOpinionWords);

System.out.println(Arrays.toString(sentiments)); // Output: [1, -1, 0]
```

In this example, the tool identifies that the sentiment towards "battery life" is positive, "screen" is negative, and no sentiment is detected for "performance."

## Contributing

If you'd like to contribute to this project, please feel free to fork the repository and submit a pull request.

## License

This project is licensed under the MIT License.
