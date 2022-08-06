$(() => {
    class InputField {
        constructor(status, lower_bound, upper_bound) {
            this.status = status;
            this.lower_bound = lower_bound;
            this.upper_bound = upper_bound;
        }

        checkNumber(value) {
            value = value.replace(',', '.');
            return (this.lower_bound <= value && value <= this.upper_bound);
        }
    }

    const data = {
        y_input: new InputField(false, -3, 3),
        r_input: new InputField(false, 1, 4)
    };

    function isNumber(string) {
        return /(^-?\d+([,.]\d+)?)$/.test(string);
    }

    function confirm() {
        if(data.y_input.status == data.r_input.status && data.y_input.status == true && $('input[name="x"]:checked').val() != undefined) {
            $(':button').removeAttr('disabled');
        } else {
            $(':button').prop('disabled', true);
        }
    }

    function check(element) {
        const idName = '#' + element, shortcut = $(idName), value = shortcut.val();

        if(!isNumber(value)) {
            error('Необходимо ввести число.')
        } else if(!data[element].checkNumber(value)) {
            error('Число должно быть в диапазоне от ' + data[element].lower_bound + ' до ' + data[element].upper_bound +'.');
        } else {
            shortcut.nextAll(idName + ' + .error').remove();
            data[element].status = true;
        }

        function error(cause) {
            shortcut.nextAll(idName + ' + .error').remove();
            shortcut.after('<span class="error"><b>' + cause + '</b></span>');
            data[element].status = false;
        }

        confirm();
    }

    $('#y_input').on("input", () => check('y_input'));
    $('#r_input').on("input", () => check('r_input'));
    $('input[name="x"]').on("change", () => confirm());
});