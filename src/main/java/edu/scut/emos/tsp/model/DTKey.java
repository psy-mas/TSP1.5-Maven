package edu.scut.emos.tsp.model;

public final class DTKey {
    private Position one;
    private Position two;

    public DTKey(Position one, Position two) {
        this.one = one;
        this.two = two;
    }

    public Position getOne() {
        return one;
    }

    public Position getTwo() {
        return two;
    }

    @Override
    public int hashCode() {
        if (this.one == null || this.two == null) {
            return 0;
        }
        return this.one.hashCode() + this.two.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DTKey that = (DTKey) o;

        // 两个地址都不能为空
        if (this.one == null || this.two == null || that.one == null || that.two == null)
            return false;

        return that.one.equals(this.one) && that.two.equals(this.two) ||
                that.one.equals(this.two) && that.two.equals(this.one);
    }

    @Override
    public String toString() {
        return "DTKey{" +
                "one=" + one +
                ", two=" + two +
                '}';
    }
}
