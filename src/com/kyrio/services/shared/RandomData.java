package com.kyrio.services.shared;

import com.kyrio.services.ErrorCode;
import com.kyrio.services.KyrioException;

public class RandomData {
    private static final java.util.Random _random = new java.util.Random();
    public static Provider[] PROVIDERS = new Provider[]
    {
        new Provider("1002", "Time Warner Cable"),
        new Provider("1005", "Comcast"),
        new Provider("1008", "Adelphia"),
        new Provider("1010", "Cox Communications"),
        new Provider("1011", "Charter"),
        new Provider("1012", "Insight Communications"),
        new Provider("1014", "Mediacom"),
        new Provider("1015", "Cablevision"),
        new Provider("1016", "Cable One"),
        new Provider("1017", "Bright House Networks"),
        new Provider("1018", "Suddenlink"),
        new Provider("1024", "Massillon Cable"),
        new Provider("1027", "Clear Picture, Inc"),
        new Provider("1099", "LotsACable"),
        new Provider("1111", "Ridge Cable"),
        new Provider("1236", "Mythical Cable"),
        new Provider("1237", "NewMythical Cable")
    };

    public static int nextInteger(int max) {
        return _random.nextInt(max);
    }

    public static int nextInteger(int min, int max) {
    	if (max - min <= 0)
    		return min;
    	
        return min + _random.nextInt(max - min);
    }

    public static <T> T pick(T[] values) {
        if (values == null || values.length == 0)
            return null;

        return values[nextInteger(values.length)];
    }
    
    public static boolean chance(float chances, float maxChances) {
    	chances = chances >= 0 ? chances : 0;
    	maxChances = maxChances >= 0 ? maxChances : 0;
    	if (chances == 0 && maxChances == 0)
        	return false;
    	
        maxChances = Math.max(maxChances, chances);
        double start = (maxChances - chances) / 2;
        double end = start + chances;
        double hit = _random.nextDouble() * maxChances;
        return hit >= start && hit <= end;
    }

    public static boolean nextBoolean() {
        return _random.nextInt(100) < 50;
    }

    public static Provider nextProvider()
    {
        return pick(PROVIDERS);
    }

    public static KyrioException nextError()
    {
        return chance(1, 2)
            ? new KyrioException(ErrorCode.UNKNOWN, 500, "Test error")
            : new KyrioException(ErrorCode.TIMEOUT, 504, "Test timeout");
    }
}
