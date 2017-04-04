angular
    .module('ssnApp')
    .controller('HomeController', HomeController);


function HomeController($scope, $http) {
    $scope.greeting = "Hello";
}