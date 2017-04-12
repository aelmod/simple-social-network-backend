angular
    .module('ssnApp')
    .controller('SendRegisterController', SendRegisterController);


function SendRegisterController($scope, $http, $location) {
    $scope.register = (userRegisterForm) => {
        $http
            .post('/api/users/register', userRegisterForm)
        // .then((res) => {
        //     $location.path('login');
        // });
    };
}