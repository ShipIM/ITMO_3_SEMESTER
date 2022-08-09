$(() => {
    class InputField {
        constructor(object, lower_bound, upper_bound) {
            this.object = object;
            this.lower_bound = lower_bound;
            this.upper_bound = upper_bound;
        }

        result() {
            let value = this.object.val().replace(',', '.');

            if(/(^-?\d+([,.]\d+)?)$/.test(value)) {
                if(this.lower_bound <= value && value <= this.upper_bound) {
                    this.object.next('.error').remove();
                    return true;
                } else {
                    this.error('Число должно быть в диапазоне от ' + this.lower_bound + ' до ' + this.upper_bound +'.')
                }
            } else {
                value == "" ? this.error("") : this.error('Необходимо ввести число.');
            }
        }

        error(cause) {
            this.object.next('.error').remove();
            this.object.after('<span class="error">' + cause + '</span>');
        }
    }

    let y_input = new InputField($('#y_input'), -3, 3), r_input = new InputField($('#r_input'), 1, 4);

    function confirm() {
        if (y_input.result() == true & r_input.result() == true &  $('input[name="x"]:checked').val() != undefined) {
            $(':button').removeAttr('disabled');
        } else {
            $(':button').prop('disabled', true);
        }
    }

    $('#y_input').on('input', () => confirm());
    $('#r_input').on('input', () => confirm());
    $('input[name="x"]').on('change', () => confirm());
})