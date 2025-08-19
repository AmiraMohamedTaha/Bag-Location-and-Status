var userName = getLoggedInUser().username

var query = `SELECT * FROM APPLICATION_8590 WHERE My_User = '${userName}'`

ExecuteQueryModified(query, Grid_1_callbackFunc);

function Grid_1_callbackFunc(error, xhr) {
  if (xhr.response) {
    Grid_1DataArr = JSON.parse(xhr.response);
    if (Grid_1DataArr.length > 0) {
      sessionStorage.setItem("Bag_Id", Grid_1DataArr[0].Bag_Id)
      Success("Logged successfully")
    } else {
      Warning('You have no bags in the system')
    }
  }
}
