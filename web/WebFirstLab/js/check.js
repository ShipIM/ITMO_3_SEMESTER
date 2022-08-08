$(() => {
    class InputField {
        constructor(idName, lower_bound, upper_bound) {
            this.idName = idName;
            this.lower_bound = lower_bound;
            this.upper_bound = upper_bound;
        }

        check() {
            function isNumber(string) {
                return /(^-?\d+([,.]\d+)?)$/.test(string);
            }

            let value = $('#' + this.idName).val();
    
            if(!isNumber(value)) {
                value == "" ? this.error("") : this.error('Необходимо ввести число.');
            } else if(!this.checkNumber(value)) {
                this.error('Число должно быть в диапазоне от ' + this.lower_bound + ' до ' + this.upper_bound +'.');
            } else {
                $('#' + this.idName + ' + .error').remove();
                
                return true;
            }

            return false;
        }

        error(cause) {
            $('#' + this.idName + ' + .error').remove();
            $('#' + this.idName).after('<span class="error"><b>' + cause + '</b></span>');
        }

        checkNumber(value) {
            value = value.replace(',', '.');
            return (this.lower_bound <= value && value <= this.upper_bound);
        }
    }

    let y_input = new InputField('y_input', -3, 3);
    let r_input = new InputField('r_input', 1, 4);

    function confirm() {
        if(y_input.check() == true & r_input.check() == true & $('input[name="x"]:checked').val() != undefined) {
            $(':button').removeAttr('disabled');
        } else {
            $(':button').prop('disabled', true);
        }
    }

    $('#y_input').on("input", () => confirm());
    $('#r_input').on("input", () => confirm());
    $('input[name="x"]').on("change", () => confirm());
});