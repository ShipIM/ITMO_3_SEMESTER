$(() => {
    let picture = $('#picture');

    let coordinates = new Image(), red_dot = new Image(), green_dot = new Image();
    coordinates.src = 'images/coordinates.svg';
    red_dot.src = 'images/red_dot.svg';
    green_dot.src = 'images/green_dot.svg'
    let image_array = [coordinates, green_dot, red_dot], imagesLoaded = 0;

    $(image_array).on('load', () => {
        imagesLoaded++;

        if (imagesLoaded === image_array.length) {
            allImagesLoaded();
        }
    })

    function allImagesLoaded() {
        let jQueryCanvas = $('<canvas height=' + coordinates.height + ' width=' + coordinates.width
            + ' id="canvas"></canvas>');
        $(document.body).append(jQueryCanvas);

        let canvas = document.getElementById("canvas");
        let context = canvas.getContext('2d');

        let x = $('td.result_x');
        let y = $('td.result_y');
        let r = $('td.result_r');
        let verdict = $('td.result_verdict');

        context.drawImage(coordinates, 0, 0);

        for (let index = 0; index < verdict.length; index++) {
            let size = jQueryCanvas.width() / 2 * 0.935 / r[index].innerHTML;
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

        picture.attr("src", canvas.toDataURL());
        jQueryCanvas.remove();
    }

    picture.on("click", (event) => {
        let error_message = $('#picture + p.error');
        let radius = $('.r:disabled');
        let offset = picture.offset();
        let width = -1 * (picture.width() / 2 - Math.abs(event.pageX - offset.left));
        let height = picture.height() / 2 - Math.abs(event.pageY - offset.top);

        if (radius.length === 1) {
            let size = picture.width() / 2 * 0.935 / radius.val();

            document.getElementById("fields_form:xValue").value = width / size;
            document.getElementById("fields_form:textField").value = height / size;

            document.getElementById("fields_form:formSendButton").click();
        } else {
            error_message.remove();
            picture.after($('<p class="error">Необходимо указать радиус.</p>'));
        }
    })
})