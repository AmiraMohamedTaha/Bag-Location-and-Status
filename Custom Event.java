event.log('Bag Track');

var radius = Sensor.cached.R;
var lux = Sensor.cached.currentLuxValue;
var sensorId = Sensor.profile.SensorId;


const query = `SELECT * FROM APPLICATION_8590 WHERE Bag_Id = '${sensorId}' LIMIT 1`;

SearchIn(query, function (err, result) {
  if (err) {
    event.error("Query Error: " + err);
    return;
  }

  if (!result || result.length === 0) {
    event.error("Bag not found");
    return;
  }

  var email = result[0].Email;

  const opt = {
    "host": "smtp.gmail.com",
    "port": "587",
    "username": "amira.mohamed.mokhtar@gmail.com",
    "password": "yvok tlmz lcwz rbsl",
    "to": email,
  };

  if (radius > 100) {
    opt.subject = "Bag is out of range.";
    opt.body = "The bag has moved out of range.";
    SendEmail(opt);
    event.log("Sent: Bag is out of range.");
  }

  if (lux > 10) {
    opt.subject = "Bag is Opened";
    opt.body = "Light detected.";
    SendEmail(opt);
    event.log("Sent: Bag is Opened");
  }

  event.end();
});
