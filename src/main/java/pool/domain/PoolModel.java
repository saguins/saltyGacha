package pool.domain;

import java.util.Objects;

public class PoolModel {
    private Integer id;
    private String name;
    private String tier;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTier() {
        return tier;
    }

    public String getParsedTier() {
        return "[" + tier + "]";
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tier, name);
    }

    @Override
    public boolean equals(Object o) {
        if (!getClass().equals(o.getClass())) return false;
        PoolModel that = (PoolModel) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(tier, that.tier) &&
                Objects.equals(id, that.id);
    }
}
