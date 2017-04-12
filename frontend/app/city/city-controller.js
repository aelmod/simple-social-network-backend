angular
    .module('ssnApp')
    .controller('CityController', CityController);

function CityController($scope, $http) {
    $scope.cities = () => {
        $http
            .get('/api/cities')
            .then((res) => {
                $scope.cities = res.data;
            })
    }
}