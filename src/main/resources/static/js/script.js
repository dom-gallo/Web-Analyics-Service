const currentLocation = window.location;

const Http = new XMLHttpRequest();
const url =`http://localhost:8080/api/v1/pageview/new?url=${currentLocation}`
Http.open("GET", url);
Http.send();
Http.onreadystatechange = (e) => {
    console.log(Http.responseText);
};
