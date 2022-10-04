$(() => {
    let form = $('#fields_form');

    form.on('submit', (element) => {

        form.append(() => {
            return $('<input>', {
                type: 'hidden',
                name: 'r',
                value: $('button:disabled').val()
            })
        })

        form.append(() => {
            return $('<input>', {
                type: 'hidden',
                name: 'time',
                value: Intl.DateTimeFormat().resolvedOptions().timeZone
            })
        })

        return true;
    });
})