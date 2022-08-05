$(() => {
    $('td.input form').on('submit', () => {
        let date = new Date().toLocaleString('en-GB', {timezone: 'UTC'});

        $.ajax({
            url: './main.php',
            method: 'get',
            dataType: 'html',
            data: $('td.input form').serialize() + "&time=" + date,
            success: (html) => {
                alert(html);
            }
        })
    });
})