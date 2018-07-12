$( document ).ready(function() {

   var path = String(window.location.pathname);
   var api;
   var columns;

   // deduce API path from url, Build JSON for dataTable
   switch(path) {
       case "/airplanes":
           api = 'http://localhost:8080/api/airplanes';
           columns = [
               { "data": "id" },
               { "data": "name" },
               { "data": "fuel" }
           ];
           break;
           case "/airports":
           api = 'http://localhost:8080/api/airports';
           columns = [
               { "data": "id" },
               { "data": "name" }
           ];
           break;
  }


   $('#dataTable').DataTable( {
       "order": [[ 0, "asc" ]],
       "ajax": {
           url: api,
           dataSrc: ''
       },
       "columns": columns
   } );

   $('#dataTable tbody').on( 'click', 'tr', function () {
        deselect();
       $(this).addClass('selected');
       var table = $('#dataTable').DataTable();
       var data = table.row(this).data();
       getSingleRecord(data.id, api);
       $('#modal').modal('toggle');
   });


   $("#addBtn").on( 'click', function (){
        $("#btnsubmit").attr('onclick', 'submitNew("' + api +'");');
        $('#modal').modal('toggle');
        $("#btnreload").hide();
  });

     $("#btnreload").on( 'click', function (){
        document.getElementById("fuel").value = "5000";
        $("#btnsubmit").click();
    });

});
