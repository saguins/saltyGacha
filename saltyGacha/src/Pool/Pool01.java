
package Pool;

public class Pool01 {

    private Pool01UnitName unitName;
    private int maxNum = 99; //amount of charactors in this pool
    private int[] arrayId = new int[maxNum + 1]; //array of charactors
    private int[] arrayRate = new int[100]; //array of rarity probability

    public Pool01() {
        this.unitName = new Pool01UnitName();
        for (int i = 1; i < arrayId.length; i++) { //create id
            arrayId[i] = i;
        }
        for (int i = 0; i < arrayRate.length; i++) { //create %rate
            if (i <= 1) {
                arrayRate[i] = 3; //SSR 2%
            } else if (i <= 27) {
                arrayRate[i] = 2; //SR 25%
            } else {
                arrayRate[i] = 1; //R 73%
            }
        }
    }

    public String getTeir(int id) {
        if (id <= 2) { //1-32
            return "SSR";
        } else if (id <= 66) { //33-66
            return "SR";
        } else { //67-99
            return "R";
        }
    }
    
    public String getName(int id) {
        return this.unitName.getUnitName(id);
    }

    public int pickOne() {
        int num = arrayRate[(int) (Math.random() * arrayRate.length)];
        //get number of rarity probability [SSR, SR, R]
        //then find charactor from a pool of this teir
        switch (num) {
            case 3: //get SSR
                return arrayId[(int) (Math.random() * ((32 - 1) + 1)) + 1];
            case 2: //get SR
                return arrayId[(int) (Math.random() * ((66 - 33) + 1)) + 33];
            default: //get R
                return arrayId[(int) (Math.random() * ((99 - 67) + 1)) + 67];
        }
    }

    public int pickOneGuarantee() {
        int num = arrayRate[(int) (Math.random() * (arrayRate.length - 73))];
        //in case to get SR or better
        switch (num) {
            case 3: //get SSR
                return arrayId[(int) (Math.random() * ((32 - 1) + 1)) + 1];
            default://get SR
                return arrayId[(int) (Math.random() * ((66 - 33) + 1)) + 33];
        }
    }

    public int[] pickTen() {
        int[] result = new int[10];
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                result[i] = pickOneGuarantee();
            } else {
                result[i] = pickOne();
            }
        }
        return result;
    }

    @Override
    public String toString() {
        int[] result = pickTen();
        return "Result:\n"
                + "[" + getTeir(result[0]) + "] " + getName(result[0]) + "\n"
                + "[" + getTeir(result[1]) + "] " + getName(result[1]) + "\n"
                + "[" + getTeir(result[2]) + "] " + getName(result[2]) + "\n"
                + "[" + getTeir(result[3]) + "] " + getName(result[3]) + "\n"
                + "[" + getTeir(result[4]) + "] " + getName(result[4]) + "\n"
                + "[" + getTeir(result[5]) + "] " + getName(result[5]) + "\n"
                + "[" + getTeir(result[6]) + "] " + getName(result[6]) + "\n"
                + "[" + getTeir(result[7]) + "] " + getName(result[7]) + "\n"
                + "[" + getTeir(result[8]) + "] " + getName(result[8]) + "\n"
                + "[" + getTeir(result[9]) + "] " + getName(result[9]);
    }

}
