const currentLocation = window.location;
const Http = new XMLHttpRequest();
const url =`http://localhost:8080/api/v1/pageview/new?url=${currentLocation}`
Http.open("GET", url);
Http.send();
Http.onreadystatechange = (e) => {
    console.log(Http.responseText);
};

[
    {"id":32,"viewedAt":"2022-05-06T19:13:31.936023","url_viewed":"/hi"},
    {"id":33,"viewedAt":"2022-05-06T19:13:34.092667","url_viewed":"/hi1"},
    {"id":34,"viewedAt":"2022-05-06T19:13:35.13127","url_viewed":"/hi12"},
    {"id":35,"viewedAt":"2022-05-06T19:13:35.998821","url_viewed":"/hi"},
    {"id":36,"viewedAt":"2022-05-06T19:13:36.921716","url_viewed":"/hi"},
    {"id":37,"viewedAt":"2022-05-06T19:13:38.278002","url_viewed":"/"},
    {"id":38,"viewedAt":"2022-05-06T19:13:41.347037","url_viewed":"/home"},
    {"id":39,"viewedAt":"2022-05-06T19:13:44.451061","url_viewed":"/home/hello"}
]