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
}