angular
    .module('ssnApp')
    .controller('HomeController', HomeController);


function HomeController($scope, $http, $location) {
    if (localStorage.token === undefined) $location.path('/login');
    else {
        $location.path('/')
    }
}