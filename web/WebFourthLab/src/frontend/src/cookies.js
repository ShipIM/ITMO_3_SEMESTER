export function addCookie(name, value = "") {
    document.cookie = name + "=" + value + "; path=/";
}

export function getCookie(name) {
    let nameEQ = name + "=";
    let array = document.cookie.split(';');

    for (let i = 0; i < array.length; i++) {
        let cookie = array[i];

        while (cookie.charAt(0) === ' ')
            cookie = cookie.substring(1, cookie.length);

        if (cookie.indexOf(nameEQ) === 0)
            return cookie.substring(nameEQ.length, cookie.length);
    }

    return null;
}

export function eraseCookie(name) {
    document.cookie = name + '=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}