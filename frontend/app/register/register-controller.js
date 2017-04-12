angular
    .module('ssnApp')
    .controller('RegisterController', RegisterController);


function RegisterController($scope, $http) {
    $http
        .get('/api/countries')
        .then((res) => {
            $scope.countries = res.data;
        });
    $http
        .get('/api/cities')
        .then((res) => {
            $scope.cities = res.data;
        })
}