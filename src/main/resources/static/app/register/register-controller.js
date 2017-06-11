angular
    .module('ssnApp')
    .controller('RegisterController', RegisterController);


function RegisterController($scope, $http) {
    $scope.afterRegisterToken = [];
    $scope.register = (userRegisterForm) => {
        $http
            .post('/api/users/register', userRegisterForm)
            .then((res) => {
                $scope.afterRegisterToken.push(res.data);
            });
    };
}