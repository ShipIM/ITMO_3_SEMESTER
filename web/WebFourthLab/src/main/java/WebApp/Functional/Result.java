package WebApp.Functional;

public interface Result {
    ResultVariations result();

    enum ResultVariations {
        TRUE("true"),
        FALSE("false"),
        ERROR("error");

        private final String name;

        ResultVariations(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
}
