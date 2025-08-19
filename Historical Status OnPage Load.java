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
      data = JSON.parse(result.response);
    }

    SetPluginParameterValue('Map 1', 'SQL query statement', data);
    DrawPlugin('Map 1');
  }
}
