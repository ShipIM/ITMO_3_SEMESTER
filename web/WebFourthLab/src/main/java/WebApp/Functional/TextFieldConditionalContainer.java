package WebApp.Functional;

public class TextFieldConditionalContainer implements ConditionalContainer<Float> {
    private final ConditionalContainer<Float> conditionalContainer;
    private final float lowerBound;
    private final float upperBound;

    public TextFieldConditionalContainer(ConditionalContainer<Float> conditionalContainer, float lowerBound, float upperBound) {
        this.conditionalContainer = conditionalContainer;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public Float content() {
        float content = conditionalContainer.content();

        if (content > lowerBound && content < upperBound) {
            return content;
        }

        throw new WrongInputException("Out of correct range");
    }
}
