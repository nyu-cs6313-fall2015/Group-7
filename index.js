/*---------------------Google Map-------------------*/
<script>
var width = 7; // the width of the matrix
var height = 7; // the height of the matrix
var map; // google map object
var infoWindow; // infoWindow, show the keyword and other parameters
var overlay = new Array(); // overlay array
var visRectangle = new Array(); // rectangle and overlay array
TXTOverlay.prototype = new google.maps.OverlayView();

function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    zoom: 13,
    center: {lat: 40.719601, lng: -73.963501},
    mapTypeId: google.maps.MapTypeId.SATELLITE,
    heading: 0,
    tilt: 60 
  });
  infoWindow = new google.maps.InfoWindow();
  pageRefresh();
}

function updateMap() {
  var b = [];
    b.push({name:"NorthEast", latlng: new google.maps.LatLng(40.878192, -73.909766)})
    b.push({name:"SouthWest", latlng: new google.maps.LatLng(40.699885, -74.021867)})
    var bounds = new google.maps.LatLngBounds();
    for (var i = 0; i < b.length; i++) {
      bounds.extend(b[i].latlng);   
    }
    map.fitBounds(bounds);

  baseLat = (b[0].latlng.lat() - b[1].latlng.lat()) / height;  
  baseLng = (b[0].latlng.lng() - b[1].latlng.lng()) / width;  
  for (var h = 0; h < height; h++) {
    for (var w = 0; w < width; w++) {
      var rectangle = new google.maps.Rectangle({
        map: map,
        bounds: new google.maps.LatLngBounds (
          new google.maps.LatLng(b[1].latlng.lat()+h*baseLat, b[1].latlng.lng()+w*baseLng),
          new google.maps.LatLng(b[1].latlng.lat()+(h+1)*baseLat, b[1].latlng.lng()+(w+1)*baseLng)
        ),
        strokeWeight: 0.5,
        visible: true,
        //fillColor:"#00FF00"
      })
      

      var txt = new Array()
      txt[0] = "Hello world";
      txt[1] = "Test";
      var relevance = new Array()
      relevance[0] = 0.5;
      relevance[1] = 0.8;
      var age = new Array()
      age[0] = 0
      var xdata = <?php echo $testid; ?>
      
      console.log(xdata);
      //var txt = "Hello world"
      overlay[h*width+w] = new TXTOverlay(rectangle.bounds, txt, relevance, age, map, "up");
      visRectangle[h*width+w] = new VisRectangle(h*width+w, rectangle, overlay[h*width+w]);
    }
  }

  // bound change
  google.maps.event.addListener(map, "bounds_changed", function() {
    zoom = map.getZoom();  
    if (zoom < 13) {
      for(var i = 0; i < overlay.length; i++) {
        overlay[i].hide();
      }
    }
    else {
      for(var i = 0; i < overlay.length; i++) {
        overlay[i].show();
      }
    }
  })
}


function VisRectangle(id, GRectangle, GOverlay) {
  this.id_ = id;
  this.rectangle_ = GRectangle;
  this.overlay_ = GOverlay;
  this.rectangle_.addListener('click', function() {rectClicked(id, GRectangle, GOverlay)});
//  this.rectangle_.addListener('mouseover', function() {rectMouseOver(id, GRectangle, GOverlay)});
}

// run after rectangle is clicked
function rectClicked(id, rectangle, overlay) {
  var txt = id + "</br>";
  document.getElementById("listContent").innerHTML = txt;
  var ne = rectangle.getBounds().getNorthEast();
  var sw = rectangle.getBounds().getSouthWest();

  var contentString = '<b>' + overlay.txt_ + '</b><br>' + 
    'New north-east corner: ' + ne.lat() + ', ' + ne.lng() + '<br>' + 
    'New south-west corner: ' + sw.lat() + ', ' + sw.lng();
  var center = new google.maps.LatLng(sw.lat() + (ne.lat()-sw.lat())/2, sw.lng() + (ne.lng()-sw.lng())/2);

  infoWindow.setContent(contentString);
  infoWindow.setPosition(center)
  infoWindow.open(map);

}

