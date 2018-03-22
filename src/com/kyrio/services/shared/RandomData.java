package com.kyrio.services.shared;

import com.kyrio.services.ErrorCode;
import com.kyrio.services.KyrioException;

/**
 * Random data generator used to simulate server responses.
 */
public class RandomData {
    private static final java.util.Random _random = new java.util.Random();
    
    /**
     * All currently connected cable providers
     */
    public static Provider[] PROVIDERS = new Provider[]
    {
        new Provider("2000", "Local Test Cable Provider A"),
        new Provider("2001", "Local Test Cable Provider B"),
        new Provider("2002", "Local Test Cable Provider C"),
        new Provider("2003", "Local Test Cable Provider D"),
        new Provider("2004", "Local Test Cable Provider E"),
        new Provider("2005", "Local Test Cable Provider F"),
        new Provider("2006", "Local Test Cable Provider G"),
        new Provider("2007", "Local Test Cable Provider H"),
        new Provider("2008", "Local Test Cable Provider I"),
        new Provider("2009", "Local Test Cable Provider J"),
        new Provider("2010", "Local Test Cable Provider K"),
        new Provider("2011", "Local Test Cable Provider L"),
        new Provider("2012", "Local Test Cable Provider M"),
        new Provider("2013", "Local Test Cable Provider N"),
        new Provider("2014", "Local Test Cable Provider O"),
        new Provider("2015", "Local Test Cable Provider P"),
        new Provider("2016", "Local Test Cable Provider Q")
    };

    /**
     * Generates random integer less or equal to max value.
     * @param max A maximum for random values.
     * @return A random integer value.
     */
    public static int nextInteger(int max) {
        return _random.nextInt(max);
    }

    /**
     * Generates random integer within specified range.
     * @param min A minimum for random values.
     * @param max A maximum for random values.
     * @return A random integer value.
     */
    public static int nextInteger(int min, int max) {
    	if (max - min <= 0)
    		return min;
    	
        return min + _random.nextInt(max - min);
    }

    /**
     * Picks a random element from values array.
     * @param values Array with possible values.
     * @return A random value.
     */
    public static <T> T pick(T[] values) {
        if (values == null || values.length == 0)
            return null;

        return values[nextInteger(values.length)];
    }
    
    /**
     * Determines a random chance from maximum chances.
     * @param chances Number of chances to test.
     * @param maxChances Maximum number of chances.
     * @return <code>true</code> is chance happend or <code>false</code> otherwise.
     */
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

    /**
     * Generates random boolean value.
     * @return A random boolean value.
     */
    public static boolean nextBoolean() {
        return _random.nextInt(100) < 50;
    }

    /**
     * Picks a random cable provider from the list of registered providers.
     * @return A random cable provider.
     */
    public static Provider nextProvider()
    {
        return pick(PROVIDERS);
    }

    /**
     * Generates a random error returned by Kyrio services.
     * @return A random error.
     */
    public static KyrioException nextError()
    {
        return chance(1, 2)
            ? new KyrioException(ErrorCode.UNKNOWN, 500, "Test error")
            : new KyrioException(ErrorCode.TIMEOUT, 504, "Test timeout");
    }
}
