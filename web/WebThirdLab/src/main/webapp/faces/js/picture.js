let picture;
let coordinates, red_dot, green_dot;
let image_array, imagesLoaded;

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
        error_message.remove();
        let radius = $('.r:disabled');

        let type = $('[name = fields_form\\:typeOptions]:checked');
        let star = $('[name = fields_form\\:starOptions]:checked');
        let triangle = $('[name = fields_form\\:triangleOptions]:checked');

        let offset = picture.offset();
        let width = -1 * (picture.width() / 2 - Math.abs(event.pageX - offset.left));
        let height = picture.height() / 2 - Math.abs(event.pageY - offset.top);

        if (radius.length === 1 && type.length === 1
            && ((type.val() === "STAR" && star.length === 1) || (type.val() === "TRIANGLE" && triangle.length === 1))) {
            let size = picture.width() / 2 * 0.935 / radius.val();

            document.getElementById("hidden_fields_form:xValue").value = width / size;
            document.getElementById("hidden_fields_form:yValue").value = height / size;
            document.getElementById("hidden_fields_form:typeValue").value = type.val();
            document.getElementById("hidden_fields_form:starValue").value =
                (star.val() !== undefined ? star.val() : 0);
            document.getElementById("hidden_fields_form:triangleValue").value =
                (triangle.val() !== undefined ? triangle.val() : 0);

            document.getElementById("hidden_fields_form:hiddenFormSendButton").click();
        } else {
            error_message.remove();
            picture.after($('<p class="error">Необходимо указать радиус, тип точки и дополнительные параметры.</p>'));
        }
    })
})

function loadPicture() {
    $.getJSON("/WebThirdLab_war_exploded/data", function (data) {
        let canvas = createCanvas();
        let context = canvas.getContext('2d');
        context.drawImage(coordinates, 0, 0);

        for (const element of data) {
            addPictureOnCanvas(element["r"], element["x"],
                element["y"], element["result"], canvas);
        }

        picture.attr("src", canvas.toDataURL());
        canvas.remove();
    });
}

function redrawPicture(radius) {
    $.getJSON("/WebThirdLab_war_exploded/data", function (data) {
        $('#picture + p.error').remove();

        let canvas = createCanvas();
        let context = canvas.getContext('2d');
        context.drawImage(coordinates, 0, 0);

        for (const element of data) {
            if ((Math.abs(element["x"]) - radius * (1 / 0.935)) <= 0
                && (Math.abs(element["y"]) - radius * (1 / 0.935))) {
                addPictureOnCanvas(radius, element["x"], element["y"], element["result"], canvas);
            }
        }

        picture.attr("src", canvas.toDataURL());
        canvas.remove();
    });
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
    if (verdict) {
        context.drawImage(green_dot, x * size + jQueryCanvas.width() / 2
            - green_dot.width / 2, (-1 * y * size + jQueryCanvas.height() / 2
            - green_dot.height / 2));
    } else {
        context.drawImage(red_dot, x * size + jQueryCanvas.width() / 2 - red_dot.width / 2,
            (-1 * y * size + jQueryCanvas.height() / 2 - red_dot.height / 2));
    }
}