// run after mouseover it
function rectMouseOver(id, rectangle, overlay) {
  var ne = rectangle.getBounds().getNorthEast();
  var sw = rectangle.getBounds().getSouthWest();

  var contentString = '<b>' + overlay.txt_ + '</b><br>' + 
    'New north-east corner: ' + ne.lat() + ', ' + ne.lng() + '<br>' + 
    'New south-west corner: ' + sw.lat() + ', ' + sw.lng();
  var center = new google.maps.LatLng(sw.lat() + (ne.lat()-sw.lat())/2, sw.lng() + (ne.lng()-sw.lng())/2);

  infoWindow.setContent(contentString);
  infoWindow.setPosition(center)
  infoWindow.open(map);
}
/** @constructor */
function TXTOverlay(bounds, txt, relevance, age, map, type) {

  this.bounds_ = bounds;
  this.txt_ = txt;
  this.relevance_ = relevance;
  this.age_ = age;
  this.map_ = map;
  this.type_ = type;
  this.div_ = null;
  this.setMap(map);
}

TXTOverlay.prototype.onAdd = function() {

  var div = document.createElement('div');
  div.style.border = 'none';
  div.style.borderWidth = '0px';
  div.style.position = 'absolute';

  for (idx in this.txt_) {
    var label = document.createElement('label');
    label.innerHTML = this.txt_[idx] +'</br>';
    label.style.width = '10px';
    label.style.height = '10px';
    label.style.fontWeight = 'bold';
    label.style.fontSize = this.relevance_[idx]*4 + 'em';
    label.style.position = 'relative';
    div.appendChild(label);
  }
  
  /*var img = document.createElement('img');
  if (this.type_ == "up") {
    img.src = "./images/up.png";   
  } 
  img.style.marginLeft = '5px';
  img.style.width = '10px';
  img.style.height = '10px';
  img.style.position = 'relative';
  div.appendChild(img);*/

  this.div_ = div;

  var panes = this.getPanes();
  panes.overlayImage.appendChild(this.div_);
};

TXTOverlay.prototype.draw = function() {

  var overlayProjection = this.getProjection();
  var sw = overlayProjection.fromLatLngToDivPixel(this.bounds_.getSouthWest());
  var ne = overlayProjection.fromLatLngToDivPixel(this.bounds_.getNorthEast());

  var div = this.div_;
  div.style.left = sw.x + 'px';
  div.style.top = ne.y + 'px';
  div.style.width = (ne.x - sw.x) + 'px';
  div.style.height = (sw.y - ne.y) + 'px';
};

TXTOverlay.prototype.hide = function() {
  if (this.div_) {
    this.div_.style.visibility = 'hidden';
  }
};

TXTOverlay.prototype.show = function() {
  if (this.div_) {
    this.div_.style.visibility = 'visible';
  }
};

google.maps.event.addDomListener(window, 'load', initMap);



/*--------------------------- TagCloud -----------------------*/
function updateTagCloud() {
  width = 600;
  height = 200;

  var fill = d3.scale.category20();
  
  d3.layout.cloud().size([width, height])
      .words([
        ".NET", "Silverlight", "jQuery", "CSS3", "HTML5", "JavaScript", "SQL","C#"].map(function(d) {
        return {text: d, size: 10 + Math.random() * 30};
      }))
      .rotate(function() { return ~~(Math.random() * 2) * 90; })
      .font("Impact")
      .fontSize(function(d) { return d.size; })
      .on("end", draw)
      .start();
  
  function draw(words) {
    d3.select("#tagCloud")
        .attr("width", width)
        .attr("height", height)
      .append("g")
        .attr("transform", "translate(200,100)")
      .selectAll("text")
        .data(words)
      .enter().append("text")
        .style("font-size", function(d) { return d.size + "px"; })
        .style("font-family", "Impact")
        .style("fill", function(d, i) { return fill(i); })
        .attr("text-anchor", "middle")
        .attr("transform", function(d) {
          return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
        })
        .text(function(d) { return d.text; });
  }
}


/*----------------------Web Page------------------*/
function pageRefresh() {
  updateMap();
  //updateTagCloud();
}
</script>
