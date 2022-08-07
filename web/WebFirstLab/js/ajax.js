$(() => {
    $('td.input form').on('submit', (element) => {
        let date = new Date().toLocaleString('en-GB', {timezone: 'UTC'});

        $.ajax({
            url: './php/table.php',
            method: 'get',
            dataType: 'html',
            data: $('td.input form').serialize() + "&time=" + date,
            success: (html) => {
                $('.content_table').remove();

                $('td.content_table_container').append(html); 
            }
        })

        element.preventDefault();
    });

    $(':button').on('click', () => {
        $('td.input form').submit();

        $(':text').val('');
        $(':radio').prop('checked', false);
        $(':button').attr('disabled', true);
    });
})