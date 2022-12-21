package WebApp.Functional;

public class NumberConditionalContainer implements ConditionalContainer<Float> {
    private final String content;

    public NumberConditionalContainer(String content) {
        this.content = content;
    }

    @Override
    public Float content() {
        try {
            return Float.parseFloat(content);
        } catch (NumberFormatException | NullPointerException e) {
            throw new WrongInputException("Not a number");
        }
    }
}
