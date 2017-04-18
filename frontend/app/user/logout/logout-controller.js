angular
    .module('ssnApp.user')
    .controller('LogoutController', LogoutController);

function LogoutController($location) {
    localStorage.removeItem('token');
    $location.path('/login');
}