package pool;

import pool.domain.PoolModel;
import pool.domain.TierList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PoolResult {

    private Integer total;
    private Integer rare;
    private Integer superRare;
    private Integer extremelyRare;
    private List<PoolModel> characters;


    public PoolResult(List<PoolModel> characters) {
        this.characters = characters;
        this.total = characters.size();
        this.rare = filterByTier(characters, TierList.R);
        this.superRare = filterByTier(characters, TierList.SR);
        this.extremelyRare = filterByTier(characters, TierList.SSR);
    }


    private Integer filterByTier(List<PoolModel> characters, TierList tierList) {
        return (int) characters.stream().filter(f -> f.getTier().equals(tierList.getTier())).count();
    }

    private double getPercentage(int partOf, int total) {
        double result = (double) partOf * 100.0 / (double) total;
        return BigDecimal.valueOf(result).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public void getTotal() {
        System.out.println("Your total of items are: " + total);
    }

    public void getResultInQuantity() {
        System.out.println("Rare: " + rare);
        System.out.println("Super Rare: " + superRare);
        System.out.println("Extremely Rare: " + extremelyRare);
    }

    public void getResultInPercentage() {
        System.out.println("Rare: " + getPercentage(rare, total) + "%");
        System.out.println("Super Rare: " + getPercentage(superRare, total) + "%");
        System.out.println("Extremely Rare: " + getPercentage(extremelyRare, total) + "%");
    }

    public void getResultListOfCharacters() {
        Set<String> results = new HashSet<>();
        for (PoolModel item : characters) {
            int quantity = (int) characters.stream().filter(f -> f.equals(item)).count();
            String result = quantity + "x " + item.getParsedTier() + item.getName();
            results.add(result);
        }
        results.forEach(System.out::println);
    }
}

