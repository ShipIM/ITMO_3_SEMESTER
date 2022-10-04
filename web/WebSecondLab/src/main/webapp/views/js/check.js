$(() => {
    let y = $('#y_input');

    function confirm() {
        if (textFieldConfirm(y, y, -3, 5) & $('.r:disabled').length === 1 &&
            $('input[name="x"]:checked').length === 1) {
            $('#send').removeAttr('disabled');
        } else {
            $('#send').prop('disabled', true);
        }
    }

    function error(object, cause) {
        object.next('.error').remove();
        if (cause !== "") object.after('<span class="error">' + cause + '</span>');
    }

    function textFieldConfirm(input, output, lower_bound, upper_bound) {
        let value = input.val().replace(',', '.').replace(/^\s*/, '')
            .replace(/\s*$/, '');

        if (!isNaN(parseFloat(value)) && isFinite(value)) {
            if (lower_bound <= value && value <= upper_bound) {
                error(output, "");
                return true;
            }

            error(output, 'Число должно быть в диапазоне от ' + lower_bound + ' до ' + upper_bound + '.')
        } else {
            value === "" ? error(output, "") : error(output, 'Необходимо ввести число.');
        }

        return false;
    }

    y.on('input', function () {
        confirm()
    });

    $('button').on('click', function () {
        $('.r').removeAttr('disabled');
        $(this).prop('disabled', true);
        $('#picture + p.error').remove();

        confirm();
    });

    $('input[name="x"]').on('change', function () {
        $('input[name="x"]').prop('checked', false);
        $(this).prop('checked', true);

        confirm();
    });
})