package WebService;

class PairOfCurrencyValueAndDate implements Comparable<PairOfCurrencyValueAndDate>,Cloneable {

    PairOfCurrencyValueAndDate() {
    }

    PairOfCurrencyValueAndDate(String date, double value) {
        this.date = date;
        this.value = value;
    }

    String date;
    double value;

    @Override
    public int compareTo(PairOfCurrencyValueAndDate pair) {
        return this.value > pair.value ? 1 : this.value < pair.value ? -1 : 0;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
