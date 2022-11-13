package Containers;

public abstract class TextBean<T> {
    private T value;

    public TextBean() {

    }

    public TextBean(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
