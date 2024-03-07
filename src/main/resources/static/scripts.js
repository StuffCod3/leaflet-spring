var map = L.map('map').setView([51.505, -0.09], 13);

L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
}).addTo(map);

var drawnItems = new L.FeatureGroup();
map.addLayer(drawnItems);

var drawControl = new L.Control.Draw({
    edit: {
        featureGroup: drawnItems
    },
    draw : {
        position : 'topleft',
        polygon : true,
        polyline : true,
        rectangle : true,
        circle : false,
        marker: false,
        circlemarker: false
    }
});

map.addControl(drawControl);

map.on(L.Draw.Event.CREATED, function (event) {
    var layer = event.layer;
    drawnItems.addLayer(layer);

    var coordinates = layer.getLatLngs();

    if (Array.isArray(coordinates[0])) {
        console.log("ПОЛИГОН")
        sendData("/getCordPolygons", coordinates);
    } else {
        console.log("ПОЛИЛАЙН")
        sendData("/getCordPolyline", coordinates);
    }
});

function sendData(url, coordinates) {
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json",
        data: JSON.stringify({ coordinates: coordinates }),
        success: function (response) {
            console.log("Координаты отправлены " + coordinates);
        },
        error: function (error) {
            console.error("Ошибка отправки", error);
        }
    });
}
