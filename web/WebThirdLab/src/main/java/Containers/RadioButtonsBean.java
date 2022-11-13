package Containers;

import java.util.Map;

public abstract class RadioButtonsBean<T> {
    private Map<String, T> values;
    private T chosenValue;

    public RadioButtonsBean() {

    }

    public RadioButtonsBean(Map<String, T> values) {
        this.values = values;
    }

    public Map<String, T> getValues() {
        return values;
    }

    public T getChosenValue() {
        return chosenValue;
    }

    public void setChosenValue(T chosenValue) {
        this.chosenValue = chosenValue;
    }
}
