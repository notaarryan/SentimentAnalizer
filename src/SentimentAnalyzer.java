import java.util.Arrays;

public class SentimentAnalyzer {
    // Tip: labeled continue can be used when iterating featureSet + convert review to lower-case
    public static int[] detectProsAndCons(String review, String[][] featureSet,
                                          String[] posOpinionWords,
                                          String[] negOpinionWords) {
        int[] featureOpinions = new int[featureSet.length];
        review = review.toLowerCase();
        nextFeature: for (int i = 0; i < featureSet.length; i++) {
            String[] features = featureSet[i];
            for (String feature : features) {
                if (review.contains(feature)) {
                    int opinion = getOpinionOnFeature(review,
                            feature, posOpinionWords, negOpinionWords);
                    if (opinion != 0) {
                        featureOpinions[i] = opinion;
                        continue nextFeature;
                    }
                }
            }
        }
        return featureOpinions;
    }

    // First invoke checkForWasPhrasePattern and
    // if it cannot find an opinion only then invoke checkForOpinionFirstPattern
    private static int getOpinionOnFeature(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {

        int opinion = checkForWasPhrasePattern(review,feature,posOpinionWords,negOpinionWords);

        if (opinion == 0) {
            opinion = checkForOpinionFirstPattern(review,feature,posOpinionWords,negOpinionWords);
        }

        return opinion;

    }

    // Tip: Look at String API doc. Methods like indexOf, length, substring(beginIndex), startsWith can come into play
    // Return 1 if positive opinion found, -1 for negative opinion, 0 for no opinion
    // You can first look for positive opinion. If not found, only then you can look for negative opinion
    private static int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        String pattern = feature + " was ";
        String[] sentences = review.split("\\.");

        for (String sentence : sentences) {
            int index = sentence.indexOf(pattern);
            if (index>=0) {
                String sub = review.substring(index+pattern.length()).trim();

                for (String pos : posOpinionWords) {
                    if (sub.startsWith(pos)) {
                        return 1;
                    }
                }

                for (String neg : negOpinionWords) {
                    if (sub.startsWith(neg)) {
                        return -1;
                    }
                }
            }
        }

        return 0;
    }

    // You can first look for positive opinion. If not found, only then you can look for negative opinion
    private static int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords,
                                                   String[] negOpinionWords) {
        // Extract sentences as feature might appear multiple times.
        // split() takes a regular expression and "." is a special character
        // for regular expression. So, escape it to make it work!!
        String[] sentences = review.split("\\.");


        for (String sentence : sentences) {
            int index = sentence.indexOf(feature);
            if (index>=0) {
                for (String pos : posOpinionWords) {
                    if (sentence.contains(pos)) {
                        return 1;
                    }
                }

                for (String neg : negOpinionWords) {
                    if (sentence.contains(neg)) {
                        return -1;
                    }
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        String review = "I chose two items from the new menu, the shrimp scampi and the shrimp and chicken carbonara, both with my favourite soup and some warm breadsticks. The soup was amazing, as always.";

        //String review = "Sorry OG, but you just lost some loyal customers. Horrible service, no smile or greeting just attitude. The breadsticks were stale and burnt, appetizer was cold and the food came out before the salad.";

        String[][] featureSet = {
                { "ambiance", "ambience", "atmosphere", "decor" },
                { "dessert", "ice cream", "desert" },
                { "food" },
                { "soup" },
                { "service", "management", "waiter", "waitress", "bartender", "staff", "server" } };
        String[] posOpinionWords = { "good", "fantastic", "friendly", "great", "excellent", "amazing", "awesome",
                "delicious" };
        String[] negOpinionWords = { "slow", "bad", "horrible", "awful", "unprofessional", "poor" };

        int[] featureOpinions = detectProsAndCons(review, featureSet, posOpinionWords, negOpinionWords);
        System.out.println("Opinions on Features: " + Arrays.toString(featureOpinions));
    }
}