package app.entities.classes;

import app.entities.interfaces.Resultable;

import java.text.DecimalFormat;

public class Timer {
    private final Resultable resultable;

    public Timer(Resultable resultable) {
        this.resultable = resultable;
    }

    public String time() {
        long startTime = System.nanoTime();
        resultable.result();
        long endTime = System.nanoTime();

        DecimalFormat df = new DecimalFormat("#.#");
        df.setMaximumFractionDigits(8);

        return df.format((endTime - startTime) / 1000000f).replace(",", ".");
    }
}
