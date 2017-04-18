angular
    .module('ssnApp')
    .controller('LoginController', LoginController);


function LoginController($scope, $http) {
    $scope.send = (user) => {
        $http
            .post('/api/login', user)
            .then((res) => {
                localStorage.token = res.data;
            });
    };
}