package by.clevertec.util;

import by.clevertec.model.Flower;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Task15Util {
    public static boolean isFlowerInRangeAndPreferred(Flower flower) {
        Set<Character> nameRange = new LinkedHashSet<>();
        for (char c = 'S'; c >= 'C'; c--) {
            nameRange.add(c);
        }
        char firstChar = flower.getCommonName().charAt(0);
        boolean isNameInRange = nameRange.contains(firstChar);
        boolean isShadePreferred = flower.isShadePreferred();
        boolean hasPreferredMaterial = flower.getFlowerVaseMaterial().contains("Glass")
                || flower.getFlowerVaseMaterial().contains("Aluminum")
                || flower.getFlowerVaseMaterial().contains("Steel");
        return isNameInRange && isShadePreferred && hasPreferredMaterial;
    }
}
