angular
    .module('ssnApp')
    .controller('CountryController', CountryController);

function CountryController($scope, $http) {
    $http
        .get('/api/countries')
        .then((res) => {
            $scope.countries = res.data;
        });
}