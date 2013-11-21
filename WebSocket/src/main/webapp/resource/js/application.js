var wsocket;

//window.addEventListener("loaf", connect, false);
function connect() {
    wsocket = new WebSocket("ws://localhost:8080/mavenproject1/ws");
    wsocket.onmessage = onMessage;
}
function send(evt) {
    wsocket.send("send");
    document.write("SEND");
}

wsocket.onerror = function(event) {
     document.write("Error");
};

wsocket.onopen = function(event) {
    document.write(event);
};

wsocket.onmessage = function(event) {
    document.write(evt);
};