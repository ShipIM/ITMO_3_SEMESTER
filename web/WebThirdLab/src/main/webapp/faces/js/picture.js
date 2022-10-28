let picture;
let coordinates, red_dot, green_dot;
let image_array, imagesLoaded;
let x, y, r, verdict;

$(() => {
    coordinates = new Image();
    red_dot = new Image();
    green_dot = new Image();

    coordinates.src = 'images/coordinates.svg';
    red_dot.src = 'images/red_dot.svg';
    green_dot.src = 'images/green_dot.svg';

    image_array = [coordinates, green_dot, red_dot];
    imagesLoaded = 0;

    picture = $('#picture')

    $(image_array).on('load', () => {
            imagesLoaded++;

            if (imagesLoaded === image_array.length) {
                loadPicture();
            }
        }
    )

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

function loadPicture() {
    x = $('.result_x');
    y = $('.result_y');
    r = $('.result_r');
    verdict = $('.result_verdict');

    let canvas = createCanvas();
    let context = canvas.getContext('2d');
    context.drawImage(coordinates, 0, 0);

    for (let index = 0; index < verdict.length; index++) {
        addPictureOnCanvas(r[index].innerHTML, x[index].innerHTML,
            y[index].innerHTML, verdict[index].innerHTML, canvas);
    }

    picture.attr("src", canvas.toDataURL());
    canvas.remove();
}

function redrawPicture(radius) {
    $('#picture + p.error').remove();

    x = $('.result_x');
    y = $('.result_y');
    verdict = $('.result_verdict');

    let canvas = createCanvas();
    let context = canvas.getContext('2d');
    context.drawImage(coordinates, 0, 0);

    for (let index = 0; index < verdict.length; index++) {
        if ((Math.abs(x[index].innerHTML) - radius * (1 / 0.935)) <= 0
            && (Math.abs(y[index].innerHTML) - radius * (1 / 0.935))) {
            addPictureOnCanvas(radius, x[index].innerHTML, y[index].innerHTML, verdict[index].innerHTML, canvas);
        }
    }

    picture.attr("src", canvas.toDataURL());
    canvas.remove();
}

function createCanvas() {
    let jQueryCanvas = $('<canvas height=' + coordinates.height + ' width=' + coordinates.width
        + ' id="canvas"></canvas>');
    $(document.body).append(jQueryCanvas);

    return document.getElementById("canvas");
}

function addPictureOnCanvas(radius, x, y, verdict, canvas) {
    let jQueryCanvas = $(canvas);
    let context = canvas.getContext('2d');
    let size = jQueryCanvas.width() / 2 * 0.935 / radius;
    switch (verdict) {
        case "true": {
            context.drawImage(green_dot, x * size + jQueryCanvas.width() / 2
                - green_dot.width / 2, (-1 * y * size + jQueryCanvas.height() / 2
                - green_dot.height / 2));
            break;
        }
        case "false": {
            context.drawImage(red_dot, x * size + jQueryCanvas.width() / 2 - red_dot.width / 2,
                (-1 * y * size + jQueryCanvas.height() / 2 - red_dot.height / 2));
            break;
        }
    }
}