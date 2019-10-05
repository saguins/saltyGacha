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
    private List<PoolModel> loot;


    public PoolResult(List<PoolModel> loot) {
        this.loot = loot;
        this.total = loot.size();
        this.rare = filterByTier(loot, TierList.R);
        this.superRare = filterByTier(loot, TierList.SR);
        this.extremelyRare = filterByTier(loot, TierList.SSR);
    }


    private Integer filterByTier(List<PoolModel> loot, TierList tierList) {
        return (int) loot.stream().filter(f -> f.getTier().equals(tierList.getTier())).count();
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

    public void getResultListOfItems() {
        Set<String> results = new HashSet<>();
        for (PoolModel item : loot) {
            int quantity = (int) loot.stream().filter(f -> f.equals(item)).count();
            String result = quantity + "x " + item.getParsedTier() + item.getName();
            results.add(result);
        }
        results.forEach(System.out::println);
    }
}

