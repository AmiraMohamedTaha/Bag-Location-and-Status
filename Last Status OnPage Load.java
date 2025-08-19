sessionStorage.setItem('Bag_Id', '68398');
var bagId = sessionStorage.getItem("Bag_Id");
if (bagId == null) {
  window.location.replace("https://learning.masterofthings.com/rte.html?project=39932&page=6");
} else {
  var Query = `SELECT * FROM GROUP_${bagId} order by TimeStamp desc limit 1`
  debugger
  ExecuteQueryModified(Query, SearchBag)

  function SearchBag(err, result) {
    if (result.response) {
      debugger
      var data = JSON.parse(result.response);
      if (data[0].R > 100) {
        SetPluginParameterValue('Map 1', 'Border color', 'red');
      } else {
        SetPluginParameterValue('Map 1', 'Border color', 'green');
      }
      console.log("Lux Value:", data[0].currentLuxValue);
      if (data[0].currentLuxValue > 10) {
        SetPluginParameterValue('Map 1', 'Marker URL', 'https://cdn-icons-png.flaticon.com/128/16493/16493994.png');
      } else {
        SetPluginParameterValue('Map 1', 'Marker URL', 'https://cdn-icons-png.flaticon.com/128/6935/6935859.png');
      }
    }

    DrawPlugin('Map 1');
  }
}
