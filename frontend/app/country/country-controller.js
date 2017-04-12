angular
    .module('ssnApp')
    .controller('CountryController', CountryController);

function CountryController($scope, $http) {
    $scope.getCountries = () => {
        $http
            .get('/api/countries')
            .then((res) => {
                $scope.countries = res.data;
            });
    }
}