
package pool;

import pool.domain.PoolModel;
import pool.domain.TierList;

import java.util.ArrayList;
import java.util.List;

public class Pool {

    private List<String> names = new PoolUnitName().getUnitNames();
    private List<PoolModel> models = new ArrayList<>();

    public Pool() {
        for (String name : names) {
            PoolModel model = new PoolModel();
            model.setName(name);
            model.setId(names.indexOf(name) + 1);

            TierList tier = TierList.fromBaseNumber(model.getId(), names.size());
            model.setTier(tier.getTier());
            models.add(model);
        }
    }

    private PoolModel pickOne() {
        int rnd = (int) (Math.random() * names.size());
        return models.get(rnd);
    }

    private PoolModel pickOneGuarantee() {
        int rare = TierList.SR.getPercentageNumberRecursively(names.size());
        int rnd = (int) (Math.random() * rare);
        return models.get(rnd);
    }

    public List<PoolModel> pickTen() {
        List<PoolModel> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                result.add(pickOneGuarantee());
            } else {
                result.add(pickOne());
            }
        }
        return result;
    }
}
