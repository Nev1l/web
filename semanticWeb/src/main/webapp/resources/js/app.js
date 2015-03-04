var app = angular.module('app', []);

app.controller('SetController', function($scope, $http, $q, $window) {
	$scope.search = function() {
		var sql = "select * where {?s rdf:type "+$scope.param.value+".?s :name ?name. filter regex(?name,\""+$scope.text+"\",\"i\")}";
		$http.get(
				"http://localhost:80/openrdf-sesame?action=exec&queryLn=SPARQL&query="+sql).success(
				function(response) {
					$scope.weather = new Weather(coords, temp, wind,
							cloudiness, pressure, humidity);
				});
	};
	$scope.obj = {};
	$scope.text = '';
	$scope.params = [ new Param("Track", "Song"), new Param("Album", "Album"),
			new Param("Group", "MusicGroup"),
			new Param("Artist", "MusicArtist") ];
	$scope.param = $scope.params[0];
});

var Param = function(name, value) {
	this.name = name;
	this.value = "<http://www.semanticweb.org/viktar_kapachou/ontologies/2015/1/untitled-ontology-7#"
			+ value + ">";
};

var Artist = function(name,image,description){
	this.name = name;
	this.image = image;
	this.description = description;
}

var MusicGroup = function(name,image,description){
	this.name = name;
	this.image = image;
	this.description = description;
}

var Album = function(name,image,year){
	this.name = name;
	this.image = image;
	this.year = year;
	this.performer=performer;
}

/*
 *  $(document).ready(function() {
      $.ajax({
                url: 'http://localhost:80/openrdf-sesame/repositories/test',
                dataType: 'json', 
                data: { 
                    queryLn: 'SPARQL',
                    query: "SELECT * WHERE { ?s ?p ?o }", 
                    limit: 'none',
                    infer: 'true',
                    Accept: 'application/sparql-results+json'
                },
                success: displayData, 
                error: displayError
        });
    });

    function displayError(xhr, textStatus, errorThrown) {
        alert(textStatus);
        alert(errorThrown);
    }

    function displayData(data) {
        var header = $('#result thead').append('<tr/>');
        $.each(data.head.vars, function(key,value) {
            header.append("<th>" + value + "</th>");
        });


        $.each(data.results.bindings, function(index, bs) {
        var row = $('<tr/>');
        $.each(data.head.vars, function(key, varname) {
            row.append("<td>" + bs[varname].value + "</td>"); 
            });
        $("#result tbody").after(row);
        });
    }
 * */

var Song = function(name,image,year){
	this.name = name;
	this.text = text;
	this.performer=performer;
}