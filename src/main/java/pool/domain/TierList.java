package pool.domain;

public enum TierList {
    SSR("SSR", 2.0, null),
    SR("SR", 25.0, SSR),
    R("R", 73.0, SR);

    private String tier;
    private Double percentage;
    private TierList next;

    public String getTier() {
        return this.tier;
    }

    public Double getPercentage() {
        return this.percentage;
    }

    TierList(String tier, Double percentage, TierList next) {
        this.tier = tier;
        this.percentage = percentage;
        this.next = next;
    }

    private Integer getPercentageNumber(Integer size) {
        double divisor = size;
        double result = this.getPercentage() / divisor * 100.0;
        return (int) result;
    }

    public Integer getPercentageNumberRecursively(Integer size) {
        TierList tier = this;
        double percentage = this.getPercentage();

        while (tier.next != null) {
            tier = tier.next;
            percentage += tier.getPercentageNumber(size);
        }

        return (int) percentage;
    }

    public static TierList fromBaseNumber(Integer baseNumber, Integer size) {
        for (TierList tierList : TierList.values()) {
            if (tierList.getPercentageNumberRecursively(size) >= baseNumber) {
                return tierList;
            }
        }
        throw new RuntimeException("Rarity Not Found in this Tier List " + baseNumber);
    }

}
