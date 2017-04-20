angular
    .module('ssnApp')
    .controller('RegisterController', RegisterController);


function RegisterController($scope, $http, $location) {
    $scope.register = (userRegisterForm) => {
        $http
            .post('/api/users/register', userRegisterForm)
            .then(() => {
                $location.path('/login');
            });
    };
    $http
        .get('/api/countries')
        .then((res) => {
            $scope.countries = res.data;
        });

    $scope.getCities = (countryId) => {
        $http
            .get('/api/countries/' + countryId)
            .then((res) => {
                $scope.cities = res.data.cities;
            })
    }
}