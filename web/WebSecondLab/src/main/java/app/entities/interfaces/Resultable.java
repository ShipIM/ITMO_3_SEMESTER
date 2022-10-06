package app.entities.interfaces;

public interface Resultable {
    Result result();

    enum Result {
        TRUE("true"),
        FALSE("false"),
        ERROR("error");

        private final String name;

        Result(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
}
