package edu.scut.emos.tsp.model;

/**
 * @author emos
 *
 */
public class DTKey {
    private Position one;
    private Position two;

    public DTKey(Position one, Position two) {
        this.one = one;
        this.two = two;
    }

    public Position getOne() {
        return one;
    }

    public void setOne(Position one) {
        this.one = one;
    }

    public Position getTwo() {
        return two;
    }

    public void setTwo(Position two) {
        this.two = two;
    }

    /**
     * 返回将DTKey的两个位置元素转置
     *
     * @return 转置后的新的DTKey
     */
    public DTKey revert() {
        return new DTKey(this.two, this.one);
    }

    @Override
    public int hashCode() {
        return this.one.hashCode() + this.two.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DTKey that = (DTKey) o;

        return that.one.equals(this.one) && that.two.equals(this.two);
    }
}
