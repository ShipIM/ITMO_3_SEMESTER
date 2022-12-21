package WebApp.Functional;

import java.text.DecimalFormat;

public class Timer {
    private final Result result;

    public Timer(Result result) {
        this.result = result;
    }

    public String time() {
        long startTime = System.nanoTime();
        result.result();
        long endTime = System.nanoTime();

        DecimalFormat df = new DecimalFormat("#.#");
        df.setMaximumFractionDigits(4);

        return df.format((endTime - startTime) / 1000000f).replace(",", ".");
    }
}
