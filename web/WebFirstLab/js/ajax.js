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
        $.ajax({
            url: './php/table.php',
            method: 'get',
            dataType: 'html',
            data: $('form').serialize() + "&time=" + Intl.DateTimeFormat().resolvedOptions().timeZone,
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