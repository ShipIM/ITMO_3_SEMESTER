function redrawPicture(radius) {
    let picture = $('#picture');

    let coordinates = new Image(), red_dot = new Image(), green_dot = new Image();
    coordinates.src = 'images/coordinates.svg';
    red_dot.src = 'images/red_dot.svg';
    green_dot.src = 'images/green_dot.svg'

    let jQueryCanvas = $('<canvas height=' + coordinates.height + ' width=' + coordinates.width
        + ' id="canvas"></canvas>');
    $(document.body).append(jQueryCanvas);

    let canvas = document.getElementById("canvas");
    let context = canvas.getContext('2d');

    let x = $('td.result_x');
    let y = $('td.result_y');
    let verdict = $('td.result_verdict');

    context.drawImage(coordinates, 0, 0);

    for (let index = 0; index < verdict.length; index++) {
        if ((Math.abs(x[index].innerHTML) - radius * (1 / 0.935)) <= 0
            && (Math.abs(y[index].innerHTML) - radius * (1 / 0.935))) {
            let size = jQueryCanvas.width() / 2 * 0.935 / radius;
            switch (verdict[index].innerHTML) {
                case "true": {
                    context.drawImage(green_dot, x[index].innerHTML * size + jQueryCanvas.width() / 2
                        - green_dot.width / 2, (-1 * y[index].innerHTML * size + jQueryCanvas.height() / 2
                        - green_dot.height / 2));
                    break;
                }
                case "false": {
                    context.drawImage(red_dot, x[index].innerHTML * size + jQueryCanvas.width() / 2 - red_dot.width / 2,
                        (-1 * y[index].innerHTML * size + jQueryCanvas.height() / 2 - red_dot.height / 2));
                    break;
                }
            }
        }
    }

    picture.attr("src", canvas.toDataURL());
    jQueryCanvas.remove();
}