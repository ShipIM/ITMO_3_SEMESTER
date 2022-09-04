$(() => {
    $.ajax({
        url: './php/table.php',
        method: 'post',
        dataType: 'html',
        success: (html) => {
            $('#content_table_container > table').remove();

            $('#content_table_container').append(html);
        }
    })

    $('form').on('submit', (element) => {
        let date = new Date().toLocaleString('en-GB', {timezone: 'UTC'});

        $.ajax({
            url: './php/table.php',
            method: 'get',
            dataType: 'html',
            data: $('form').serialize() + "&time=" + date,
            success: (html) => {
                $('#content_table_container > table').remove();

                $('#content_table_container').append(html);
            }
        })

        element.preventDefault();
    });

    $(':button').on('click', () => {
        $('form').submit();

        $(':text').val('');
        $(':radio').prop('checked', false);
        $(':button').attr('disabled', true);
    });
})