angular
    .module('ssnApp')
    .controller('LoginController', LoginController);


function LoginController($scope, $http, $location, $rootScope) {
    $scope.send = (user) => {
        $http
            .post('/api/login', user)
            .then((res) => {
                localStorage.token = res.data;
                $http
                    .get('/api/users/currentUser')
                    .then((res) => {
                        $rootScope.currentUser = res.data;
                        $location.path('/user/' + res.data.id);
                    });
            });
    };
}