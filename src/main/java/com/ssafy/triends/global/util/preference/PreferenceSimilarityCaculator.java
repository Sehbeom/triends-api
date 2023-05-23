package com.ssafy.triends.global.util.preference;

import com.ssafy.triends.domain.user.model.UserPreferenceDto;
import com.ssafy.triends.global.model.ContentType;

import java.util.*;

public class PreferenceSimilarityCaculator {
    private static final int NUMBER_OF_CONTENT_TYPES = ContentType.values().length;

    public static double calculateTwoUsers(UserPreferenceDto user1, UserPreferenceDto user2) {
        int user1PreferenceBits = user1.getPreferenceBitFormat();
        if (user1PreferenceBits == -1) {
            user1PreferenceBits = convertToBitFormat(user1);
            user1.setPreferenceBitFormat(user1PreferenceBits);
        }

        int user2PreferenceBits = user2.getPreferenceBitFormat();
        if (user2PreferenceBits == -1) {
            user2PreferenceBits = convertToBitFormat(user2);
            user2.setPreferenceBitFormat(user2PreferenceBits);
        }

        int correspondBits = checkCorrespondBits(user1PreferenceBits, user2PreferenceBits);
        return calculateSimilarity(correspondBits);
    }

    public static Map<UserPreferenceDto, Double> calculateOneWithOthers(UserPreferenceDto oneUser, List<UserPreferenceDto> others) {
        Map<UserPreferenceDto, Double> similarities = new HashMap<>();

        for (UserPreferenceDto other : others) {
            similarities.put(other, calculateTwoUsers(oneUser, other));
        }

        return sortBySimilarity(similarities);
    }

    private static Map<UserPreferenceDto, Double> sortBySimilarity(Map<UserPreferenceDto, Double> similarities) {
        Map<UserPreferenceDto, Double> sorted = new TreeMap<>(new ValueComparator(similarities));
        sorted.putAll(similarities);

        return sorted;
    }

    static class ValueComparator implements Comparator<UserPreferenceDto> {
        private Map<UserPreferenceDto, Double> map;

        ValueComparator(Map<UserPreferenceDto, Double> map) {
            this.map = map;
        }
        @Override
        public int compare(UserPreferenceDto o1, UserPreferenceDto o2) {
            Double value1 = this.map.get(o1);
            Double value2 = this.map.get(o2);

            if (value1 > value2)
                return -1;
            else if (value1 < value2)
                return 1;
            else {
                return o1.getUserId() - o2.getUserId();
            }
        }
    }

    private static int convertToBitFormat(UserPreferenceDto userPreferenceDto) {
        List<ContentType> contentTypes = new ArrayList<>(Arrays.asList(ContentType.values()));
        int bitFormat = 0;

        for (ContentType contentType : userPreferenceDto.getContentTypes()) {
            bitFormat += Math.pow(2, contentTypes.indexOf(contentType));
        }

        return bitFormat;
    }

    private static int checkCorrespondBits(int bits1, int bits2) {
        return ((int)Math.pow(2, NUMBER_OF_CONTENT_TYPES) - 1) & (~(bits1 ^ bits2));
    }

    private static double calculateSimilarity(int correspondBits) {
        int numOfOnes = countOne(correspondBits);
        return (double) numOfOnes / NUMBER_OF_CONTENT_TYPES;
    }

    private static int countOne(int bits) {
        int count = 0;
        while (bits != 0) {
            bits &= bits - 1;
            count += 1;
        }
        return count;
    }
}
